import ru.ifmo.se.pokemon.*;

class Discharge extends SpecialMove {
	protected Discharge() {
		super(Type.ELECTRIC, 80, 100);
	}
	
	@Override
	protected String describe() {
		return "Имеет 30% вероятность парализовать цель";
	}
	
	@Override
	protected void applyOppEffects(Pokemon p) {
		if (Math.random() <= 0.3) Effect.paralyze(p);
	}
}