import annotations.Between;
import annotations.GreaterThan;
import annotations.LongerThan;

public class A {
    public A() {
    }

    @Between(from = 1, to = 10)
    int a = 5;
    @GreaterThan(num = -4)
    @Between(from = 3, to = 7)
    Integer b = 6;
    @LongerThan(length = 5)
    String name = "hello";
    slova slov = slova.poka;
    B aB = new B();

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b=" + b +
                ", name='" + name + '\'' +
                ", slov=" + slov +
                ", aB=" + aB +
                '}';
    }
}
