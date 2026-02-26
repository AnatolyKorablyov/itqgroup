package com.itqgroup.service.app.domain.repository;

import com.itqgroup.service.app.domain.model.DocumentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentSummary, String> {
    @Query(
            value = "select * from document_summary u where u.status = :#{#status} and u.author = :#{#author} and u.create_date >= :#{#start_date} and u.create_date <= :#{#end_date}", nativeQuery = true)
    List<DocumentSummary> findAllByParameters(@Param("status") String status, @Param("author") String author,
                                        @Param("start_date") Date start_date, @Param("end_date") Date end_date);
}
