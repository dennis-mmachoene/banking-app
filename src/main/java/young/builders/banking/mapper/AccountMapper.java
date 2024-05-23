package young.builders.banking.mapper;

import young.builders.banking.dto.AccountDto;
import young.builders.banking.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getName(),
                account.getBalance()
        );
                return accountDto;
    }
}
