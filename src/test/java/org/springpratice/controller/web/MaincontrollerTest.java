package org.springpratice.controller.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
// 테스트를 진행할 때 Junit에 내장된 실행자 외에 다른 실행자를 실행시킴
// 스프링 부트 테스트와 Junit 사이에 연결자 역할 수행

@WebMvcTest(controllers = Maincontroller.class)
// @Service, @Component, @Repository 사용 불가
// Web(Spring MVC)에 집중 가능하여 테스트 가능한 어노테이션
public class MaincontrollerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받음
    private MockMvc mvc; //웹 API를 사용시 사용, MVC 테스트의 시작점

    @Test
    public void printTest() throws Exception {
        String test = "test";

        mvc.perform(get("/test"))
                // MockMvc를 통해 /test 주소로 HTTP GET 요청을 함
                .andExpect(status().isOk())
                // 테스트의 결과를 검증 ok --> 200인지 아닌지를 검증
                .andExpect(content().string(test));
                // 응답 본문의 내용을 검증
    }

    @Test
    public void ReturnTestDto() throws Exception {
        String test = "test";
        int amount = 1000;

        mvc.perform(
                get("/test/dto")
                        .param("name", test)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(test)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    // param : API 테스트할 때 사용될 요청 파라미터를 설정
    // -----> 값은 String만 허용(숫자나, 날짜 등의 데이터 등록 시에는 문자열로 변경 필요)
    // jsonPath : JSON 응답값을 필드별로 검증 가능한 메소드
    // $를 기준으로 필드명을 명시
}
