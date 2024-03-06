package net.aymane.operationservice.dto;

import lombok.Getter;
import lombok.Setter;
import net.aymane.operationservice.enums.OperationType;
import java.time.LocalDate;

@Getter
@Setter
public class OperationResponseDto {
    private LocalDate date;
    private String amount;
    private OperationType type;
}
