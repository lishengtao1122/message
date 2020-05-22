package com.lsdk.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsdk.service.dao.mapper.MessageMapper;
import com.lsdk.service.dao.model.Message;
import com.lsdk.service.service.IMessageService;
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
public class MessageService extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
