//Задано масив значень A={ai} з 10 елементів. Знайти максимальний і мінімальний елементи, поміняти їх місцями. 
public class Array1 {
    public static void main(String[] args) {
        // Створення масиву з 10 елементів
        int[] arr = new int[10];

        // Заповнення масиву випадковими числами від -50 до 50
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101) - 50;
        }

        // Виведення початкового масиву
        System.out.println("Початковий масив:");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Пошук мінімального та максимального елементів та їх індексів
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }

        // Виведення мінімального та максимального значень
        System.out.println("\nМінімальне значення: " + arr[minIndex]);
        System.out.println("Максимальне значення: " + arr[maxIndex]);

        // Обмін мінімального та максимального елементів місцями
        int temp = arr[minIndex];
        arr[minIndex] = arr[maxIndex];
        arr[maxIndex] = temp;

        // Виведення зміненого масиву
        System.out.println("\nМасив після обміну:");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}