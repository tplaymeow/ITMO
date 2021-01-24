import ru.ifmo.se.pokemon.*;

class Battleground {
	public static void main(String[] args) {
		Battle b = new Battle();
		
		b.addAlly(new TapuLele("Бравлер1", 1));
		b.addAlly(new Corphish("Бравлер2", 1));
		b.addAlly(new Crawdaunt("Бравлер3", 1));

		b.addFoe(new Swinub("Роялер1", 1));
		b.addFoe(new Piloswine("Роялер2", 1));
		b.addFoe(new Mamoswine("Роялер3", 1));
		
		b.go();
	}
}