import ru.ifmo.se.pokemon.*;

class Waterfall extends PhysicalMove {
  protected Waterfall() {
    super(Type.WATER, 80, 100);
  }

  @Override
  protected String describe() {
    return "Имеет 20% вероятность заставить цель дрогнуть";
  }

  @Override
  protected void applyOppEffects(Pokemon p) {
    if (Math.random() <= 0.2) Effect.flinch(p);
  }
}
