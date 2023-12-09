package com.be_project.service;


import com.be_project.entity.Image;

import java.util.List;

public interface IImageService {
    List<Image> findAllByPostId(long postId);
}
