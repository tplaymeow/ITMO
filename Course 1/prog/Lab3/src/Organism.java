public abstract class Organism {
    private String name;
    private final Place bornAt;
    private Place location;
    private Size size;
    private Organ[] bodies;

    public Organism(String name, Place bornAt, Size size, Organ[] bodies) {
        this.name = name;
        this.bornAt = bornAt;
        this.location = bornAt;
        this.size = size;
        this.bodies = bodies;
    }

    public void leaveMark() {
        this.location.setMark(new Mark(this, this.location));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getBornAt() {
        return bornAt;
    }

    public Place getLocation() {
        return location;
    }

    public void setLocation(Place location) {
        this.location = location;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Organ[] getBodies() {
        return bodies;
    }

    public void setBodies(Organ[] bodies) {
        this.bodies = bodies;
    }
}

enum Size {
    SMALL,
    MIDDLE,
    LARGE
}
