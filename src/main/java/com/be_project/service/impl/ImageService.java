package com.be_project.service.impl;

import com.be_project.entity.Image;
import com.be_project.repository.IImageRepo;
import com.be_project.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepo imageRepo;
    @Override
    public List<Image> findAllByPostId(long postId) {
        return imageRepo.findAllByPostId(postId);
    }
}
