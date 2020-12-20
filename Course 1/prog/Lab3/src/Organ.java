public class Organ {
    private final String name;
    private final boolean isRudiment;

    public Organ(String name, boolean isRudiment) {
        this.name = name;
        this.isRudiment = isRudiment;
    }

    public Organ(String name) {
        this.name = name;
        this.isRudiment = true;
    }

    public String getName() {
        return name;
    }

    public boolean isRudiment() {
        return isRudiment;
    }
}
