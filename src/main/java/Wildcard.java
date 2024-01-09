sealed interface Wildcard permits AnyValue, Step, Range, ListValues {

    boolean test(CronField field);

    String process(CronField field);

}
