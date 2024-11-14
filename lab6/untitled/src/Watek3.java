public class Watek3 implements Runnable {
    private int index;
    private int liczba_watkow;
    private Obraz obraz;

    public Watek3(int start, int liczba_watkow,Obraz obraz) {
        this.index = start;
        this.liczba_watkow = liczba_watkow;
        this.obraz = obraz;
    }

    @Override
    public void run() {
        obraz.calculate_cykliczny(index, liczba_watkow);
    }
}
