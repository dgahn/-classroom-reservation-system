package me.dgahn.crs.account;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dgahn.crs.account.AccountDto.AccountLogInFormDto;
import me.dgahn.crs.account.AccountDto.AccountRegisterFormDto;
import me.dgahn.crs.account.AccountDto.AccountSecurityDto;
import me.dgahn.crs.config.exception.RequestErrorException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/accounts/id/search")
  public String searchId() {
    return "accounts/searchId";
  }

  @GetMapping("/accounts/password/new")
  public String newPassword() {
    return "accounts/newPassword";
  }

  @GetMapping("/accounts/join")
  public String joinForm(Model model) {
    AccountRegisterFormDto formDto = new AccountRegisterFormDto();
    model.addAttribute("form", formDto);
    return "accounts/join";
  }

  @GetMapping("/login-error")
  public String loginError(Model model, AccountLogInFormDto formDto) {
    formDto.setMessage("아이디나 비밀번호가 틀렸습니다.");
    model.addAttribute("form", formDto);
    return "login";
  }

  @PostMapping("/accounts/join")
  public String join(@ModelAttribute("form") AccountRegisterFormDto form) {
    accountService.join(form);
    return "redirect:/";
  }

  @GetMapping("/")
  public String loginForm(Model model, @AuthenticationPrincipal AccountSecurityDto dto) {
    if (dto != null) {
      model.addAttribute("name", dto.getUsername());
    }

    AccountLogInFormDto formDto = new AccountLogInFormDto();
    model.addAttribute("form", formDto);

    return "login";
  }

}
