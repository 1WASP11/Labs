import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Act1 {   
    public static void main(String[] args) throws IOException
{
    System.out.println("What's your name?");
    BufferedReader input = new BufferedReader(
            new InputStreamReader(System.in));
    String userName = input.readLine();
    System.out.println("Hello, " + userName + "!");
   
    int now = 2025;
    System.out.println("What's your age?");
    int age = Integer.parseInt(input.readLine());
    System.out.println("You was born in " + (now - age));
   }
}