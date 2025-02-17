import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Дано довжини ребер A, B, C прямокутного паралелепіпеда. Знайти його об’єм та площу поверхні.

public class Act2 {   
    public static void main(String[] args) throws IOException
{
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in));
    System.out.println("Lenght of the edge a is:");
    float a = Float.parseFloat(reader.readLine());
    System.out.println("Lenght of the edge b is:");
    float b = Float.parseFloat(reader.readLine());
    System.out.println("Lenght of the edge c is:");
    float c = Float.parseFloat(reader.readLine());

    float V = a*b*c;
    float S = 2*((a*b)+(b*c)+(a*c));

    System.out.println("Volume of a rectangular parallelogram is " + V + " sm^3");
    System.out.println("Area of a rectangular parallelogram is " + S + " sm^2");
   }
}