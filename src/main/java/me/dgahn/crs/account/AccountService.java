package me.dgahn.crs.account;

import lombok.RequiredArgsConstructor;
import me.dgahn.crs.account.AccountDto.AccountRegisterFormDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  @Transactional
  public Long signUp(AccountRegisterFormDto formDto) {
    Account account = Account.builder()
                             .id(formDto.getId())
                             .password(formDto.getPassword())
                             .email(formDto.getEmail())
                             .name(formDto.getName())
                             .isSuperUser(false)
                             .build();
    validateDuplicateId(account);
    accountRepository.save(account);
    return account.getAccountId();
  }

  private void validateDuplicateId(final Account account) {
    Optional<Account> optionalAccount = accountRepository.findById(account.getId());
    if (optionalAccount.isPresent()) {
      throw new RuntimeException("이름 중복");
    }
  }

}
