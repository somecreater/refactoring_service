package org.webservice.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.webservice.config.SecurityProperty;
import org.webservice.entity.MemberEntity;
import org.webservice.repository.MemberRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@Slf4j
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SecurityProperty securityProperty;
    @Autowired
    private PasswordEncoder encoder;

    //@Test
    @DisplayName("permitall URL 테스트")
    public void testpermitall() throws Exception {

        for(String url: securityProperty.getUrl().getPermitAll()){
            //mockMvc.perform(get(url)).andExpect(status().isOk());
            System.out.println(url);
        }
    }
    //@Test
    @DisplayName("회원가입 테스트")
    public  void testregister(){
        MemberEntity member=new MemberEntity();
        String testid="tester";
        String orgtestpass="0000";
        String enctestpass=encoder.encode(orgtestpass);
        String testname="테스터";
        String phone=null;
        boolean testenable=true;

        member.setUserid(testid);
        member.setUserpw(enctestpass);
        member.setUsername(testname);
        member.setPhone(phone);
        member.setEnabled(testenable);

        memberRepository.save(member);
    }

    @Test
    @DisplayName("로그인 테스트")
    public void testloginaction() throws Exception {
        String encodepass=encoder.encode("0000");
        mockMvc.perform(post(securityProperty.getUrl().getLoginAction())
                .param(securityProperty.getUsername(), "tester")
                .param(securityProperty.getPassword(), "0000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(securityProperty.getUrl().getLoginSuccess()));
    }

}
