package ocena3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simple_executor_test {

    private static final int NTHREADS = 10;

    public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

		double xp = 0;
		double xk = Math.PI;

		System.out.println("Podaj krok: ");
		Scanner scanner = new Scanner(System.in);
		double dx = scanner.nextDouble();

		//sekwencyjnie - 1 polecenie
		System.out.println("Sekwencyjnie:");
		Calka_sekw calka_sekw = new Calka_sekw(xp,xk,dx);
		calka_sekw.getWynik();

		double range = (xk -xp)/NTHREADS;
		List<Future<Double>> results = new ArrayList<>();
		for(int i = 0; i < NTHREADS; i++) {
			double start = xp + i*range;
			double end = start + range;

			Calka_callable calkaCallable = new Calka_callable(start,end, dx);
			results.add(executor.submit(calkaCallable));
		}

		double wynik = 0;
		try {
			for (Future<Double> result : results) {
				wynik += result.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		executor.shutdown();

		while (!executor.isTerminated()) {}

		System.out.println("Wynik całki: " + wynik);
    }
} 
