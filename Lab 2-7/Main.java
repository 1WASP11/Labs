import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Введення довжини сторони квадрата
        System.out.println("Enter square side value in sm: ");
        double side = sc.nextDouble();
        
        //Введення кількості сторін правильного багатокутника
        System.out.println("Enter count of regular polygon faces: ");
        int faces = sc.nextInt();

        //Введення радіусу кола
        System.out.println("Enter radius value in sm: ");
        double radius = sc.nextDouble();

        //Введення довжин сторін трикутника
        System.out.println("Enter a side value in sm: ");
        double a = sc.nextDouble();
        System.out.println("Enter b side value in sm: ");
        double b = sc.nextDouble();
        System.out.println("Enter c side value in sm: ");
        double c = sc.nextDouble();
        sc.close();

        //Створюємо масив батьківського класу з обʼєктами або батьківського (Square), або дочірнього (RegularPolygon) класів
        Square[] sq = new Square[]{
            new Square(side),
            new RegularPolygon(side, faces)
        };

        //Створюємо масив батьківського абстрактного класу з обʼєктами дочірніх класів Circle та Triangle
        GeometricFigure[] fg = new GeometricFigure[] {
            new Circle(radius),
            new Triangle(a, b, c)
        };

        // Виводимо площу та периметр квадрата та правильного багатокутника
        for (Square s : sq) {
            System.out.println(s);
        }

        // Виводимо радіус кола, сторони трикутника, їхні площі та периметри
        for(GeometricFigure f : fg) {
            System.out.println(f);
        }
    }
}