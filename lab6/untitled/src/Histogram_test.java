import java.util.Scanner;


class Histogram_test {
    
    public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Set image size: n (#rows), m(#kolumns)");
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		System.out.println("Set number of threads");
		int num_threads = scanner.nextInt();

		//zad1
		/*Obraz obraz_1 = new Obraz(n, num_threads);
		Watek[] NewThr = new Watek[num_threads];
		//pula watkow
		for (int i = 0; i < num_threads; i++) {
			(NewThr[i] = new Watek(i,obraz_1)).start();
		}

		for (int i = 0; i < num_threads; i++) {
			try {
				NewThr[i].join();
			}
			catch (InterruptedException e) {}
		}
		obraz_1.calculate_histogram();*/
		//obraz_1.print_histogram();

		//zad2 blokowo
		/*Obraz obraz2 = new Obraz(n, m);
		Watek2[] NewThr2 = new Watek2[num_threads];
		Thread[] NewThr = new Thread[num_threads];
		int el_na_watek = (int)Math.ceil(94.0/num_threads);
		for(int i =0 ; i < num_threads;i++){
			int start = el_na_watek*i;
			int koniec = el_na_watek*(i+1);
			if(koniec > 94) koniec = 94;
			NewThr2[i] = new Watek2(i,start, koniec,obraz2);
			NewThr[i] = new Thread(NewThr2[i]);
			NewThr[i].start();
		}

		for (int i = 0; i < num_threads; i++) {
			try {
				NewThr[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		obraz2.calculate_histogram();*/
		//obraz2.print_histogram();

		//zadanie 3
		Obraz obraz3 = new Obraz(n, m);
		Watek3[] NewThr3 = new Watek3[num_threads];
		Thread[] NewThr = new Thread[num_threads];
		for(int i =0 ; i < num_threads;i++){
			NewThr3[i] = new Watek3(i, num_threads,obraz3);
			NewThr[i] = new Thread(NewThr3[i]);
			NewThr[i].start();
		}

		for (int i = 0; i < num_threads; i++) {
			try {
				NewThr[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		obraz3.calculate_histogram();
		obraz3.print_histogram();


		//zadanie 4
		/*Obraz obraz4 = new Obraz(n, m);
		Watek4[] NewThr4 = new Watek4[num_threads];
		Thread[] NewThr = new Thread[num_threads];
		int el_na_watek = (int)Math.ceil((float)m/num_threads);
		for(int i =0 ; i < num_threads;i++){
			int start = el_na_watek*i;
			int koniec = el_na_watek*(i+1);
			if(koniec > m) koniec = m;
			NewThr4[i] = new Watek4(start, koniec,obraz4);
			NewThr[i] = new Thread(NewThr4[i]);
			NewThr[i].start();
		}

		for (int i = 0; i < num_threads; i++) {
			try {
				NewThr[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		obraz4.calculate_histogram();
		obraz4.print_histogram();*/
    }

}

