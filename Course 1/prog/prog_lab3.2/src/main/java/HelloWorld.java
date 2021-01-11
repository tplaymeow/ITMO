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
        Place plEarth = new Place("Земля", earth, 10, 10);
        Place plArcStones = new Place("Архейские камни", youngEarth, 153, 435);
        Place plYoungEarth = new Place("Молодая земля", veryYoungEarth, 0, 0);
        Place plAFarPlanet = new Place("Далекая планета", aFarStar, 0, 0);

        //Create characteristic
        Characteristic crCharacteristic = new Characteristic(Size.LARGE, 5000, true, Shape.OTHER_SYMMETRICAL);

        //Create characters
        Creature creature1 = new Creature("Тварь(найденная)", plEarth, crCharacteristic);
        Creature creature2 = new Creature("Тварь(старая)", plYoungEarth, crCharacteristic);
        creature2.setComplexity(11);
        SeaStar seaStar = new SeaStar("Морская звезда", plEarth, crCharacteristic);
        Human lake = new Human("Лэйк", plEarth, 7);
        Creature starec = new Creature("Старец", plAFarPlanet, crCharacteristic);

        //Create others
        OrganismPrinter printer = new OrganismPrinter(creature1);
        Evolution evolution = new Evolution();
        Think difficultThink = new Think(10, "Что-то сложное");
        Think myth = new Think(2, "древние мифы о Старцах");
        Think story = new Think(2, "фантастические рассказы друга-фольклориста из Мискатоникского университета о живущих в горах тварях родом из космоса");
        Think think1 = new Think(2, "не могло ли на докембрийском камне оставить следы существо более примитивное, чем лежащая перед ним особь");
        Think easyThink = new Think(2, "Легкое объяснение");


        //...
        System.out.println();
        //Начало замеса
        printer.printBiologyKingdom();
        printer.looksLike(seaStar);
        printer.comparison(seaStar);
        printer.isWater();
        evolution.getUp(creature1);
        creature2.moveTo(plArcStones);// <- тут надо еще подумать
        creature2.leaveMark();
        lake.thinkAbout(difficultThink);

        lake.thinkAbout(myth);
        starec.moveTo(plYoungEarth);//
        starec.startLife();
        lake.thinkAbout(story);
        lake.thinkAbout(think1);
        lake.refuseThink(easyThink);

        printer.comparison(plArcStones.getMarks().get(0).getAuthor());
        creature1.reduceFakeLeg();
        creature1.simplifyShape();
    }
}

