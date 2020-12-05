import ru.ifmo.se.pokemon.*;

class BraveBird extends PhysicalMove {
	protected BraveBird() {
		super(Type.FLYING, 120, 100);
	}
	
	@Override
	protected String describe() {
		return "Покемон получает отдачу в размере 1/3 нанесённых повреждений";
	}
	
	@Override
	protected void applySelfDamage(Pokemon att, double damage) {
		att.setMod(Stat.HP, (int) Math.round(damage / 3));
	}
}