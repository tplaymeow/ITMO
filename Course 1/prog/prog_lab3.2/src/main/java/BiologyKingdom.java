public enum BiologyKingdom {
    ANIMALIA("Животные"),
    PLANTAE("Растения"),
    FUNGI("Грибы"),
    ARCHAEA("Вирусы"),
    BACTERIA("Бактерии");

    private final String name;

    BiologyKingdom(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
