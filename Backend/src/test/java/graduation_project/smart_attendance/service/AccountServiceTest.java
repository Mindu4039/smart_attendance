package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.dto.AccountDto;
import graduation_project.smart_attendance.dto.FindAccountDto;
import graduation_project.smart_attendance.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@Transactional
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void beforeEach() {
        Account account = AccountDto.builder()
                .username("test")
                .password("test")
                .confirmPassword("test")
                .name("test")
                .email("test@test.com")
                .build().toEntity();
        account.setId(1L);
        accountRepository.save(account);
    }

    @Test
    @DisplayName("유저 만들기")
    void createUser() {

        Account account = AccountDto.builder()
                .username("id")
                .password("pw")
                .confirmPassword("pw")
                .name("name")
                .email("email@e.com")
                .build().toEntity();
        account.setId(2L);
        Long accountId = accountService.createAccount(account);
        assertThat(accountId == 2L);
    }

    @Test
    @DisplayName("유저 찾기")
    void findUser() {
        String result = accountService.findUser(FindAccountDto.builder()
                .username("test")
                .password("test")
                .build());
        Assertions.assertThat(result.matches("[0-9]+"));
    }

    @Test
    @DisplayName("유저 찾기 실패")
    void findUserFailed() {
        String result = accountService.findUser(FindAccountDto.builder()
                .username("notest")
                .password("test")
                .build());
        Assertions.assertThat(result.matches("False"));
    }

    @Test
    void currentAccount() {
    }
}