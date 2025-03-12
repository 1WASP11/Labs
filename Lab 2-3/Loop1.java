import java.util.Scanner;

public class Loop1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            double alpha, beta, y;
            
            // Введення значення β
            System.out.print("Введіть значення β: ");
            beta = sc.nextDouble();
            
            // Цикл для α від 0 до 0.5 з кроком 0.1
            for (alpha = 0.0; alpha <= 0.5; alpha += 0.1) {
                y = Math.pow(Math.sin(alpha + beta), 2);
                
                // Виведення результату
                System.out.println("α = " + alpha + "\t y = " + y);
            }
            
            sc.close();
        }
    }
}
