package get.to.the.point.pilot01.controller;

import get.to.the.point.pilot01.Service.MemberService;
import get.to.the.point.pilot01.dto.ResponseGetMember;
import get.to.the.point.pilot01.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private Member testMember;
    private ResponseGetMember testResponseGetMember;

    @BeforeEach
    void setUp() {
        testMember = new Member(1L, "test@example.com", "Test User", "password123");
        testResponseGetMember = new ResponseGetMember(1L, "test@example.com", "Test User");
    }

    @Test
    void registerMemberTest() throws Exception {
        doNothing().when(memberService).registerMember(Mockito.any(Member.class));

        mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"email\":\"test@example.com\"," +
                                "\"name\":\"Test User\"," +
                                "\"password\":\"password123\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("회원 가입 성공"));
    }

    @Test
    void getMemberByIdTest() throws Exception {
        given(memberService.getMemberById(1L)).willReturn(Optional.of(testResponseGetMember));

        mockMvc.perform(get("/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("test@example.com")))
                .andExpect(jsonPath("$.name", is("Test User")));
    }

    @Test
    void getAllMembersTest() throws Exception {
        given(memberService.getAllMembers()).willReturn(List.of(testMember));

        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is("test@example.com")));
    }

    @Test
    void deleteMemberByIdTest() throws Exception {
        doNothing().when(memberService).deleteMemberById(1L);

        mockMvc.perform(delete("/members/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 삭제 성공"));
    }

    @Test
    void deleteMemberByEmailTest() throws Exception {
        doNothing().when(memberService).deleteMemberByEmail("test@example.com");

        mockMvc.perform(delete("/members/email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 삭제 성공"));
    }

    @Test
    void updateMemberTest() throws Exception {
        doNothing().when(memberService).updateMember(Mockito.any(Member.class));

        mockMvc.perform(put("/members/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"email\":\"updated@example.com\"," +
                                "\"name\":\"Updated User\"," +
                                "\"password\":\"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 수정 성공"));
    }
}
