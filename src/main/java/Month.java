import java.util.regex.Pattern;

class Month implements CronField {
    private final String field;

    private Month(String field) {
        this.field = field;
    }

    public static CronField of(String cronPart) {
        return new Month(cronPart);
    }

    @Override
    public int min() {
        return 1;
    }

    @Override
    public int max() {
        return 12;
    }

    @Override
    public String name() {
        return "month";
    }

    @Override
    public String field() {
        return field;
    }

    @Override
    public Pattern pattern() {
        return Pattern.compile("(\\*|(?:[1-9]|1[012])(?:(?:-(?:[1-9]|1[012]))?|(?:,(?:[1-9]|1[012]))*))");
    }
}