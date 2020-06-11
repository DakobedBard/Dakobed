package org.mddarr.dakobedreports.comments.api;


import org.mddarr.dakobedreports.comments.entity.Comment;
import org.mddarr.dakobedreports.comments.models.CommentPostRequest;
import org.mddarr.dakobedreports.comments.services.CommentsService;
import org.mddarr.dakobedreports.reports.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value="comments")
public class CommentAPI {

    @Autowired
    CommentsService commentsService;

//    @PostMapping(value = "/post/")
//    public Optional<Report> postComment(@RequestBody CommentPostRequest commentRequest){
//        return commentsService.postComment(commentRequest);
//    }

    @GetMapping()
    public List<Comment> getCommentsByReport(@RequestParam String reportID){
        return commentsService.getCommentsByID(reportID);
    }
//    @GetMapping("/{id}")
//    public Page<Comment> getComments(@PathVariable("id") Long id){
//
//    }

}
