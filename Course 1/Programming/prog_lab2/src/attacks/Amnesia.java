import ru.ifmo.se.pokemon.*;

class Amnesia extends StatusMove {
  protected Amnesia() {
    super(Type.PSYCHIC, 0, 0);
  }

  @Override
  protected String describe() {
    return "Повышает свою Спец. Защиту на две ступени";
  }

  @Override
  protected void applySelfEffects(Pokemon p) {
    p.setMod(Stat.SPECIAL_DEFENSE, 2);
  }
}
