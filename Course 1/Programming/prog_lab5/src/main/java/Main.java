import app.App;

public class Main {
    public static void main(String[] args) {
        App app;
        switch (args.length) {
            case 1:
                app = new App(args[0]);
                app.interactive(System.in);
                break;
            case 2:
                app = new App(args[0], args[1]);
                app.interactive(System.in);
                break;
            default:
                System.out.println("Не верное количество аргументов");
        }
    }
}
