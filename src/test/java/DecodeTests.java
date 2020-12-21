import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecodeTests {
    @Test
    void whenEmpty_thenNFException() {
        try {
            Integer.decode("");
            fail("Expected NFE");

        } catch (NumberFormatException e) {
            assertEquals("Zero length string", e.getMessage(), "Expected 'Zero length string'");
        }
    }

    @Test
    void whenMinus_thenNegative() {
        int result = Integer.decode("-1");
        if (result > 0) {
            fail("Expected negative");
        }
    }

    @Test
    void whenPlus_thenPositive() {
        int result = Integer.decode("+1");
        if (result < 0) {
            fail("Expected positive");
        }
    }

    @Test
    void whenTwoMinus_thenNFE() {
        try {
            Integer.decode("--1");
            fail("Expected NFE");

        } catch (NumberFormatException e) {
            assertEquals(
                    "Sign character in wrong position",
                    e.getMessage(), "Expected 'Sign character in wrong position'");
        }
    }

    @Test
    void whenTwoPlus_thenNFE() {
        try {
            Integer.decode("++1");
            fail("Expected NFE");

        } catch (NumberFormatException e) {
            assertEquals(
                    "Sign character in wrong position",
                    e.getMessage(), "Expected 'Sign character in wrong position'");
        }
    }

    @Test
    void whenNotNumber_thenNFE() {
        try {
            Integer.decode("sorok_dva");
            fail("Expected NFE");
        } catch (NumberFormatException ignored) { }
    }

    @Test
    void whenMinValue_thenCorrect() {
        int result = Integer.decode(String.valueOf(Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    void whenMaxValue_thenCorrect() {
        int result = Integer.decode(String.valueOf(Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    void whenHex_thenCorrect() {
        int result = Integer.decode("0xFE");
        assertEquals(254, result);
        result = Integer.decode("#FE");
        assertEquals(254, result);
    }

    @Test
    void whenOct_thenCorrect() {
        int result = Integer.decode("0276");
        assertEquals(190, result);
    }
}