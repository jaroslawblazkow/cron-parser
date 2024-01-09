import java.util.stream.IntStream;

public final class Step implements Wildcard {
    @Override
    public boolean test(CronField field) {
        return field.field().contains("/");
    }

    @Override
    public String process(CronField field) {
        String[] parts = field.field().split("/");
        int step = Integer.parseInt(parts[1]);
        return String.join(" ", IntStream.rangeClosed(field.min(), field.max()).filter(i -> i % step == 0).mapToObj(String::valueOf).toArray(String[]::new));
    }
}
