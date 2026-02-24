package com.itqgroup.service.app.utils;

import com.itqgroup.service.api.DocumentDto;
import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {

  DocumentSummary map(DocumentDto dto);

  DocumentOutDto map(DocumentSummary dto);
}
