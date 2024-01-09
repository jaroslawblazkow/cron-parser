import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CronExpressionParserTest {

    @Test
    void testValidInput() {
        String[] args = {"*/15 0 1,15 * 1-5 /usr/bin/find"};
        String expected = """
                minute        0 15 30 45
                hour          0
                day of month  1 15
                month         1 2 3 4 5 6 7 8 9 10 11 12
                day of week   1 2 3 4 5
                command       /usr/bin/find""";
        assertEquals(expected, CronExpressionParser.parse(args));
    }

    @Test
    void testNoArguments() {
        String[] args = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionParser.parse(args);
        });
        assertEquals("No args provided. Please provide cron expression.", exception.getMessage());
    }

    @Test
    void testMissingDayOfWeek() {
        String[] args = {"*/15 0 1,15 *"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionParser.parse(args);
        });
        assertEquals("Invalid cron expression", exception.getMessage());
    }

    @Test
    void testMissingCommand() {
        String[] args = {"*/15 0 1,15 * 1-5"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionParser.parse(args);
        });
        assertEquals("Missing command", exception.getMessage());
    }

    @Test
    void testDisallowedCharInCronExpression() {
        String[] args = {"*/15 0 1#15 * 1-5 /usr/bin/find"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionParser.parse(args);
        });
        assertEquals("Invalid day of month field: 1#15", exception.getMessage());
    }

    @Test
    void testExceededRangeOfMonth() {
        String[] args = {"*/15 0 1,15 13 1-5 /usr/bin/find"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CronExpressionParser.parse(args);
        });
        assertEquals("Invalid month field: 13", exception.getMessage());
    }

    @Test
    void testValidCommandWithFlag() {
        String[] args = {"5 0 * 8 * /usr/bin/ls -l"};
        String expected = """
                minute        5
                hour          0
                day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
                month         8
                day of week   1 2 3 4 5 6 7
                command       /usr/bin/ls -l""";
        assertEquals(expected, CronExpressionParser.parse(args));
    }

    @Test
    @Disabled("This is not implemented yet")
    void testFebruaryHasLessDays() {
        String[] args = {"5 0 30 2 * /usr/bin/command"};
        assertThrows(IllegalArgumentException.class, () -> CronExpressionParser.parse(args));
    }
}
