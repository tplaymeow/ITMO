public enum CosmosObject {
    EARTH("Земля", 4500000000L),
    A_FAR_PLANET("Далекая планета", 6000000000L),
    MARS("Марс", 4600000000L);

    private final String name;
    private final long age;

    CosmosObject(String name, long age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return this.name;
    }

    public long getAge() {
        return age;
    }
}
