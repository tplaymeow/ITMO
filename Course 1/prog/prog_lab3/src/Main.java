import java.util.Date;

public class Main {
    public static void action1() {
        Characteristic ch = new Characteristic(Size.LARGE, 4500, true, Shape.OTHER_SYMMETRICAL);
        Place aFarPlanet = new Place(CosmosObject.A_FAR_PLANET,12,45);
        Place arhStones = new Place(CosmosObject.EARTH, 356, 32);

        Old p1 = new Old("Brawler1", aFarPlanet, aFarPlanet, ch);
        p1.flyTo(arhStones);
        p1.leaveMark(new Date());

        Place mountains = new Place(CosmosObject.EARTH, 54,45);
        Characteristic simpleCh = new Characteristic(Size.MIDDLE, 20, true, Shape.OTHER_NON_SYMMETRICAL);
        SimpleOrganism p2 = new SimpleOrganism("Простой чел", aFarPlanet, mountains, simpleCh, BiologyKingdom.ANIMALIA,new Organ[]{});

        System.out.println("Является ли " + p1.getName() + " простым организмом? Ответ: " + p1.equals(p2));
        System.out.println("Внешний вид:");
        System.out.println(p1);
    }

    public static void main(String[] args) {
        Place onEart1 = new Place(CosmosObject.EARTH, 44,45);
        Place onEart2 = new Place(CosmosObject.EARTH, 44,45);
        Characteristic simpleCh = new Characteristic(Size.MIDDLE, 85, true, Shape.OTHER_SYMMETRICAL);
        Human human = new Human("Дрейк", onEart1, onEart2, simpleCh, BiologyKingdom.ANIMALIA,new Organ[]{});

        human.dreamAbout();
        action1();
    }
}
