import ru.ifmo.se.pokemon.*;

class WildCharge extends PhysicalMove {
	protected WildCharge() {
		super(Type.ELECTRIC, 90, 100);
	}
	
	@Override
	protected String describe() {
		return "Покемон получает отдачу в размере 1/4 нанесённых повреждений.";
	}
	
	@Override
	protected void applySelfDamage(Pokemon att, double damage) {
		att.setMod(Stat.HP, (int) Math.round(damage / 4));
	}
}