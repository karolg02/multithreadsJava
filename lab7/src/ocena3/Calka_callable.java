package ocena3;

import java.util.concurrent.Callable;


public class Calka_callable implements Callable<Double>{
//public class ocena3.Calka_callable{

    private double dx;
    private double xp;
    private double xk;
    private int N;

    public Calka_callable(double xp, double xk, double dx) {
        this.xp = xp;
        this.xk = xk;
        this.N = (int) Math.ceil((xk-xp)/dx);
        this.dx = (xk-xp)/N;
        /*System.out.println("Creating an instance of ocena3.Calka_callable");
        System.out.println("xp = " + xp + ", xk = " + xk + ", N = " + N);
        System.out.println("dx requested = " + dx + ", dx final = " + this.dx);*/
    }

    private double getFunction(double x) {
        return Math.sin(x);
    }

    @Override
    public Double call() throws Exception {
        double calka = 0;
        int i;
        for(i=0; i<N; i++){
            double x1 = xp+i*dx;
            double x2 = x1+dx;
            calka += ((getFunction(x1) + getFunction(x2))/2.)*dx;
        }
        System.out.println("Numer watku: " + Thread.currentThread().getName() + " Calka czastkowa: " + calka);
        return calka;
    }
}