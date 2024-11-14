public class Watek2 implements Runnable{

    private int start;
    private int koniec;
    private Obraz obraz;

    public Watek2(int start, int koniec, Obraz obraz) {
        this.start = start;
        this.koniec = koniec;
        this.obraz = obraz;
    }

    @Override
    public void run() {
        this.obraz.calculate_blokowe(this.start, this.koniec);
    }
}
