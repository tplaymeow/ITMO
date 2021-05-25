package commands.commandDescriptions;

import exceptions.BadArgumentException;
import model.Semester;

public class FilterCommandDescription extends CommandDescription {
    private final Semester semester;

    public FilterCommandDescription(String value) throws BadArgumentException {
        super("filter_by_semester_enum");
        if (!argumentsCountIsEqual(value, 1)) throw new BadArgumentException();
        try {
            semester = Semester.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new BadArgumentException("Не верный аргумент");
        }

    }

    public Semester getSemester() {
        return semester;
    }
}
