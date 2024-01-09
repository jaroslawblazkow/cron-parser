import java.util.Arrays;

public class CronExpressionParser {

    public static void main(String[] args) {
        System.out.println(parse(args));
    }

    public static String parse(String[] args) {
        validateInput(args);
        String[] cronParts = args[0].split(" ");
        CronExpression cronExpression = CronExpression.of(cronParts);
        String command = String.join(" ", Arrays.copyOfRange(cronParts, 5, cronParts.length));
        return cronExpression.print() + String.format("%-14s", "command") + command;
    }

    private static void validateInput(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("No args provided. Please provide cron expression.");
        }
        if (args[0].split(" ").length < 5) {
            throw new IllegalArgumentException("Invalid cron expression");
        }

        if (args[0].split(" ").length == 5) {
            throw new IllegalArgumentException("Missing command");
        }
    }
}
