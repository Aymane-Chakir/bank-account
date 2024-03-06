package net.aymane.accountservice.Mapper;

import net.aymane.accountservice.dto.BankAccountRequest;
import net.aymane.accountservice.dto.BankAccountResponse;
import net.aymane.accountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BankAccountMapper {
    public BankAccountResponse fromBankAccountToResp(BankAccount bankAccount){
        BankAccountResponse bankAccountResponse = new BankAccountResponse();
        if (!Objects.isNull(bankAccount)){
        BeanUtils.copyProperties(bankAccount,bankAccountResponse);
        }
        return bankAccountResponse;
    };

    public BankAccount fromReqToBankAccount(BankAccountRequest bankAccountRequest){
        BankAccount bankAccount=new BankAccount();
        if (!Objects.isNull(bankAccountRequest)){
        BeanUtils.copyProperties(bankAccountRequest,bankAccount);
    }
        return bankAccount;
    };

}
