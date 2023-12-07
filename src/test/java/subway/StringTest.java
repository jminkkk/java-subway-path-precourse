package subway;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    void split() {
        String[] split = "( 2km / 8분 )"
                .replaceAll("[()]", "")
                .split("/");

        assertThat(split).containsExactly(" 2km ", " 8분 ");
    }
}
