import ru.ifmo.se.pokemon.*;

class RockTomb extends PhysicalMove {
  protected RockTomb() {
    super(Type.ROCK, 50, 80);
  }

  @Override
  protected String describe() {
    return "Имеет 100% вероятность понизить Скорость цели на одну ступень";
  }

  @Override
  protected void applyOppEffects(Pokemon p) {
    p.setMod(Stat.SPEED, -1);
  }
}
