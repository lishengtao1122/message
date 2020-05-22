package com.lsdk.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsdk.service.dao.mapper.MessageLogMapper;
import com.lsdk.service.dao.model.MessageLog;
import com.lsdk.service.service.IMessageLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lesent
 * @since 2020-05-19
 */
@Service
public class MessageLogService extends ServiceImpl<MessageLogMapper, MessageLog> implements IMessageLogService {

}
