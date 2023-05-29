import ru.ifmo.se.pokemon.*;

public class Crawdaunt extends Corphish {
  public Crawdaunt(String name, int level) {
    super(name, level);
    setType(Type.WATER, Type.DARK);
    setStats(63, 120, 85, 90, 55, 55);

    addMove(new Swift());
  }
}
