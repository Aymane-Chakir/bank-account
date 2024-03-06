package net.aymane.operationservice.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aymane.operationservice.dto.OperationRequestDto;
import net.aymane.operationservice.mapper.OperationMapper;
import net.aymane.operationservice.repository.OperationRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Override
    public void createOperation(OperationRequestDto operationRequestDto) {
        OperationRequestDto operation = new OperationRequestDto();
        operationRepository.save(operationMapper.opReqtoOperation(operation));
    }
}
