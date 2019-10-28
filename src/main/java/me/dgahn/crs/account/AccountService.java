package me.dgahn.crs.account;

import lombok.RequiredArgsConstructor;
import me.dgahn.crs.account.AccountDto.AccountRegisterFormDto;
import me.dgahn.crs.config.exception.RequestErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;
  private final BCryptPasswordEncoder encoder;

  @Transactional
  public Long join(AccountRegisterFormDto formDto) {
    validateDuplicateId(formDto.getId());
    formDto.setPassword(encoder.encode(formDto.getPassword()));
    Account account = Account.createSaveAccount(formDto);
    accountRepository.save(account);
    return account.getAccountId();
  }

  public Account findByAccountId(Long accountId) {
    return accountRepository.findByAccountId(accountId);
  }

  public Account findByName(String id) {
    Optional<Account> optionalAccount = accountRepository.findById(id);
    if (optionalAccount.isEmpty()) {
      throw new RequestErrorException(HttpStatus.NO_CONTENT.value(), "사용자 아이디가 존재하지 않습니다.");
    }

    return optionalAccount.get();
  }

  private void validateDuplicateId(final String id) {
    Optional<Account> optionalAccount = accountRepository.findById(id);
    if (optionalAccount.isPresent()) {
      throw new RequestErrorException(HttpStatus.CONFLICT.value(), "사용자 아이디 중복됬습니다.");
    }
  }

  public List<Account> findAll() {
    Optional<List<Account>> optionalAccounts = accountRepository.findAll();
    return optionalAccounts.isEmpty() ? new ArrayList<>() : optionalAccounts.get();
  }

}
