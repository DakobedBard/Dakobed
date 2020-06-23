package org.mddarr.dakobedreports.reports.services;



import org.mddarr.dakobedreports.reports.dto.ReportDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

//    @Autowired
//    ReportRepository reportRepository;
//
//    public void postReport(ReportDTO report){
//        if(report.getImageurl() ==null){
//            System.out.println("Wyhere is the imageurl" );
//            report.setImageurl( "https://dakobed.s3-us-west-1.amazonaws.com/fernow.jpg");
//        }else{
//            System.out.println("Not null");
//        }
//
//        Report tripReport = new Report(report);
//        reportRepository.save(tripReport);
//    }
//
//    public List<Report> getReports(){
//        List<Report> reports = new ArrayList<>();
//        reportRepository.findAll().forEach(reports::add);
//        return reports;
//    }
//
//    public Optional<Report> getReport(String id){
//        return reportRepository.findById(id);
//    }
//
//    public ResponseEntity<Report> updateReport(String id, Report report){
//        Optional<Report> reportData = reportRepository.findById(id);
//
//        if (reportData.isPresent()) {
//            Report _report = reportData.get();
//            _report.setName(report.getName());
//            _report.setRegion(report.getRegion());
//            _report.setElevationGain(report.getElevationGain());
//            _report.setReport(report.getReport());
//            _report.setDistance(report.getDistance());
//            return new ResponseEntity<>(reportRepository.save(_report), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    public ResponseEntity<HttpStatus> deleteReport(String id) {
//        try {
//            reportRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//        }
//    }

}
