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
                현재를 사랑하라.
                작자미상
                """);

        assertThat(output)
                .contains("명언 : ")
                .contains("작가 : ");
    }

    @Test
    @DisplayName("등록시 생성된 명언번호 노출")
    public void t2() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(output)
                .contains("명언 : ")
                .contains("작가 : ")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록할때 마다 생성되는 명언번호가 증가")
    public void t3() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(output)
                .contains("명언 : ")
                .contains("작가 : ")
                .contains("1번 명언이 등록되었습니다.")
                .contains("명언 : ")
                .contains("작가 : ")
                .contains("2번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록 기능 추가")
    public void t4() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(output)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("삭제 기능 추가")
    public void t5() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                """);

        assertThat(output)
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("정상적이지 않은 id값이 들어올 경우 예외 처리")
    public void t6() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=일
                """);

        assertThat(output)
                .contains("삭제할 id를 입력해주세요(숫자)");
    }

    @Test
    @DisplayName("존재하지 않는 명언삭제에 대한 예외처리")
    public void t7() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                삭제?id=1
                """);

        assertThat(output)
                .contains("1번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("수정 명령어를 입력하면 기존 명언이 표출되며 수정할 명언과 작가를 다시 입력한다.")
    public void t8() {
        String output = AppTest.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                삭제?id=1
                수정?id=2
                현재와 자신을 사랑하라.
                홍길동
                목록
                """);

        assertThat(output)
                .contains("명언(기존) : 과거에 집착하지 마라.")
                .contains("작가(기존) : 작자미상");
    }
}
