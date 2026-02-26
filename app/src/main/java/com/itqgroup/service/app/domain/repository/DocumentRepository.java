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

    @Query(
            value = "select * from document_summary u where u.status = :#{#status}", nativeQuery = true)
    List<DocumentSummary> findAllByStatus(@Param("status") String status);

    @Query(
            value = "select id from document_summary u where u.status = :#{#status}", nativeQuery = true)
    List<String> findIdsByStatus(@Param("status") String status);

    @Query(
            value = "select * from document_summary u where and u.author = :#{#author}", nativeQuery = true)
    List<DocumentSummary> findAllByAuthor(@Param("author") String author);

    @Query(
            value = "select * from document_summary u where and u.create_date >= :#{#start_date} and u.create_date <= :#{#end_date}", nativeQuery = true)
    List<DocumentSummary> findAllByDates(@Param("start_date") Date start_date, @Param("end_date") Date end_date);
}
