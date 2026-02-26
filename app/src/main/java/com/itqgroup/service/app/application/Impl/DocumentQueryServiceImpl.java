package com.itqgroup.service.app.application.Impl;

import com.itqgroup.service.api.DocumentOutDto;
import com.itqgroup.service.api.DocumentsParameters;
import com.itqgroup.service.api.HistoryOutDto;
import com.itqgroup.service.app.application.DocumentQueryService;
import com.itqgroup.service.app.domain.model.HistorySummary;
import com.itqgroup.service.app.domain.repository.Pagination;
import com.itqgroup.service.app.domain.service.DocumentDomainService;
import com.itqgroup.service.app.domain.service.HistoryDomainService;
import com.itqgroup.service.app.utils.DocumentMapper;
import com.itqgroup.service.app.domain.model.DocumentSummary;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DocumentQueryServiceImpl implements DocumentQueryService {

    @Value("${application.limit.pageSize}")
    private int DEFAULT_PAGE_SIZE;

    private final DocumentDomainService documentDomainService;
    private final HistoryDomainService historyDomainService;
    private final DocumentMapper mapper = Mappers.getMapper(DocumentMapper.class);

    private static String ASCENDING = "asc";

    @Override
    public List<DocumentSummary> getDocumentsByFilter(DocumentsParameters parameters) {

        return documentDomainService.getByParameters(parameters);
    }

    @Override
    public List<DocumentSummary> getDocuments(Optional<List<String>> ids,
                                              Optional<Integer> offset,
                                              Optional<Integer> limit,
                                              Optional<String> sortOrder) {
        if (ids.isEmpty()) {
            int pageSize = limit.orElse(DEFAULT_PAGE_SIZE);
            int postOffset = offset.orElse(0);
            String sort = sortOrder.orElse(ASCENDING);
            List<DocumentSummary> documentSummaries = documentDomainService.findAllByPagination(
                    new Pagination(pageSize, postOffset, JpaSort.unsafe(sort, "create_date")));
            return documentSummaries;
        }
        else {
            return documentDomainService.getByIds(ids.get());
        }
    }

    @Override
    public DocumentOutDto getDocumentById(String documentId) {
        try {
            DocumentOutDto documentOutDto = mapper.map(documentDomainService.getById(documentId));
            List<HistorySummary> historySummaries = historyDomainService.getByDocId(documentId);
            List<HistoryOutDto> historyOutDtoList = historySummaries.stream()
                    .map(s -> {
                        HistoryOutDto outDto = mapper.map(s);
                        return outDto;})
                    .collect(Collectors.toList());
            documentOutDto.setHistory(historyOutDtoList);
            return documentOutDto;
        } catch (EntityNotFoundException ex) {
            log.info("document by id not found");
            throw ex;
        }
    }

    @Override
    public List<String> getIdsByStatus(String status) {
        try {
            return documentDomainService.getIdsByStatus(status);
        } catch (EntityNotFoundException ex) {
            log.info("document by status not found");
            throw ex;
        }
    }
}
