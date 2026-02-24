package com.itqgroup.service.app.application;

import com.itqgroup.service.api.ApprovedStatus;
import com.itqgroup.service.api.StatusTransferParameters;
import com.itqgroup.service.api.SubmittedStatus;
import com.itqgroup.service.api.DocumentDto;
import jakarta.persistence.EntityExistsException;

import java.util.Map;


public interface DocumentApplicationService {

    String create(DocumentDto dto) throws EntityExistsException;

    Map<String, SubmittedStatus> submitted(StatusTransferParameters statusTransferParameters);

    Map<String, ApprovedStatus> approved(StatusTransferParameters statusTransferParameters);
}
