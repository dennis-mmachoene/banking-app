package young.builders.banking.service.impl;

import org.springframework.stereotype.Service;
import young.builders.banking.dto.AccountDto;
import young.builders.banking.entity.Account;
import young.builders.banking.mapper.AccountMapper;
import young.builders.banking.repository.AccountRepository;
import young.builders.banking.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }
        double new_balance = account.getBalance() - amount;
        account.setBalance(new_balance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect((Collectors.toList()));
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
     accountRepository.deleteById(id);
    }

    private final AccountRepository accountRepository;



    public AccountServiceImpl(AccountRepository accountRepository){

        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        double new_balance = account.getBalance() + amount;
        account.setBalance(new_balance);

        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }


}
