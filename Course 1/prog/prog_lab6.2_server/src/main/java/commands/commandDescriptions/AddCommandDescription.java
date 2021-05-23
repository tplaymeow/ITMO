package commands.commandDescriptions;

import exceptions.BadArgumentException;
import model.userInput.StudyGroupUserInput;

public class AddCommandDescription extends CommandDescription {
    private StudyGroupUserInput studyGroupUserInput;

    public AddCommandDescription(String arguments) throws BadArgumentException {
        super("add");
        if (!argumentsCountIsEqual(arguments, 0)) throw new BadArgumentException();
    }

    public void setStudyGroupUserInput(StudyGroupUserInput studyGroupUserInput) {
        this.studyGroupUserInput = studyGroupUserInput;
    }

    public StudyGroupUserInput getStudyGroupUserInput() {
        return studyGroupUserInput;
    }
}

