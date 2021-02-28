import annotations.GreaterThan;

public class B {
    public B() {
    }

    String string = "какой же я лох";
    @GreaterThan(num = 16)
    Float aFloat = 14.4F;
    C c = new C();

    @Override
    public String toString() {
        return "B{" +
                "string='" + string + '\'' +
                ", aFloat=" + aFloat +
                ", c=" + c +
                '}';
    }
}
