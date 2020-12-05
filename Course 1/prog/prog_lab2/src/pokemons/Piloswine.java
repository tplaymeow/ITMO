import ru.ifmo.se.pokemon.*;

public class Piloswine extends Swinub {
  public Piloswine(String name, int level) {
    super(name, level);
    setStats(100, 100, 80, 60, 60, 50);

    addMove(new Amnesia());
  }
}
