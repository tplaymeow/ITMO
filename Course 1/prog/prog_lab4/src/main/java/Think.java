import java.util.Objects;

public class Think {
    private final int intellectFloor;
    private final String thinkDescription;

    public Think(int intellectFloor, String thinkDescription) {
        this.intellectFloor = intellectFloor;
        this.thinkDescription = thinkDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Think think = (Think) o;
        return intellectFloor == think.intellectFloor && Objects.equals(thinkDescription, think.thinkDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intellectFloor, thinkDescription);
    }

    @Override
    public String toString() {
        return thinkDescription;
    }

    public int getIntellectFloor() {
        return intellectFloor;
    }
}
