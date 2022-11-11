package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
class ColorsTest {
    Colors colors;

    @Test
    void colorName() {
        String expected_BLUE = "BLUE";
        String expected_RED = "RED";
        String expected_BLACK = "BLACK";
        String expected_GREEN = "GREEN";
        String expected_ORANGE = "ORANGE";
        String expected_YELLOW = "YELLOW";

        assertEquals(expected_BLUE, Colors.BLUE.color);
        assertEquals(expected_RED, Colors.RED.color);
        assertEquals(expected_BLACK, Colors.BLACK.color);
        assertEquals(expected_GREEN, Colors.GREEN.color);
        assertEquals(expected_ORANGE, Colors.ORANGE.color);
        assertEquals(expected_YELLOW, Colors.YELLOW.color);
    }

    @Test
    void getColorByNumber() {
        String expectedResult = "BLUE";

        String result = Colors.getColorByNumber(0).color;

        assertEquals(expectedResult, result);
    }

    @Test
    void getColorByNumber2() {
        assertNull(Colors.getColorByNumber(15));
    }

    @Test
    void getColorNum() {
        int expectedResult = 0;

        int result = Colors.BLUE.getColorNum();

        assertEquals(expectedResult, result);
    }

    @Test
    void getColorNum2() {
        int expectedResult = 10;

        int result = Colors.BLUE.getColorNum();

        assertNotEquals(expectedResult, result);
    }

    @Test
    void getColorNum3() {
        int expectedResult = 1;

        int result = Colors.RED.getColorNum();

        assertEquals(expectedResult, result);
    }

    @Test
    void getNumberOfColors() {
        int expectedResult = 6;

        int result = Colors.getNumberOfColors();

        assertEquals(expectedResult, result);
    }

    @Test
    void getNumberOfColor2() {
        int expectedResult = 100;

        int result = Colors.getNumberOfColors();

        assertNotEquals(expectedResult, result);
    }

    @Test
    void testToString() {
        String expectedResult = "BLUE";

        String result = Colors.BLUE.toString();

        assertEquals(expectedResult, result);
    }

}