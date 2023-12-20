package com.be_project.service.impl;

import com.be_project.entity.Post;
import com.be_project.entity.Report;
import com.be_project.entity.dto.FilterDto;
import com.be_project.repository.IPostRepo;
import com.be_project.repository.IReportRepo;
import com.be_project.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportService implements IReportService {
    @Autowired
    private IReportRepo reportRepo;
    @Autowired
    private IPostRepo postRepo;

    @Override
    public Report saveReport(Report report) {
        return reportRepo.save(report);
    }

    @Override
    public Page<Report> getAllReports(FilterDto filterDto, int page, int size) {
        if (filterDto.getStartDate() == null || filterDto.getStartDate().equals(""))
            filterDto.setStartDate(LocalDate.parse("1000-01-01"));

        if (filterDto.getEndDate() == null || filterDto.getEndDate().equals(""))
            filterDto.setEndDate(LocalDate.parse("9999-12-31"));
        return reportRepo.getAll(filterDto.getStatus(), filterDto.getTitle(), filterDto.getUsername(), filterDto.getStartDate(), filterDto.getEndDate(), PageRequest.of(page, size));
    }

    @Override
    public Page<Report> getAllReportsByAccountId(long accountId, FilterDto filterDto, int page, int size) {
        if (filterDto.getStartDate() == null || filterDto.getStartDate().equals(""))
            filterDto.setStartDate(LocalDate.parse("1000-01-01"));

        if (filterDto.getEndDate() == null || filterDto.getEndDate().equals(""))
            filterDto.setEndDate(LocalDate.parse("9999-12-31"));
        return reportRepo.getAllByAccountId(accountId, filterDto.getStatus(), filterDto.getTitle(), filterDto.getUsername(), filterDto.getStartDate(), filterDto.getEndDate(), PageRequest.of(page, size));
    }

    @Override
    public void blockPost(Report report) {
        Post post = report.getPost();
        post.setStatus("Vô hiệu hóa");
        report.setStatus("Đã phê duyệt");
        postRepo.save(post);
        reportRepo.save(report);
    }

    @Override
    public void denyBlockPost(Report report) {
        report.setStatus("Đã hủy");
        reportRepo.save(report);
    }

    @Override
    public Report getReportByPostId(long postId) {
        return reportRepo.getReportByPostId(postId);
    }
}
