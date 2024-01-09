import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CronExpression {

    private final List<CronField> fields;

    private CronExpression(List<CronField> fields) {
        this.fields = fields;
    }

    String print() {
        StringBuilder sb = new StringBuilder();
        for (CronField field : fields) {
            sb.append(field.printName());
            sb.append(expand(field));
            sb.append("\n");
        }
        return sb.toString();
    }

    private String expand(CronField field) {
        Set<Wildcard> allWildcards = Set.of(new AnyValue(), new ListValues(), new Step(), new Range());
        return allWildcards.stream().filter(it -> it.test(field)).findFirst()
                .map(it -> it.process(field)).orElse(field.field());
    }

    static CronExpression of(String[] cronParts) {
        ArrayList<CronField> fields = new ArrayList<>();
        fields.add(Minute.of(cronParts[0]));
        fields.add(Hour.of(cronParts[1]));
        fields.add(DayOfMonth.of(cronParts[2]));
        fields.add(Month.of(cronParts[3]));
        fields.add(DayOfWeek.of(cronParts[4]));
        fields.forEach(CronField::validate);
        return new CronExpression(fields);
    }
}
