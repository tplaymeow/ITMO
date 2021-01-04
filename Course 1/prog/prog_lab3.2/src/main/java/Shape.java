public enum Shape {
    CIRCLE("Круглая форма"),
    SQUARE("Квадратная форма"),
    OTHER_SYMMETRICAL("Симетричная форма"),
    OTHER_NON_SYMMETRICAL("Не семетричная форма"),
    SIMPLE_SHAPE("Простая форма");

    private final String name;

    Shape(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
