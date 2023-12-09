package com.be_project.entity;

import com.be_project.entity.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String categoryPost;
    private String description;
    private String requirement;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String avatar;
    private LocalDate createdAt;
    private String status;
    @ManyToOne
    private Account account;
    private long countView;
    @ManyToOne
    private CategoryProduct categoryProduct;

    public Post(PostDto postDto) {
        this.title = postDto.getTitle();
        this.categoryPost = postDto.getCategoryPost();
        this.description = postDto.getDescription();
        this.requirement = postDto.getRequirement();
        this.address = postDto.getAddress();
        this.avatar = postDto.getAvatar();
        this.status = postDto.getStatus();
        this.countView = postDto.getCountView();
        this.account = postDto.getAccount();
        this.createdAt = postDto.getCreatedAt();
        this.categoryProduct = postDto.getCategoryProduct();
    }
}
