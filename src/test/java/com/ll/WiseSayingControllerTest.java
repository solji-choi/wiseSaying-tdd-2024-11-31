package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {
    @Test
    @DisplayName("등록 명령어를 입력하면 명언과 작가를 입력할 수 있다.")
    public void t1() {
        String output = AppTest.run("""
                등록
                """);

        assertThat(output)
                .contains("명언 : ")
                .contains("작가 : ");
    }
}
