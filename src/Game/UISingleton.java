package Game;

public class UISingleton {
    private static UI ui;

    public static UI getUi() {
        if (ui == null) {
            ui = new UI();
        }
        return ui;
    }
}
