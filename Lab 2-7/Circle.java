public class Circle extends GeometricFigure{ // Дочірній клас абстрактного батьківського класу GeometricFigure
    private double radius;

    // Конструктор класу
    public Circle(double radius) {
        this.radius = radius;
    }

    // Метод для виводу радіусу
    public double getRadius() {
        return radius;
    }

    // Метод для обчислення площі
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Метод для обчислення периметру
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    // Метод для виведення результатів обчислень в консоль
    @Override
    public String toString() {
        return String.format (
            "Circle radius is: %.2f%nCircle area is: %.2fsm%nCircle perimeter is: %.2fsm%n",
            getRadius(), getArea(), getPerimeter()
        );
    }
}