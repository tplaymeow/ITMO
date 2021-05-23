package commands.commandDescriptions;

import exceptions.BadArgumentException;
import model.userInput.StudyGroupUserInput;

public class UpdateCommandDescription extends CommandDescription {
    private final int id;
    private StudyGroupUserInput studyGroupUserInput;

    public UpdateCommandDescription(String argument) throws BadArgumentException {
        super("update");
        if (!argumentsCountIsEqual(argument, 1)) throw new BadArgumentException();
        try {
            this.id = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new BadArgumentException("Не верный формат числа.");
        }
    }

    public void setStudyGroupUserInput(StudyGroupUserInput studyGroupUserInput) {
        this.studyGroupUserInput = studyGroupUserInput;
    }

    public StudyGroupUserInput getStudyGroupUserInput() {
        return studyGroupUserInput;
    }

    public int getId() {
        return id;
    }
}
