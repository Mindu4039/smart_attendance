package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.service.AccountService;
import graduation_project.smart_attendance.service.CourseService;
import graduation_project.smart_attendance.service.MemberService;
import graduation_project.smart_attendance.validator.MemberValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser
@WebMvcTest(MemberController.class)
@ExtendWith(SpringExtension.class)
@Slf4j
class MemberControllerTest {

    @MockBean
    MemberService memberService;

    @MockBean
    MemberValidator memberValidator;

    @MockBean
    AccountService accountService;

    @MockBean
    CourseService courseService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("멤버 조회 테스트")
    void memberList() {
    }

    @Test
    void addMemberForm() {
    }

    @Test
    void addMember() {
    }

    @Test
    void deleteMember() {
    }

    @Test
    void close() {
    }

    @Test
    void updateMember() {
    }

    @Test
    void editMember() {
    }
}