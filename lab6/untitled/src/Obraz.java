import java.sql.SQLOutput;
import  java.util.Random;


class Obraz {
    
	
	private int l_znakow = 94;
    private int size_n;
    private int size_m;
    private char[][] tab;
    private char[] tab_symb;
    private int[] histogram;
	private int[] histogram_watku;
    
    public Obraz(int n, int m) {
	
	this.size_n = n;
	this.size_m = m;
	tab = new char[n][m];
	tab_symb = new char[l_znakow];
	
	final Random random = new Random();
	
	// for general case where symbols could be not just integers
	for(int k=0;k<l_znakow;k++) {
	    tab_symb[k] = (char)(k+33); // substitute symbols
	}

	for(int i=0;i<n;i++) {
	    for(int j=0;j<m;j++) {	
		//tab[i][j] = tab_symb[random.nextInt(l_znakow)];  // ascii 33-127
		tab[i][j] = (char)(random.nextInt(l_znakow)+33);  // ascii 33-127
		System.out.print(tab[i][j]+" ");
	    }
	    System.out.print("\n");
	}
	System.out.print("\n\n"); 
	
	histogram = new int[l_znakow];
	histogram_watku = new int[l_znakow];
   	clear_histogram();
    }
    
    public void clear_histogram(){

	for(int i=0;i<l_znakow;i++) histogram[i]=0;

    }

    public void calculate_histogram(){

	  for(int i=0;i<size_n;i++) {
	    for(int j=0;j<size_m;j++) {

		// optymalna wersja obliczania histogramu, wykorzystująca fakt, że symbole w tablicy
		// można przekształcić w indeksy tablicy histogramu
		// histogram[(int)tab[i][j]-33]++;
		
		// wersja bardziej ogólna dla tablicy symboli nie utożsamianych z indeksami
		// tylko dla tej wersji sensowne jest zrównoleglenie w dziedzinie zbioru znaków ASCII
		  for(int k=0;k<l_znakow;k++) {
		    if(tab[i][j] == tab_symb[k]) histogram[k]++;
		    //if(tab[i][j] == (char)(k+33)) histogram[k]++;	    
		  }

	    }
	  }

    }
	//1
	public void calculate_histogram_watki(int index){
		for(int i=0;i<size_n;i++) {
			for(int j=0;j<size_m;j++) {
				if((int)tab[i][j]-33 == index) histogram_watku[(int)tab[i][j]-33]++;
			}
		}
	}
	//2
	public void calculate_blokowe(int start, int koniec) {
		for (int i = 0; i < size_n; i++) {
			for (int j = 0; j < size_m; j++) {
				int symbolIndex = (int) tab[i][j] - 33;
				if (symbolIndex >= start && symbolIndex < koniec) {
					histogram_watku[symbolIndex]++;
				}
			}
		}
	}
	//3
	public void calculate_cykliczny(int start, int ilosc_watkow) {
		int[] tabTemp = new int[l_znakow];

		for(int i = 0;i<l_znakow;i++) tabTemp[i] = 0;

		for(int i = start; i < size_n; i += ilosc_watkow){
			for(int j = 0; j < size_m; j++){
				for( int k = 0; k < l_znakow; k++) {
					if(tab[i][j]==tab_symb[k]) tabTemp[k]++;
				}
			}
		}
		save_sum(tabTemp);
	}

	private synchronized void save_sum(int[] tabTemp) {
		for(int i = 0; i < l_znakow; i++) {
			histogram_watku[i] += tabTemp[i];
		}
	}
	//4
	public void calculate_blokowo_kolumnowo(int start, int koniec) {

		for(int i = 0; i < size_n; i++){
			for(int j = start; j < koniec; j++){
				int symbolIndex = (int) tab[i][j] - 33;
				synchronized (this){
					histogram_watku[symbolIndex]++;
				};
				//histogram_watku[symbolIndex]++;
			}
		}
	}


// uniwersalny wzorzec dla różnych wariantów zrównoleglenia - można go modyfikować dla
// różnych wersji dekompozycji albo stosować tak jak jest zapisane poniżej zmieniając tylko
// parametry wywołania w wątkach
//
//calculate_histogram_wzorzec(start_wiersz, end_wiersz, skok_wiersz,
//                            start_kol, end_kol, skok_kol,
//                            start_znak, end_znak, skok_znak){
//
//  for(int i=start_wiersz;i<end_wiersz;i+=skok_wiersz) {
//     for(int j=start_kol;j<end_kol;j+=skok_kol) {
//        for(int k=start_znak;k<end_znak;k+=skok_znak) {
//           if(tab[i][j] == tab_symb[k]) histogram[k]++;
//

	public synchronized void print_histogram_custom(int id){
		for(int i = 0; i < l_znakow; i++){
			System.out.print("Thread id: "+id+ " ");
			System.out.print(tab_symb[i]+ " " +histogram[i]);
			for(int j = 0; j <histogram_watku[i]; j++){
				System.out.print("=");
			}
			System.out.print("\n");
		}
	}

    public void print_histogram(){
	
	for(int i=0;i<l_znakow;i++) {
	    System.out.print(tab_symb[i]+" "+histogram[i] + " " + histogram_watku[i]+" ");
		for(int j = 0; j <histogram_watku[i]; j++){
			System.out.print("=");
		}
		System.out.println("\n");
	    //System.out.print((char)(i+33)+" "+histogram[i]+"\n");	    
	}

    }

}
