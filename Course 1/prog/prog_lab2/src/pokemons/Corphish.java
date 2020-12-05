import ru.ifmo.se.pokemon.*;

public class Corphish extends Pokemon {
  public Corphish(String name, int level) {
    super(name, level);
    setType(Type.WATER);
    setStats(43, 80, 65, 50, 35, 35);

    setMove(new RazorShell(), new Waterfall(), new XScissor());
  }
}
