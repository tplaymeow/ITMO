public class SeaStar extends Organism implements SeaAnimal{
    public SeaStar(String name, Place bornAt, Characteristic characteristic) {
        super(name,
                bornAt,
                characteristic,
                1,
                BiologyKingdom.ANIMALIA,
                new Organ[]{});
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
        swimTo(location);
    }

    public void swimTo(Place location) {
        if ((location.getCosmosObject() == this.getLocation().getCosmosObject())
                && (Math.sqrt(Math.pow(location.getxCoordinate() - this.getLocation().getxCoordinate(), 2) + Math.pow(location.getyCoordinate() - this.getLocation().getyCoordinate(), 2)) < 2)) {
            this.setLocation(location);
            System.out.println(this.getName() + " переплыл в " + location + ".");
        }
    }
}
