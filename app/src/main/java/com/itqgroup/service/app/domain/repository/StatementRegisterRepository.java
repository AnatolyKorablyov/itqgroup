package com.itqgroup.service.app.domain.repository;

import com.itqgroup.service.app.domain.model.StatementRegisterSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRegisterRepository extends JpaRepository<StatementRegisterSummary, String> {
}
