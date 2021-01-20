import java.util.ArrayList;
import java.util.Objects;

public class Human extends Organism implements Thinkable{

    private final int intellectLevel;
    private ArrayList<Think> thinks;

    public Human(String name, Place bornAt, int intellectLevel) {
        super(name,
                bornAt,
                new Characteristic(Size.SMALL, 70, true, Shape.OTHER_SYMMETRICAL),
                5,
                new BiologicalAffiliation(BiologyKingdom.ANIMALIA, 1),
                new Organ[]{new Heart("Сердце", OrganType.IN_USAGE, 10000, .95)});
        this.intellectLevel = intellectLevel;
        thinks = new ArrayList<>();
    }

    @Override
    public void moveTo(Place location) {
        this.setLocation(location);
    }

    public void thinkAbout(Think think) {
        if (intellectLevel >= think.getIntellectFloor()) {
            thinks.add(think);
            System.out.println(this.getName() + " подумал о " + think);
        } else {
            System.out.println("Это невозможно уразуметь!");
        }
    }

    public void refuseThink(Think think){
        System.out.println(this.getName() + " отказадся от " + think);
    }

    public void makeAConclusion(){
        if (thinks.size() >= 5) {
            System.out.println(this.getName() + " сделал вывод.");
        } else {
            System.out.println("Для окончательных выводов недоставало информации.");
        }
    }

    public void rename(Organism organism, String name) {
        System.out.println(this.getName()+ " назвал " + organism.getName() + " " + name);
        organism.setName(name);
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

    @Override
    public String toString() {
        return this.getName() + " " + intellectLevel;
    }
}
