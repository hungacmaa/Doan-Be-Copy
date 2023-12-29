package com.be_project.repository;


import com.be_project.entity.Censor;
import com.be_project.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICensorRepo extends JpaRepository<Censor, Long> {

    List<Censor> findAllByPostId(long postId);

    Long countByStatus(String censorStatus);

    @Query("SELECT COUNT(c) FROM Censor c WHERE c.reviewer.id = 3" )
    Long countAISystem();

    @Query("SELECT COUNT(c) FROM Censor c WHERE c.reviewer.id != 3" )
    Long countAdmin();

    @Query("SELECT COUNT(c) FROM Censor c WHERE c.reviewer.id = 3 AND c.result ='Duyệt'" )
    long countAcceptByAI();

    @Query("SELECT COUNT(c) FROM Censor c WHERE c.reviewer.id = 3 AND c.result ='Khóa'" )
    long countRejectByAI();

    @Query("SELECT COUNT(c) FROM Censor c WHERE c.reviewer.id != 3 AND c.result ='Duyệt'" )
    long countAcceptByAdmin();

    @Query("SELECT COUNT(c) FROM Censor c WHERE c.reviewer.id != 3 AND c.result ='Khóa'" )
    long countRejectByAdmin();
}
