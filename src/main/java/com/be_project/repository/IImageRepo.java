package com.be_project.repository;

import com.be_project.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IImageRepo extends JpaRepository<Image, Long> {
    List<Image> findAllByPostId(long postId);
    void deleteByPostId(long postId);
}
