package com.itqgroup.service.app.domain.repository;

import com.itqgroup.service.app.domain.model.HistorySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistorySummary, String> {
    @Query(
            value = "select * from history_summary u where u.id_document = :#{#id_document}", nativeQuery = true)
    List<HistorySummary> findAllByDocId(@Param("id_document") String id_document);
}