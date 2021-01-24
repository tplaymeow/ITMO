import ru.ifmo.se.pokemon.*;

class RazorShell extends PhysicalMove {
  protected RazorShell() {
    super(Type.WATER, 75, 95);
  }

  @Override
  protected String describe() {
    return "Имеет 50% вероятность понизить Защиту цели на одну ступень";
  }

  @Override
  protected void applyOppEffects(Pokemon p) {
    if (Math.random() <= 0.5) p.setMod(Stat.DEFENSE, -1);
  }
}
