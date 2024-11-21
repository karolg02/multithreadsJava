package ocena4;

import java.util.concurrent.ForkJoinPool;

public class zad4 {
    public static void main(String[] args) {
        int[] tab = {1,8,10,44,55,7,2,4,38,10,22,56};
        System.out.println("Tablica przed sortowaniem:");
        printArray(tab);

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        DivideTask divideTask = new DivideTask(tab);
        int[] sortedTab = forkJoinPool.invoke(divideTask);

        System.out.println("Tablica po sortowaniu:");
        printArray(sortedTab);

    }

    public static void printArray(int[] array){
        for(int num : array){
            System.out.println(num);
        }
        System.out.println();
    }
}
