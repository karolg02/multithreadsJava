public class Watek extends Thread {

    private int nrWatku;
    private Obraz obraz;

    public Watek(int i, Obraz obraz1) {
        nrWatku = i;
        obraz = obraz1;
    }

    @Override
    public void run(){
        this.obraz.calculate_histogram_watki(nrWatku);
    }
}
