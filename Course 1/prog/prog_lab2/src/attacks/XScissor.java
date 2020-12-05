import ru.ifmo.se.pokemon.*;

class XScissor extends PhysicalMove {
  protected XScissor() {
    super(Type.BUG, 80, 100);
  }

  @Override
  protected String describe() {
    return "Наносит обычные повреждения без дополнительных эффектов";
  }
}
