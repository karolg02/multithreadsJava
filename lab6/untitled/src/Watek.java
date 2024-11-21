public class Watek extends Thread {

    private int nrWatku;
    private Obraz obraz;

    public Watek(int i, Obraz obraz1) {
        this.nrWatku = i;
        this.obraz = obraz1;
    }

    @Override
    public void run(){
        this.obraz.calculate_histogram_watki(nrWatku);
        this.obraz.print_histogram_custom(nrWatku);
    }
}
