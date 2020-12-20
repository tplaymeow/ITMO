public class Old extends Organism implements SeaStar, Plant{
    public Old(String name, Place bornAt, Size size, Organ[] bodies) {
        super(name, bornAt, size, bodies);
    }

    public void flyTo(Place place) {
        this.setLocation(place);
    }

    public void swimTo(Place place) {
        this.setLocation(place);
    }
}
