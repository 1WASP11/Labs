import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

//Задано масив цілих чисел. Визначити, скільки з них набуває найбільшого значення.
public class Array2 {
    public static void main(String[] args){
        try (Scanner sc = new Scanner(System.in)) {
            //Введення кількості значень в масиві
            System.out.println("Введіть кільсть значень у масиві: ");

            //Створення масиву
            int num = sc.nextInt();
            int[] arr = new int [num];

             //Заповнення масиву випадковими числами від -5 до 5
             for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * 11) - 5;
            }

            //Виведення значень масиву
            System.out.println("Значення масиву:");
            for (int value : arr) {
                System.out.print(value + " ");
            }
            System.out.println();

            //Пошук максимальне значення
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }

            //Пошук кількість максимальних значень
            int count = 0;
            for (int value : arr) {
                if (value == max) {
                    count++;
                }
            }

            //Визначення індекси максимальних значень
            List<Integer> maxIndices = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == max) {
                    maxIndices.add(i);
                }
            }

            //Виведення максимального значення, кількість максимальних значень та їхні індекси
            System.out.println("Максимальне значення: " + max);
            System.out.println("Кількість максимальних значень: " + count);
            System.out.println("Індекси максимальних значень: " + maxIndices);
        }
    }
}