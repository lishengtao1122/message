package com.lsdk.service.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsdk.service.dao.model.Message;
import com.lsdk.service.dao.model.MessageLog;
import com.lsdk.service.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClearMessageTask extends BaseTask implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(ClearMessageTask.class);

    @Override
    public void run() {
        Page<Message> page = new Page<>(0,30);
        page = messageService.page(page,new QueryWrapper<Message>().le("create_date", DateUtils.addDays(new Date(),-1)).isNotNull("finish_date"));
        List<Message> messageList = page.getRecords();
        if(CollectionUtils.isNotEmpty(messageList)){
            List<Integer> ids = messageList.stream().map(Message::getId).collect(toList());
            if(!messageService.removeByIds(ids)){
                logger.error(">>> clear message id :{} failure! <<<",ids);
                return;
            }
            if(messageLogService.remove(new QueryWrapper<MessageLog>().in("m_id",ids))){
                logger.error(">>> clear message logs m_id :{} failure! <<<",ids);
                return;
            }

        }
    }
}
