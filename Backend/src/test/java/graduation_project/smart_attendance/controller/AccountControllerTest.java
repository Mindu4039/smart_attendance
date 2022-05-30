package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.config.CustomAuthenticationFailureHandler;
import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.repository.AccountRepository;
import graduation_project.smart_attendance.service.AccountService;
import graduation_project.smart_attendance.validator.AccountValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AccountController.class)
@ContextConfiguration
@WithAnonymousUser
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @MockBean
    UserDetailsService userDetailsService;

    @MockBean
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    AccountValidator accountValidator;

    @Test
    void createUserForm() throws Exception {
        mvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("accountDto"))
                .andDo(print());
    }

    @Test
    void createUser() throws Exception {
        Long accountId = 1L;
        given(accountService.createAccount(any(Account.class))).willReturn(accountId);

        mvc.perform(post("/signup")
                        .param("username", "testId")
                        .param("password", "testPw")
                        .param("confirmPassword", "testPw"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

}