import java.util.Scanner;

public class Calculator2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x, y, d, a, b;
        System.out.print("input x (для y): ");
        x = sc.nextDouble();
        System.out.print("input d (для y): ");
        d = sc.nextDouble();
        if (x > 2.3) {
            y = 2.3;
        } else {
            y = d - (x * x) / 2;
        }
        System.out.println(" y=" + y);

        System.out.print("input a (для x): ");
        a = sc.nextDouble();
        System.out.print("input b (для x): ");
        b = sc.nextDouble();
        if (b < 0) {
            x = Math.pow(a, 5) + b;
        } else if (b >= 0 && b < 1.5 && a != b) {
            x = Math.pow(a, 2) + a / (a - b);
        } else if (b >= 1.5 && b < 2.5) {
            x = (Math.pow(a, 3) + b) / (Math.pow(a, 2) + Math.pow(b, 2));
        } else {
            x = Math.pow(a, 4);
        }
        System.out.println(" x=" + x);
        sc.close();
    }
}