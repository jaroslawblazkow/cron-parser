public final class ListValues implements Wildcard {
    @Override
    public boolean test(CronField field) {
        return field.field().contains(",");
    }

    @Override
    public String process(CronField field) {
        return String.join(" ", field.field().split(","));
    }
}
