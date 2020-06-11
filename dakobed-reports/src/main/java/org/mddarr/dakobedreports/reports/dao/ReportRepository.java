package org.mddarr.dakobedreports.reports.dao;


import org.mddarr.dakobedreports.reports.entity.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepository extends CrudRepository<Report, String> {


}