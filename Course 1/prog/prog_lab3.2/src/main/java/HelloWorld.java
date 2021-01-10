class HelloWorld{
    public static void main(String[] args) {
        action();
    }

    public static void action() {
        //Create cosmos objects
        CosmosObject earth = new CosmosObject("Земля", 4500000000L, true);
        CosmosObject youngEarth = new CosmosObject("Земля", 6000000L, true);
        CosmosObject veryYoungEarth = new CosmosObject("Земля", 4000000L, false);
        CosmosObject aFarStar = new CosmosObject("Далекая звезда", 50000000L, true);

        //Create places
        Place earth = new Place("Земля", CosmosObject.EARTH, 10, 10);
        Place arcStones = new Place("Архейские камни", CosmosObject.EARTH, 153, 435);
        Place aFarPlanet = new Place("Далекая планета", CosmosObject.A_FAR_PLANET, 0, 0);

        //Create characteristic
        Characteristic crCharacteristic = new Characteristic(Size.LARGE, 5000, true, Shape.OTHER_SYMMETRICAL);

        //Create characters
        Creature creature1 = new Creature("Тварь", earth, crCharacteristic);
        SeaStar seaStar = new SeaStar("Морская звезда", earth, crCharacteristic);
        Human lake = new Human("Лэйк", earth, 7);
        Creature starec = new Creature("Старец", aFarPlanet, crCharacteristic);

        //Create others
        OrganismPrinter printer = new OrganismPrinter(creature1);
        Evolution evolution = new Evolution();
        Think difficultThink = new Think(10, "Что-то сложное");
        Think myth = new Think(2, "древние мифы о Старцах");
        Think story = new Think(2, "фантастические рассказы друга-фольклориста из Мискатоникского университета о живущих в горах тварях родом из космоса");


        //...
        System.out.println();
        //Начало замеса
        printer.printBiologyKingdom();
        printer.looksLike(seaStar);
        printer.comparison(seaStar);
        printer.isWater();
        evolution.getUp(creature1);
        creature1.moveTo(arcStones);// <- тут надо еще подумать
        creature1.leaveMark(-4000000000L);
        lake.thinkAbout(difficultThink);
        lake.thinkAbout(myth);
        starec.moveTo(earth);//
        starec.startLife();
        lake.thinkAbout(story);
    }
}
