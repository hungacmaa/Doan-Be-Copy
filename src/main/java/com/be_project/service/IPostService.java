package com.be_project.service;

import com.be_project.entity.Post;
import com.be_project.entity.dto.FilterDto;
import com.be_project.entity.dto.PostDto;
import org.springframework.data.domain.Page;

public interface IPostService {
    Page<Post> getAll(FilterDto filterDto, int page, int size);
    Page<Post> getAllByAccountId(Long accountId, FilterDto filterDto, int page, int size);
    Post getByIdAndIncreaseViews(long postId);
    Post createPost(PostDto postDTO);
    Post editPost(long postId, PostDto postDto);
    Boolean deletePost(long postId);
}
