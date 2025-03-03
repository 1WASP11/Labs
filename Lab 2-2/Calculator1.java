import java.util.Scanner;

public class Calculator1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Введення значення α
        System.out.print("Введіть значення α (в радіанах): ");
        double alpha = sc.nextDouble();

        // Обчислення z1
        double numerator = Math.sin(2 * alpha) + Math.sin(5 * alpha) - Math.sin(3 * alpha);
        double denominator = Math.cos(alpha) - Math.cos(3 * alpha) + Math.cos(5 * alpha);
        double z1 = numerator / denominator;

        // Обчислення z2
        double z2 = Math.tan(3 * alpha);

        // Виведення результатів
        System.out.println("Z1 = " + z1);
        System.out.println("Z2 = " + z2);

        sc.close();
    }
}
