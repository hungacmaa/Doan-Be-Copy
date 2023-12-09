package com.be_project.service.impl;

import com.be_project.entity.Message;
import com.be_project.repository.IMessageRepo;
import com.be_project.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepo messageRepo;
    @Override
    public List<Message> findAllBySenderIdAndReceiverId(long senderId, long receiverId) {
        return messageRepo.findAllBySenderIdAndReceiverId(senderId, receiverId);
    }

    @Override
    public Message save(Message message) {
        return messageRepo.save(message);
    }

    @Override
    public long countUnreadMessagesByReceiverId(long receiverId) {
        return messageRepo.countUnreadMessagesByReceiverId(receiverId);
    }

    @Override
    public long countUnreadMessagesByAccountLoginIdAndSenderId(long accountLoginId, long senderId) {
        return messageRepo.countUnreadMessagesByAccountLoginIdAndSenderId(accountLoginId, senderId);
    }

    @Override
    public void changeStatusMessage(long senderId, long receiverId) {
        messageRepo.changeStatusMessage(senderId, receiverId);
    }
}
