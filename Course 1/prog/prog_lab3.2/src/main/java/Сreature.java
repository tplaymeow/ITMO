public class Сreature extends Organism{
    public Сreature(String name, Place bornAt) {
        super(name,
                bornAt,
                new Characteristic(Size.LARGE, 10000, true, Shape.OTHER_SYMMETRICAL),
                10,
                new BiologyKingdom[]{BiologyKingdom.PLANTAE, BiologyKingdom.ANIMALIA},
                new Organ[]{new Wings("Крылья", OrganType.IN_USAGE, 4000, 0.8),
                        new Heart("Сердце", OrganType.IN_USAGE, 4000, 0.7),
                        new FakeLeg("Лженога", 0.3),
                        new Nerves("Нервы", OrganType.IN_USAGE, 1000, 0.5)});
    }

    @Override
    public void moveTo(Place location) {

    }
}
