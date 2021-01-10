import java.util.Objects;

public class Human extends Organism{

    private final int intellectLevel;

    public Human(String name, Place bornAt, int intellectLevel) {
        super(name,
                bornAt,
                new Characteristic(Size.SMALL, 70, true, Shape.OTHER_SYMMETRICAL),
                5,
                BiologyKingdom.ANIMALIA,
                new Organ[]{new Heart("Сердце", OrganType.IN_USAGE, 10000, .95)});
        this.intellectLevel = intellectLevel;
    }

    @Override
    public void moveTo(Place location) {
        this.setLocation(location);
    }

    public void thinkAbout(Think think) {
        if (intellectLevel >= think.getIntellectFloor()) {
            System.out.println(this.getName() + " подумал о " + think);
        } else {
            System.out.println("Это невозможно уразуметь!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Human human = (Human) o;
        return intellectLevel == human.intellectLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), intellectLevel);
    }
}
