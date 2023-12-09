package com.be_project.repository;
import com.be_project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
    Role findById(long id);

}

