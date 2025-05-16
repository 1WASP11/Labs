public class RegularPolygon extends Square{ // Дочірній клас від батьківського класу Square
    private int faces; // кількість сторін

    public RegularPolygon(double side, int faces) { // Конструктор
        super(side); // Приймає змінну side від батьківського класу
        this.faces = faces;
    }

    // Метод для обчислення площі
    @Override
    public double getArea() {
        return (faces * side * side) / (4 * Math.tan(Math.PI / faces));
    }

    // Метод для обчислення периметра
    @Override
    public double getPerimeter() {
        return faces * side;
    }

    // Метод для виведення результатів обчислень в консоль
    @Override
    public String toString() {
        return String.format(
        "The area of a regular polygon with %d faces is: %.2fsm%nThe perimeter is: %.2fsm%n",
        faces, getArea(), getPerimeter()
        );
    }
}