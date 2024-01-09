import java.util.regex.Pattern;

class DayOfWeek implements CronField {
    private final String field;

    private DayOfWeek(String field) {
        this.field = field;
    }

    static DayOfWeek of(String field) {
        return new DayOfWeek(field);
    }

    @Override
    public int min() {
        return 1;
    }

    @Override
    public int max() {
        return 7;
    }

    @Override
    public String name() {
        return "day of week";
    }

    @Override
    public String field() {
        return field;
    }

    @Override
    public Pattern pattern() {
        return Pattern.compile("(\\*|(?:[0-6]|SUN|MON|TUE|WED|THU|FRI|SAT)(?:(?:-(?:[0-6]|SUN|MON|TUE|WED|THU|FRI|SAT))?|(?:,(?:[0-6]|SUN|MON|TUE|WED|THU|FRI|SAT))*))");
    }

}