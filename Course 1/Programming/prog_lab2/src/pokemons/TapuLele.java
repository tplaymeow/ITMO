import ru.ifmo.se.pokemon.*;

public class TapuLele extends Pokemon {
	public TapuLele(String name, int level) {
		super(name, level);
		setType(Type.PSYCHIC, Type.FAIRY);
		setStats(70, 85, 75, 130, 115, 95);
		
		setMove(new WildCharge(), new Discharge(), new BraveBird(), new ThunderShock());
	}
}