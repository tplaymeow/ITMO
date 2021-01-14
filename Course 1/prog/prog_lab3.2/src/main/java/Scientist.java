public class Scientist extends Human{
    private boolean isWorking;

    public Scientist(String name, Place bornAt, int intellectLevel) {
        super(name, bornAt, intellectLevel);
        isWorking = false;
    }


}
