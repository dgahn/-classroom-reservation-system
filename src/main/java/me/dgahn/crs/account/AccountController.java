package me.dgahn.crs.account;


import lombok.RequiredArgsConstructor;
import me.dgahn.crs.account.AccountDto.AccountRegisterFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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

  @GetMapping("/accounts/signUp")
  public String signUp(Model model) {
    AccountRegisterFormDto formDto = new AccountRegisterFormDto();
    model.addAttribute("form", formDto);
    return "accounts/signUp";
  }

  @PostMapping("/accounts/signUp")
  public String signUp(@ModelAttribute("form") AccountRegisterFormDto form) {
    accountService.signUp(form);
    return "redirect:/";
  }

}
