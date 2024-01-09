import java.util.regex.Pattern;

class Hour implements CronField {
    private final String field;

    private Hour(String field) {
        this.field = field;
    }

    static Hour of(String field) {
        return new Hour(field);
    }

    @Override
    public int min() {
        return 0;
    }

    @Override
    public int max() {
        return 23;
    }

    @Override
    public String name() {
        return "hour";
    }

    @Override
    public String field() {
        return field;
    }

    @Override
    public Pattern pattern() {
        return Pattern.compile("(\\*|(?:\\*|(?:\\*|(?:[0-9]|1[0-9]|2[0-3])))/(?:[0-9]|1[0-9]|2[0-3])|(?:[0-9]|1[0-9]|2[0-3])(?:(?:-(?:[0-9]|1[0-9]|2[0-3]))?|(?:,(?:[0-9]|1[0-9]|2[0-3]))*))");
    }
}