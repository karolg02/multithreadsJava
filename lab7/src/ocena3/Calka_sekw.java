package ocena3;

public class Calka_sekw {
    private double a;
    private double b;
    private double dx;
    private double N;

    public Calka_sekw(double a, double b, double dx) {
        this.a = a;
        this.b = b;
        this.dx = dx;
        this.N = (int) Math.ceil((b-a)/dx);
    }

    private double getFunction(double x) {
        return Math.sin(x);
    }

    public void getWynik() {
        double calka = 0;
        int i;
        for(i=0; i<N; i++){
            double x1 = a+i*dx;
            double x2 = x1+dx;
            calka += ((getFunction(x1) + getFunction(x2))/2.)*dx;
        }
        System.out.println("Wynik sekwencyjnej: " + calka);
    }

}
