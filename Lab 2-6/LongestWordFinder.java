import java.util.Scanner;

//Дано текст. Визначити найдовше слово

public class LongestWordFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Введення тексту
        System.out.println("Введіть текст:");
        String text = scanner.nextLine();
        scanner.close();

        //Створення зміних для подальшого пошуку найдовшого слова, його довжини та маніпулювання з поточним словом
        String longestWord = "";
        int maxLength = 0;
        String currentWord = "";

        //Цикл для проходження по кожному елементу тексту
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                currentWord += currentChar;
            } else {
                if (currentWord.length() > maxLength) {
                    maxLength = currentWord.length();
                    longestWord = currentWord;
                }
                currentWord = "";
            }
        }

        // Перевірка останнього слова після закінчення циклу
        if (currentWord.length() > maxLength) {
            longestWord = currentWord;
        }

        if (!longestWord.isEmpty()) {
            System.out.println("Найдовше слово: " + longestWord);
        } else {
            System.out.println("У тексті немає слів.");
        }
    }
}