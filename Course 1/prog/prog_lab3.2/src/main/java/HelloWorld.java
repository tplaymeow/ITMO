class HelloWorld{
    public static void main(String[] args) {
        Characteristic ch1 = new Characteristic(Size.LARGE, 15000, true, Shape.OTHER_NON_SYMMETRICAL);
        Place earth = new Place(CosmosObject.EARTH, 127, 541);
        Creature cr1 = new Creature("Развитая тварь", earth, ch1);

        SeaStar ss1 = new SeaStar("Большая звезда", earth, ch1);

        OrganismPrinter printer1 = new OrganismPrinter(cr1);

        System.out.printf("\n\n\n");

        printer1.printBiologyKingdom();
        printer1.looksLike(ss1);
        printer1.comparison(ss1);
    }
}
