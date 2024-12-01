package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    public static String run(String input) {
        input = input.stripIndent().trim() + "\n종료";

        Scanner scanner = TestUtil.genScanner(input);

        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();

        App app = new App(scanner);
        app.run();

        scanner.close();

        String output = outputStream.toString();

        TestUtil.clearSetOutToByteArray(outputStream);

        return output;
    }

    @Test
    @DisplayName("== 명언 앱 ==")
    public void t1() {
        String output = AppTest.run("");

        assertThat(output).contains("== 명언 앱 ==");
    }

    @Test
    @DisplayName("명령) ")
    public void t2() {
        String output = AppTest.run("");

        assertThat(output)
                .contains("== 명언 앱 ==")
                .contains("명령) ");
    }

    @Test
    @DisplayName("종료 명령어 입력 시 프로그램이 종료된다.")
    public void t3() {
        String output = AppTest.run("""
                
                """);

        assertThat(output)
                .contains("== 명언 앱 ==")
                .contains("명령) ")
                .contains("프로그램을 종료합니다.");
    }

}
