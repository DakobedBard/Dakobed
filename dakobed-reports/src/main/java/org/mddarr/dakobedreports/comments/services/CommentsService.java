package org.mddarr.dakobedreports.comments.services;


import org.mddarr.dakobedreports.comments.dao.CommentRepository;
import org.mddarr.dakobedreports.comments.entity.Comment;
import org.mddarr.dakobedreports.comments.models.CommentPostRequest;
import org.mddarr.dakobedreports.reports.dao.ReportRepository;
import org.mddarr.dakobedreports.reports.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ReportRepository reportRepository;

    public List<Comment> getCommentsByID(String reportID){

        Optional<Report> report = reportRepository.findById(reportID);
        if (report.isPresent()) {
            return commentRepository.findByReport(report.get());
        }
        System.out.println("YOU failed");
        List<Comment> comments = new ArrayList<>();
        return comments;
    }
//
//    public Optional<Report> postComment(CommentPostRequest commentPostRequest){
//
//        Optional<Report> report = reportRepository.findById(commentPostRequest.getEntityID());
//        if (report.isPresent()) {
//            report.get().getComments().add(new Comment(commentPostRequest.getName(), commentPostRequest.getEmail(), commentPostRequest.getComment(),report.get(),commentPostRequest.getDate()));
//            reportRepository.save(report.get());
//        }
//        return report;
//
//    }
//

}
