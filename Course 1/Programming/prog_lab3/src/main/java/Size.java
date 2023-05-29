public enum Size {
    SMALL("Маленький"),
    MIDDLE("Средний"),
    LARGE("Большой");

    private final String name;

    Size(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
