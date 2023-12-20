package com.be_project.repository;


import com.be_project.entity.Censor;
import com.be_project.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICensorRepo extends JpaRepository<Censor, Long> {

    List<Censor> findAllByPostId(long postId);
}
