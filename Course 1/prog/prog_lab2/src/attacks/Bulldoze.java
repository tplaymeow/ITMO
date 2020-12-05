import ru.ifmo.se.pokemon.*;

class Bulldoze extends PhysicalMove {
  protected Bulldoze() {
    super(Type.GROUND, 60, 100);
  }

  @Override
  protected String describe() {
    return "Понижает Скорость цели на одну ступень";
  }

  @Override
  protected void applyOppEffects(Pokemon p) {
    p.setMod(Stat.SPEED, -1);
  }
}
