package org.mddarr.dakobedreports.reports.api;


import org.mddarr.dakobedreports.reports.dto.ReportDTO;
import org.mddarr.dakobedreports.reports.entity.Report;
import org.mddarr.dakobedreports.reports.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(value="reports")
@CrossOrigin
public class ReportAPI {

    @Autowired
    ReportService reportService;

    @PostMapping(value = "post")
    public String postReport(@RequestBody ReportDTO report){
        reportService.postReport(report);
        return "Posted Sucessfully";
    }
//
    @GetMapping("/detail/")
    public ResponseEntity<Report> getReport(@RequestParam("id") String id){
        Optional<Report> reportData = reportService.getReport(id);
        return reportData.map(tripReport -> new ResponseEntity<>(tripReport, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Report> getReport(@PathVariable("id") String id){
//        Optional<Report> reportData = reportService.getReport(id);
//        return reportData.map(tripReport -> new ResponseEntity<>(tripReport, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Report> updateTutorial(@PathVariable("id") String id, @RequestBody Report tripReport) {
//        return reportService.updateReport(id, tripReport);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
//        return reportService.deleteReport(id);
//    }

    @GetMapping(value = "")
    public List<Report> getReports(){
        for(int i =0; i < 5; i++){
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid.toString());
        }
        return reportService.getReports();
    }
}
