public class Watek2 implements Runnable{

    private int start;
    private int koniec;
    private Obraz obraz;
    private int thread_id;

    public Watek2(int thread_id,int start, int koniec, Obraz obraz) {
        this.start = start;
        this.koniec = koniec;
        this.obraz = obraz;
        this.thread_id = thread_id;
    }

    @Override
    public void run() {
        this.obraz.calculate_blokowe(this.start, this.koniec);
        this.obraz.print_histogram_custom(thread_id);
    }
}
