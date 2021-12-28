package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.config.CustomAuthenticationFailureHandler;
import graduation_project.smart_attendance.repository.AccountRepository;
import graduation_project.smart_attendance.service.AccountService;
import graduation_project.smart_attendance.service.AccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
@ContextConfiguration
@WithAnonymousUser
@Slf4j
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

        mvc.perform(post("/signup"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

}