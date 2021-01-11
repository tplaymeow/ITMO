public class Creature extends Organism implements Plant, SeaAnimal{
    public Creature(String name, Place bornAt, Characteristic characteristic) {
        super(name,
                bornAt,
                characteristic,
                10,
                new BiologyKingdom[]{BiologyKingdom.PLANTAE, BiologyKingdom.ANIMALIA},
                new Organ[]{new Wings("Крылья", OrganType.IN_USAGE, 4000, 0.8),
                        new Heart("Сердце", OrganType.IN_USAGE, 4000, 0.7),
                        new FakeLeg("Лженога", 0.3),
                        new Nerves("Нервы", OrganType.IN_USAGE, 1000, 0.5)});
    }

    @Override
    public String toString() {
        return "Тварь " + this.getName() + " родилась в: " + this.getBornAt() + ", проживает в: " + this.getLocation();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public void moveTo(Place location) {
        this.setLocation(location);
        System.out.println(this.getName() + " перешел в " + location + ".");
    }

    public void flyTo(Place location) {
        boolean isFlying = false;
        for (Organ organ : this.getOrgans()) {
            if (organ instanceof Wings) {
                isFlying = true;
                break;
            }
        }
        if (isFlying && (location.getCosmosObject() == this.getLocation().getCosmosObject())) {
            this.setLocation(location);
            System.out.println(this.getName() + " перелетел в " + location + ".");
        }
    }

    public void swimTo(Place location) {
        if ((location.getCosmosObject() == this.getLocation().getCosmosObject())
                && (Math.sqrt(Math.pow(location.getxCoordinate() - this.getLocation().getxCoordinate(), 2) + Math.pow(location.getyCoordinate() - this.getLocation().getyCoordinate(), 2)) < 1000)) {
            this.setLocation(location);
            System.out.println(this.getName() + " переплыл в " + location + ".");
        }
    }

    public void generateOxygen() {
        System.out.println("Произведён кислород.");
        for (Organ organ:
             this.getOrgans()) {
            organ.setCondition(1d);
            System.out.println(organ.getName() + " воставновлен.");
        }
    }

    public void startLife() {
        this.getLocation().getCosmosObject().setLives(true);
        System.out.println(this.getName() + " зародил жизнь на " + this.getLocation().getCosmosObject() + ".");
    }

}
