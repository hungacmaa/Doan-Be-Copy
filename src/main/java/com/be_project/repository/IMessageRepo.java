package com.be_project.repository;

import com.be_project.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IMessageRepo extends JpaRepository<Message, Long> {
    @Query("SELECT m from Message m WHERE m.sender.id = :senderId AND m.receiver.id = :receiverId" +
            " OR m.sender.id = :receiverId AND m.receiver.id = :senderId ORDER BY m.createdAt")
    List<Message> findAllBySenderIdAndReceiverId(@Param("senderId") long senderId, @Param("receiverId") long receiverId);

    @Query("SELECT COUNT(m.id) FROM Message m WHERE m.receiver.id = :receiverId AND m.status = 'Chưa xem'")
    long countUnreadMessagesByReceiverId(@Param("receiverId") long receiverId);

    @Query("SELECT COUNT(m.id) FROM Message m WHERE m.sender.id = :senderId AND m.receiver.id = :accountLoginId AND m.status = 'Chưa xem'")
    long countUnreadMessagesByAccountLoginIdAndSenderId(@Param("accountLoginId") long accountLoginId, @Param("senderId") long senderId);

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.status = 'Đã xem' WHERE m.sender.id = :senderId AND m.receiver.id= :receiverId")
    void changeStatusMessage(@Param("senderId") long senderId, @Param("receiverId") long receiverId);
}
