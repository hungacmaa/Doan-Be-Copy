package com.be_project.service;

import com.be_project.entity.Message;

import java.util.List;

public interface IMessageService {
    List<Message> findAllBySenderIdAndReceiverId(long senderId, long receiverId);
    Message save(Message message);
    long countUnreadMessagesByReceiverId(long receiverId);
    long countUnreadMessagesByAccountLoginIdAndSenderId(long accountLoginId, long senderId);
    void changeStatusMessage(long senderId, long receiverId);
}
