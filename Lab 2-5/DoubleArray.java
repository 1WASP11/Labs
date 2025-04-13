import java.util.Scanner;
/*До двовимірного масиву X, що складається із n рядків та m стовпців
однорідних змінних, вводяться довільні числа. Треба підрахувати та вивести у
вигляді вектора (одновимірного масиву) середні арифметичні додатних
значень елементів кожного стовпця цього масиву.*/
public class DoubleArray {
    public static void main(String[] args){
        try (Scanner sc = new Scanner(System.in)){
            
            //Введення кільсть рядків (n) та кількість стовпців (m)
            System.out.print("Input n: ");
            int n = sc.nextInt();
            System.out.print("Input m: ");
            int m = sc.nextInt();

            //Створення двовимірного масиву
            int[][] X = new int [n][m];
            
            //Заповнення масиву випадковими числами від -10 до 10
            System.out.println("Your arr X:");
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    X[i][j] = (int) (Math.random() * 20) -10;
                    System.out.print(X[i][j] + "\t");
                }
                System.out.println();
            }

            //Створення масиву для підрахунку середніх значень
            double[] averages = new double[m];

            //Підрахунок середніх значень
            for(int j = 0; j < m; j++){
                int sum = 0;//Змінна sum буде зберігати суму всих додатніх чисел у стовпці
                int count = 0;//Змінна count буде рахувати кількість додатніх чисел у стовпці

                for(int i = 0; i < n; i++){//Внутрішній цикл, який проходить по рядках (i) цього конкретного стовпця j
                    if(X[i][j] > 0){
                        sum += X[i][j];//Додає значення до суми
                        count++;//Рахує кількість елементів
                    }
                }
                if(count > 0){//Перевіряє чи були додатні числа. Якщо так то підраховується середнє значення (averages)
                    averages[j] = (double) sum / count;
                } else {//Якщо не було, то повертає 0
                    averages[j] = 0;
                }
            }

            //Виведення значень масиву середніх значень
            System.out.print("Averages values:\n[");
                for(int j = 0; j < m; j++){
                    System.out.printf("%.2f ", averages[j]);
                }
            System.out.print("]");
        }
    }
}