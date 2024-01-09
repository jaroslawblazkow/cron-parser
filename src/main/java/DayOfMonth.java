import java.util.regex.Pattern;

class DayOfMonth implements CronField {
    private final String field;

    private DayOfMonth(String field) {
        this.field = field;
    }

    static DayOfMonth of(String field) {
        return new DayOfMonth(field);
    }

    @Override
    public int min() {
        return 1;
    }

    @Override
    public int max() {
        return 31;
    }

    @Override
    public String name() {
        return "day of month";
    }

    @Override
    public String field() {
        return field;
    }

    @Override
    public Pattern pattern() {
        return Pattern.compile("(\\*|(?:[1-9]|[12][0-9]|3[01])(?:(?:-(?:[1-9]|[12][0-9]|3[01]))?|(?:,(?:[1-9]|[12][0-9]|3[01]))*))");
    }
}