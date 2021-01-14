public class FakeLeg extends Organ{
    public FakeLeg(String name, double condition) {
        super(name, OrganType.RUDIMENT, 5000, condition);
        this.setCharacteristic(new Characteristic(Size.LARGE, 200, true, Shape.OTHER_NON_SYMMETRICAL));
        this.setMandatory(false);
    }
}
