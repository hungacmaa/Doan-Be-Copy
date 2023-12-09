package com.be_project.entity.dto;

import com.be_project.entity.Account;
import com.be_project.entity.CategoryProduct;
import com.be_project.entity.Image;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class PostDto {
    private long id;
    private String title;
    private String categoryPost;
    private String description;
    private String requirement;
    private String address;
    private String avatar;
    private String status = "Chưa trao đổi";
    private LocalDate createdAt = LocalDate.now();
    private long countView = 0;
    private List<Image> images;
    private List<Image> imagesDelete;
    private Account account;
    private CategoryProduct categoryProduct;
}
