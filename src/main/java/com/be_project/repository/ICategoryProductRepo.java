package com.be_project.repository;

import com.be_project.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryProductRepo extends JpaRepository<CategoryProduct, Long> {
}
