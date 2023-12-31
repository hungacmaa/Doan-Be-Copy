package com.be_project.service;

import com.be_project.entity.CategoryProduct;

import java.util.List;

public interface ICategoryProductService {
    List<CategoryProduct> getAll();
    CategoryProduct createProductCategory(CategoryProduct categoryProduct);

    CategoryProduct editProductCategory(long productCategoryId, CategoryProduct categoryProduct);

    void deleteProductCategory(long productCategoryId);
}
