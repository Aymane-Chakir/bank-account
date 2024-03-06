package net.aymane.operationservice.service;

import net.aymane.operationservice.dto.OperationRequestDto;
import org.springframework.web.bind.annotation.RequestParam;

public interface OperationService {
     void createOperation(@RequestParam OperationRequestDto operationRequestDto);

}
