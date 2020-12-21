public class Old extends Organism implements Plant, SeaStar{

    public Old(String name, Place bornAt, Place location, Characteristic characteristic) {
        super(name,
                bornAt,
                location,
                characteristic,
                BiologyKingdom.ANIMALIA,
                new Organ[]{new Wings("Крылья", OrganType.IN_USAGE, 1000, 0.75),
                        new Heart("Сердце", OrganType.IN_USAGE, 1000, 0.75),
                        new FakeLeg("Лженога", 0.75),
                        new Nerves("Нервы", OrganType.IN_USAGE, 1000, 0.75),
                        new SomeOrgan("Атроф. Орган", OrganType.ATROPH, 1000, 0.75),
                        new SomeOrgan("Атроф. Орган", OrganType.ATROPH, 1000, 0.75),
                        new SomeOrgan("Атроф. Орган", OrganType.ATROPH, 1000, 0.75),
                        new SomeOrgan("Руд. Орган", OrganType.RUDIMENT, 1000, 0.75)});
    }

    public void flyTo(Place location) {
        this.setLocation(location);
        System.out.println(this.getName() + " полетел в " + location.toString());
    }

    public void swimTo(Place location) {
        this.setLocation(location);
        System.out.println(this.getName() + " поплыл в " + location.toString());
    }

    public void generateOxygen() {
        System.out.println("Произведён кислород");
    }
}
