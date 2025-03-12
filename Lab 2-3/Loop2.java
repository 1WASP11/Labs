import java.util.Scanner;

public class Loop2 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            //Оголошення змінних
            double a, b, x, y, z;
            
            //Введення значень x
            System.out.print("Введіть значення x: ");
            x = sc.nextDouble();

            //Введення значень b
            System.out.print("Введіть значення b: ");
            b = sc.nextDouble();

            //Оголошення змінних MAX та xmax
            double MAX, xmax;
            a = 1;
            z = Math.pow(x, 5) + a * x + Math.pow(b, 3);
            y = Math.pow(Math.cos(z + a), 3) - x;
            MAX = y; xmax = a;
            //Створення циклу
            for (a = 1; a <= 2; a = a + 0.2) {
                z = Math.pow(x, 5) + a * x + Math.pow(b, 3);
                y = Math.pow(Math.cos(z + a), 3) - x;

            //Виведення значень на екран
            System.out.println("z="+z+"  y="+y+"  a="+a);

            if(z>MAX) {
                MAX=y; xmax=a;
            }
            
            //Виведення значень на екран
            System.out.println("MAX="+MAX+"  xmax="+xmax);
        }
        }
    }
}