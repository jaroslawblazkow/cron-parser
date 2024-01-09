import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class AnyValue implements Wildcard {

    @Override
    public boolean test(CronField field) {
        return field.field().equals("*");
    }

    @Override
    public String process(CronField field) {
        return IntStream.rangeClosed(field.min(), field.max())
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
