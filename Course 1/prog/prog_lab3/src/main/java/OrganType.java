public enum OrganType {
    RUDIMENT("Рудиментный"),
    ATROPH("Атрофированный"),
    IN_USAGE("Используемый");

    private final String name;

    OrganType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}