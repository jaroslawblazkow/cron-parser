import java.util.regex.Pattern;

public interface CronField {

    default String printName() {
        return String.format("%-14s", name());
    }

    default void validate() {
        if (!pattern().matcher(field()).matches()) {
            throw new IllegalArgumentException("Invalid " + name() + " field: " + field());
        }
    }

    int min();

    int max();

    String name();

    String field();

    Pattern pattern();
}