package org.mddarr.dakobedreports.api;


import org.mddarr.dakobedreports.reports.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportsController {

    @Autowired
    ReportService reportService;




}
