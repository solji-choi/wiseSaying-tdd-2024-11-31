package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    public static String run(String input) {
        input = input.stripIndent().trim();

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

}
