package org.springpratice.controller.web.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestResponseDtoTest {

    @Test
    public void LombokTest() {
        // Given
        String name = "test";
        int amount = 1000;

        // When
        TestResponseDto testResponseDto = new TestResponseDto(name, amount);

        // Then
        assertThat(testResponseDto.getName()).isEqualTo(name);
        assertThat(testResponseDto.getAmount()).isEqualTo(amount);

        // assertThat
        // assertj라는 테스트 검증 라이브러리의 검증 메소드
        // 검증하고 싶은 대상을 메소드 인자로 받음
        // 메소드 체이닝이 지원되어 메소드를 이어 사용 가능

        // Junit과 assertj
        // CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음
        // Junit은 is()와 같이 CoreMatchers 라이브러리가 필요
        // 자동완성이 좀 더 확실하게 지원(백기선님 유튜브 참조)

    }

}
