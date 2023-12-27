package com.be_project.service.impl;

import com.be_project.entity.CategoryProduct;
import com.be_project.entity.Post;
import com.be_project.repository.ICategoryProductRepo;
import com.be_project.service.ICategoryProductService;
import com.be_project.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryProductService implements ICategoryProductService {
    @Autowired
    private ICategoryProductRepo categoryProductRepo;
    @Autowired
    private IPostService postService;
    @Override
    public List<CategoryProduct> getAll() {
        return categoryProductRepo.findAll();
    }

    @Override
    public CategoryProduct createProductCategory(CategoryProduct categoryProduct) {
        return categoryProductRepo.save(categoryProduct);
    }

    @Override
    public CategoryProduct editProductCategory(long productCategoryId, CategoryProduct editCategoryProduct) {
        editCategoryProduct.setId(productCategoryId);
        return categoryProductRepo.save(editCategoryProduct);
    }

    @Override
    public void deleteProductCategory(long productCategoryId) {

        Optional<CategoryProduct> categoryProduct = categoryProductRepo.findById(productCategoryId);
        if(categoryProduct.isPresent()){
            List<Post> listPost = postService.getAllByCategoryProduct(categoryProduct.get());
            for(Post post:listPost){
                postService.deletePost(post.getId());
            }
            categoryProductRepo.delete(categoryProduct.get());
        }
    }
}
