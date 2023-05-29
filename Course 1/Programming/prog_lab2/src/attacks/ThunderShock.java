import ru.ifmo.se.pokemon.*;

class ThunderShock extends SpecialMove {
	protected ThunderShock() {
		super(Type.ELECTRIC, 40, 100);
	}
	
	@Override
	protected String describe() {
		return "Имеет 10% шанс парализовать цель";
	}
	
	@Override
	protected void applyOppEffects(Pokemon p) {
		if (Math.random() <= 0.1) Effect.paralyze(p);
	}
}