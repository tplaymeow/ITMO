import java.util.Objects;

class Main {
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
        Place plStart = new Place("Палатка", earth, 10, 10);
        Place plArcStones = new Place("Архейские камни", youngEarth, 153, 435);
        Place plYoungEarth = new Place("Молодая земля", veryYoungEarth, 0, 0);
        Place plAFarPlanet = new Place("Далекая планета", aFarStar, 0, 0);

        //Create characteristic
        Characteristic crCharacteristic = new Characteristic(Size.LARGE, 5000, true, Shape.OTHER_SYMMETRICAL);

        //Create characters
        Creature creature1 = new Creature("Тварь(найденная)", plEarth, crCharacteristic);
        Creature creature2 = new Creature("Тварь(старая)", plYoungEarth, crCharacteristic);
        Creature os0 = new Creature("Рассеченная особь", plYoungEarth, crCharacteristic);
        Creature os1 = new Creature("особь1", plEarth, crCharacteristic);
        Creature os2 = new Creature("особь2", plEarth, crCharacteristic);
        creature2.setComplexity(11);
        SeaStar seaStar = new SeaStar("Морская звезда", plEarth, crCharacteristic);
        Scientist lake = new Scientist("Лэйк", plEarth, 7);
        Creature starec = new Creature("Старец", plAFarPlanet, crCharacteristic);

        //Create others
        OrganismPrinter printer = new OrganismPrinter(creature1);
        Think difficultThink = new Think(10, "Что-то сложное");
        Think myth = new Think(2, "древние мифы о Старцах");
        Think story = new Think(2, "фантастические рассказы друга-фольклориста из Мискатоникского университета о живущих в горах тварях родом из космоса");
        Think think1 = new Think(2, "не могло ли на докембрийском камне оставить следы существо более примитивное, чем лежащая перед ним особь");
        Think easyThink = new Think(2, "Легкое объяснение");
        //
        Organ org1 = new Organ("Угол головы", OrganType.ATROPH, 5, 0.1) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        Organ org2 = new Organ("Трубочка1", OrganType.ATROPH, 5, 0.1) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        Organ org3 = new Organ("Трубочка2", OrganType.ATROPH, 5, 0.1) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        org1.setCharacteristic(new Characteristic(Size.LARGE, 200, true, Shape.OTHER_NON_SYMMETRICAL));
        org2.setCharacteristic(new Characteristic(Size.LARGE, 200, true, Shape.OTHER_NON_SYMMETRICAL));
        org3.setCharacteristic(new Characteristic(Size.LARGE, 200, true, Shape.OTHER_NON_SYMMETRICAL));



        //...
        System.out.println();
        //Начало замеса
        printer.printBiologyKingdom();
        printer.looksLike(seaStar);
        printer.comparison(seaStar);
        printer.isWater();
        Evolution.getUp(creature1);
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
        Evolution.regress(creature1);
        printer.mostPopularOrganType();
        lake.makeAConclusion();
        lake.thinkAbout(myth);
        lake.rename(creature1, "Старец");

        ///
        os0.getOrgans().add(0,org1);
        os0.getOrgans().add(0,org2);
        os0.getOrgans().add(0,org3);
        os1.getOrgans().add(0,org1);
        os1.getOrgans().add(0,org2);
        os1.getOrgans().add(0,org3);
        os2.getOrgans().add(0,org1);
        os2.getOrgans().add(0,org2);
        os2.getOrgans().add(0,org3);
        Main.Time time = new Main.Time(2,30);
        Scientist.Work lakesWork = lake.new Work("Исследователь", "Что-то...");
        try {
            lakesWork.start();
        } catch (WorkException e) {
            System.out.println(e.getMessage());
        }
        Place.Weather eaWeather = plEarth.new Weather(-30);
        Think weatherThink = new Think(0,"никакой опасности, процесс распада не может идти быстро при минусовой температуре");

        //
        System.out.println();

        time.printTime();
        try {
            lakesWork.end();
        } catch (WorkException e) {
            System.out.println(e.getMessage());
        }
        lake.cover(os0);
        System.out.println(lake.getLocation());
        lake.moveTo(plEarth);
        lake.learn(os1);
        lake.learn(os2);
        eaWeather.applySunEffect(os1);
        eaWeather.applySunEffect(os2);
        lake.thinkAbout(weatherThink);


    }

    static class Time {
        int hours;
        int minutes;
        public void printTime() {
            System.out.println("Время: " + hours + ":" + minutes);
        }

        public Time(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Time time = (Time) o;
            return hours == time.hours &&
                    minutes == time.minutes;
        }

        @Override
        public int hashCode() {
            return Objects.hash(hours, minutes);
        }

        @Override
        public String toString() {
            return "Time{" +
                    "hours=" + hours +
                    ", minutes=" + minutes +
                    '}';
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }
    }
}

