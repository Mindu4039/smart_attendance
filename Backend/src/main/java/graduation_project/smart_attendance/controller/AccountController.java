package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.dto.AccountDto;
import graduation_project.smart_attendance.service.AccountService;
import graduation_project.smart_attendance.service.AccountValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;
    private final AccountValidator accountValidator;

    @GetMapping("/signup")
    public String createAccountForm(Model model){
        model.addAttribute("accountDto", new AccountDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String createAccount(@Valid AccountDto form, BindingResult result){
        accountValidator.validate(form, result);
        if(result.hasErrors()){
            return "signup";
        }
        accountService.createAccount(form.toEntity());

        return "redirect:/";
    }
}
