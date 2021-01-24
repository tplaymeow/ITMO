import ru.ifmo.se.pokemon.*;

public class Swinub extends Pokemon {
  public Swinub(String name, int level) {
    super(name, level);
    setType(Type.ICE, Type.GROUND);
    setStats(50, 50, 40, 30, 30, 50);

    setMove(new RockTomb(), new Bulldoze());
  }
}
