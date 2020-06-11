package org.mddarr.dakobedreports.comments.dao;

import org.mddarr.dakobedreports.comments.entity.Comment;

import org.mddarr.dakobedreports.reports.entity.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {

    List<Comment> findByReport(Report report);

//    List<Comment> findByReport(Report report, Sort sort);
//    List<Comment> findByEntity(Long entity, Pageable pageable);

//    @Query("FROM Comment")
//    List<Comment> commentList();
//
//    @Query("FROM Comment where entity = :entity")
//    List<Comment> findByEntity(@Param("entity") Long entity);

}