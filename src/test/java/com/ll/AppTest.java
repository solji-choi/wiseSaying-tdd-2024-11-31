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

    @Test
    @DisplayName("없는 명령어가 입력될 경우 계속 명령어를 입력할 수 있도록 한다.")
    public void t4() {
        String output = AppTest.run("""
                등록
                등록
                """);

        String[] outputBits = output.split("명령\\)");

        assertThat(outputBits).hasSize(4);
    }

}
