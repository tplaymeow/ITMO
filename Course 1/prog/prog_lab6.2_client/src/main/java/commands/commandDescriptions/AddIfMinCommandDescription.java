package commands.commandDescriptions;

import exceptions.BadArgumentException;
import model.userInput.StudyGroupUserInput;

public class AddIfMinCommandDescription extends CommandDescription {
    private StudyGroupUserInput studyGroupUserInput;

    public AddIfMinCommandDescription(String arguments) throws BadArgumentException {
        super("add_if_min");
        if (!argumentsCountIsEqual(arguments, 0)) throw new BadArgumentException();
    }

    public void setStudyGroupUserInput(StudyGroupUserInput studyGroupUserInput) {
        this.studyGroupUserInput = studyGroupUserInput;
    }

    public StudyGroupUserInput getStudyGroupUserInput() {
        return studyGroupUserInput;
    }
}

