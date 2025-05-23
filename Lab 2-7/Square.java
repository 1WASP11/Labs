public class Square { // Батьківський клас
    protected double side;

    // Конструктор класу
    public Square(double side) {
        this.side = side;
    }

    // Метод для обчислення площі
    public double getArea() {
        return side * side;
    }

    // Метод для обчислення периметру
    public double getPerimeter() {
        return 4 * side;
    }

    // Метод для виведення результатів обчислень в консоль
    @Override // Спеціальна анотація, яка повідомляє про перевизначення методу
    public String toString() {
        return String.format( 
            "%nSquare area is: %.2fsm%nSquare perimeter is: %.2fsm%n",
            getArea(), getPerimeter()
        );
    }
}