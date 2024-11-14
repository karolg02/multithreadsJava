public class Watek4 implements Runnable {

    private int start;
    private int koniec;
    private Obraz obraz;

    public Watek4(int start, int koniec, Obraz obraz) {
        this.start = start;
        this.koniec = koniec;
        this.obraz = obraz;
    }

    @Override
    public void run() {
        this.obraz.calculate_blokowo_kolumnowo(start, koniec);
    }
}
