package net.aymane.operationservice.mapper;

import net.aymane.operationservice.dto.OperationRequestDto;
import net.aymane.operationservice.dto.OperationResponseDto;
import net.aymane.operationservice.entities.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {
    public OperationResponseDto fromOpToOpResp (Operation operation){
        OperationResponseDto operationResponseDto = new OperationResponseDto();
        BeanUtils.copyProperties(operation,operationResponseDto);
      return operationResponseDto;
    };

    public Operation opReqtoOperation (OperationRequestDto requestDto){
        Operation operation = new Operation();
        BeanUtils.copyProperties(requestDto,operation);
        return operation; };
}
