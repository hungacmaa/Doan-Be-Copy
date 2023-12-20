package com.be_project.repository;

import com.be_project.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IReportRepo extends JpaRepository<Report, Long> {
    @Query("SELECT r FROM Report r WHERE r.status LIKE CONCAT('%', :status, '%') " +
            "AND r.post.title LIKE CONCAT('%', :title, '%') " +
            "AND r.account.username LIKE CONCAT('%', :username, '%') " +
            "AND r.createdAt BETWEEN :startDate AND :endDate ORDER BY r.createdAt DESC")
    Page<Report> getAll(@Param("status") String status,
                        @Param("title") String title,
                        @Param("username") String username,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        Pageable pageable);

    @Query("SELECT r FROM Report r WHERE r.account.id = :accountId " +
            "AND r.status LIKE CONCAT('%', :status, '%') " +
            "AND r.post.title LIKE CONCAT('%', :title, '%') " +
            "AND r.account.username LIKE CONCAT('%', :username, '%') " +
            "AND r.createdAt BETWEEN :startDate AND :endDate ORDER BY r.createdAt DESC")
    Page<Report> getAllByAccountId(@Param("accountId") long accountId,
                                   @Param("status") String status,
                                   @Param("title") String title,
                                   @Param("username") String username,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate,
                                   Pageable pageable);

    @Query("SELECT r FROM Report r WHERE r.post.id = :postId " +
            "AND r.post.status = 'Vô hiệu hóa'")
    Report getReportByPostId(@Param("postId") long postId);
}
