import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Range implements Wildcard {
    @Override
    public boolean test(CronField field) {
        return field.field().contains("-");
    }

    @Override
    public String process(CronField field) {
        String[] parts = field.field().split("-");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);
        return range(start, end);
    }

    private String range(int min, int max) {
        return IntStream.rangeClosed(min, max)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }

}
