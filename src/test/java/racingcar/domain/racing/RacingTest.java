package racingcar.domain.racing;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RacingTest {

    @AfterEach
    void tearDown() {
        Console.close();
    }


    @DisplayName("경주할 자동차의 입력값으로 빈 문자열은 들어올 수 없다.")
    @Test
    void 경주할_자동차의_입력값_검증_빈문자열_테스트() {
        //given
        Racing racing = new Racing();
        setInputText("");

        //when //then
        Assertions.assertThatThrownBy(() -> {
                    racing.doRacing();
                })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경주할 자동차의 입력값으로 경주용 차가 20개 초과되는 입력이 들어올 수 없다.")
    @Test
    void 경주할_자동차의_입력값_검증_문자열_길이_테스트() {
        //given
        Racing racing = new Racing();
        setInputText("aaaaa,aaaab,aaaac,aaaad,aaaae,"
                + "aaaaf,aaaag,aaaah,aaaai,aaaaj,"
                + "aaaak,aaaal,aaaam,aaaan,aaaao,"
                + "aaaap,aaaaq,aaaar,aaaas,aaaat,b");

        //when //then
        Assertions.assertThatThrownBy(() -> {
                    racing.doRacing();
                })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경주할 자동차의 입력값으로 영어와 구분자(,) 외의 입력값은 들어올 수 없다.")
    @Test
    void 경주할_자동차의_입력값_검증_적절하지_않은_문자_입력_테스트() {
        //given
        Racing racing = new Racing();
        setInputText("aaaa2");

        //when //then
        Assertions.assertThatThrownBy(() -> {
                    racing.doRacing();
                })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경주할 자동차의 이름은 중복될 수 없다.")
    @Test
    void 경주할_자동차의_입력값_검증_자동차_이름_중복_테스트() {
        //given
        Racing racing = new Racing();
        setInputText("aaaaa,aaaaa");

        //when //then
        Assertions.assertThatThrownBy(() -> {
                    racing.doRacing();
                })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("경주할 자동차의 이름은 5자를 넘을 수 없다.")
    @Test
    void 경주할_자동차의_입력값_검증_자동차_이름_길이_테스트() {
        //given
        Racing racing = new Racing();
        setInputText("aaaaaa");

        //when //then
        Assertions.assertThatThrownBy(() -> {
                    racing.doRacing();
                })
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("경주의 시도횟수는 1~10 이외의 값은 들어올 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "11"})
    void 경주_시도횟수_입력값_경계값_예외_테스트(String tryCnt) {
        //given
        Racing racing = new Racing();
        setInputText("aaaaa,aaaab\n" + tryCnt);

        //when //then
        Assertions.assertThatThrownBy(() -> {
                    racing.doRacing();
                })
                .isInstanceOf(IllegalArgumentException.class);
    }


    private void setInputText(String text) {
        System.setIn(new ByteArrayInputStream(text.getBytes()));
    }
}