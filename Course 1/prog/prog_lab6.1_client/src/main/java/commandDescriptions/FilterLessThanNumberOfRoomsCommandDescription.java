package commandDescriptions;

import support.CommandName;

public class FilterLessThanNumberOfRoomsCommandDescription extends CommandDescription {
    private final int numberOfRooms;

    public FilterLessThanNumberOfRoomsCommandDescription(int numberOfRooms) {
        super(CommandName.FILTERLESSTHANNUMBEROFROOMS);
        this.numberOfRooms = numberOfRooms;
    }
}
