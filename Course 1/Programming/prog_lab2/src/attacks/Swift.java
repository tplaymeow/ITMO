import ru.ifmo.se.pokemon.*;

class Swift extends SpecialMove {
  protected Swift() {
    super(Type.NORMAL, 60, 100);  //accuracy не влияет на вероятность попадания
  }

  @Override
  protected String describe() {
    return "Не промахивается";
  }

  @Override
  protected boolean checkAccuracy(Pokemon att, Pokemon def) {
    return true;
  }
}
