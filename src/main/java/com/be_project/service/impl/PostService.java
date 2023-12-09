package com.be_project.service.impl;

import com.be_project.entity.Image;
import com.be_project.entity.Post;
import com.be_project.entity.dto.FilterDto;
import com.be_project.entity.dto.PostDto;
import com.be_project.repository.IImageRepo;
import com.be_project.repository.IPostRepo;
import com.be_project.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepo postRepo;
    @Autowired
    private IImageRepo imageRepo;

    @Override
    public Page<Post> getAll(FilterDto filterDto, int page, int size) {
        Pageable pageable;
        if (filterDto.getSort() != null && filterDto.getSort().equals("createdAt-desc")) {
            pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        } else if (filterDto.getSort() != null && filterDto.getSort().equals("countView-asc")) {
            pageable = PageRequest.of(page, size, Sort.by("countView").ascending());
        } else if (filterDto.getSort() != null && filterDto.getSort().equals("countView-desc")) {
            pageable = PageRequest.of(page, size, Sort.by("countView").descending());
        } else {
            pageable = PageRequest.of(page, size);
        }

        if (filterDto.getStartDate() == null) filterDto.setStartDate(LocalDate.parse("1000-01-01"));

        if (filterDto.getEndDate() == null) filterDto.setEndDate(LocalDate.parse("9999-12-31"));

        return postRepo.getAll(filterDto.getStatus(), filterDto.getUsername(), filterDto.getTitle(), filterDto.getCategoryPost(), filterDto.getCategoryProductName(), filterDto.getStartDate(), filterDto.getEndDate(), pageable);
    }

    @Override
    public Page<Post> getAllByAccountId(Long accountId, FilterDto filterDto, int page, int size) {
        if (filterDto.getStartDate() == null) filterDto.setStartDate(LocalDate.parse("1000-01-01"));

        if (filterDto.getEndDate() == null) filterDto.setEndDate(LocalDate.parse("9999-12-31"));

        return postRepo.findAllByAccountId(accountId, filterDto.getCategoryPost(), filterDto.getCategoryProductName(), filterDto.getStatus(), filterDto.getTitle(), filterDto.getStartDate(), filterDto.getEndDate(), PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    @Override
    public Post getByIdAndIncreaseViews(long postId) {
        Post post = postRepo.findById(postId).get();
        post.setCountView(post.getCountView() + 1);
        return postRepo.save(post);
    }

    @Override
    public Post createPost(PostDto postDto) {
        Post post = new Post(postDto);
        Post postDB = postRepo.save(post);
        List<Image> imageList = postDto.getImages();
        for (Image image : postDto.getImages()) {
            image.setPost(postDB);
        }
        imageRepo.saveAll(imageList);
        return postDB;
    }

    @Override
    public Post editPost(long postId, PostDto postDto) {
        imageRepo.saveAll(postDto.getImages());
        imageRepo.deleteAll(postDto.getImagesDelete());
        Post post = new Post(postDto);
        post.setId(postId);
        return postRepo.save(post);
    }

    @Override
    public Boolean deletePost(long postId) {
        return postRepo.deleteById(postId);
    }
}
