import ru.ifmo.se.pokemon.*;

class IceBeam extends SpecialMove {
  protected IceBeam() {
    super(Type.ICE, 90, 100);
  }

  @Override
  protected String describe() {
    return "Имеет 10% шанс заморозить цель";
  }

  @Override
  protected void applyOppEffects(Pokemon p) {
    if (Math.random() <= 0.1) Effect.freeze(p);
  }
}
