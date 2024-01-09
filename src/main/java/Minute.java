import java.util.regex.Pattern;

class Minute implements CronField {
    private final String field;

    private Minute(String field) {
        this.field = field;
    }

    static Minute of(String field) {
        return new Minute(field);
    }

    @Override
    public int min() {
        return 0;
    }

    @Override
    public int max() {
        return 59;
    }

    @Override
    public String name() {
        return "minute";
    }

    @Override
    public String field() {
        return field;
    }

    @Override
    public Pattern pattern() {
        return Pattern.compile("(\\*|(?:\\*|(?:[0-9]|[1-5][0-9]))/(?:[0-9]|[1-5][0-9])|(?:[0-9]|[1-5][0-9])(?:(?:-[0-9]|-[1-5][0-9])?|(?:,(?:[0-9]|[1-5][0-9]))*))");
    }
}