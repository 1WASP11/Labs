public class Triangle extends GeometricFigure{ // Дочірній клас батьківського абстрактного класу GeometricalFigure
    private double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Метод для виводу сторін трикутника
    public String getSides() {
        return a + ", " + b + ", " + c;
    }
    
    // Метод для обчислення площі
    @Override
    public double getArea() {
        double s = getPerimeter() / 2; // півпериметр
        return Math.sqrt(s * (s - a) * (s - b) * (s - c)); // формула Герона
    }

    // Метод для обчислення периметра
    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    // Метод для виведення результатів обчислень в консоль
    @Override
    public String toString() {
        return String.format (
            "Triangle sides is: %s%nTriangle area is: %.2fsm%nTriangle perimeter is: %.2fsm",
            getSides(), getArea(), getPerimeter()
        );
    }
}
