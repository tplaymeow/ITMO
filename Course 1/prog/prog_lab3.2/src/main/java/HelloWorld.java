import java.util.Date;

class HelloWorld{
    public static void main(String[] args) {
        Characteristic ch1 = new Characteristic(Size.LARGE, 15000, true, Shape.OTHER_SYMMETRICAL);
        Place earth = new Place("Архейские камни", CosmosObject.EARTH, 127, 541);
        Creature cr1 = new Creature("Развитая тварь", earth, ch1);
        SeaStar ss1 = new SeaStar("Морская звезда", earth, ch1);
        Evolution evolution = new Evolution();

        OrganismPrinter printer1 = new OrganismPrinter(cr1);

        System.out.print("\n");

        printer1.printBiologyKingdom();
        printer1.looksLike(ss1);
        printer1.comparison(ss1);
        printer1.isWater();
        evolution.getUp(cr1);
        cr1.leaveMark(-4000000000L);
    }
}
