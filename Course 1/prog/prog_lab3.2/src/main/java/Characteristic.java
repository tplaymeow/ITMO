import java.util.Objects;

public class Characteristic {
    private Size size;
    private double weight;
    private boolean isVisible;
    private Shape shape;

    public Characteristic(Size size, double weight, boolean isVisible, Shape shape) {
        this.size = size;
        this.weight = weight;
        this.isVisible = isVisible;
        this.shape = shape;
    }

    @Override
    public String toString() {
        String vis = (isVisible) ? "Видимый" : "Невидимый";
        return "Размер: " + size + ". Вес: " + weight + ". " + vis + ". Форма: " + shape + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characteristic that = (Characteristic) o;
        return Double.compare(that.weight, weight) == 0 && isVisible == that.isVisible && Objects.equals(size, that.size) && Objects.equals(shape, that.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, weight, isVisible, shape);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
