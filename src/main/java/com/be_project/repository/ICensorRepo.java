package com.be_project.repository;


import com.be_project.entity.Censor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICensorRepo extends JpaRepository<Censor, Long> {
}
