public enum CosmosObject {
    EARTH("Земля"),
    A_FAR_PLANET("Далекая планета"),
    MARS("Марс");

    private final String name;

    CosmosObject(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
