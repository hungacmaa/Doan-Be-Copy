package com.be_project.service;

import com.be_project.entity.Report;
import com.be_project.entity.dto.FilterDto;
import org.springframework.data.domain.Page;

public interface IReportService {
    Report saveReport(Report report);
    Page<Report> getAllReports(FilterDto filterDto, int page, int size);
    Page<Report> getAllReportsByAccountId(long accountId, FilterDto filterDto, int page, int size);
    void blockPost(Report report);
    void denyBlockPost(Report report);
    Report getReportByPostId(long postId);
}
