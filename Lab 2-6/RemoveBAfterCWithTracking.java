import java.util.Arrays;
import java.util.Scanner;

public class RemoveBAfterCWithTracking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Введення початкового тексту
        System.out.println("Type your text: ");
        String text = sc.nextLine();
        System.out.println();
        sc.close();

        // Розбиваємо текст на слова
        String[] words = text.split("\\s+"); // "\\s+" розділяє по одному або кількох пробілах
        String[] originalWords = Arrays.copyOf(words, words.length); // Зберігаємо копію оригінальних слів
        StringBuilder finalResult = new StringBuilder();

        // Обробка кожного слова
        for (int i = 0; i < words.length; i++) {
            StringBuilder wordBuilder = new StringBuilder();
            String currentWord = words[i];

            for (int j = 0; j < currentWord.length(); j++) {
                if (currentWord.charAt(j) == 'b' && j > 0 && currentWord.charAt(j - 1) == 'c') {
                    continue;
                }
                wordBuilder.append(currentWord.charAt(j));
            }
            words[i] = wordBuilder.toString(); // Оновлюємо слово в масиві

            finalResult.append(words[i]);
            if (i < words.length - 1) {
                finalResult.append(" "); // Додаємо пробіл між словами
            }
        }

        // Виведення обробленого тексту
        System.out.println("Processed text: \n" + finalResult.toString());
        System.out.println("\nChanges in words:");

        // Порівняння оригінальних і змінених слів
        for (int i = 0; i < originalWords.length; i++) {
            if (!originalWords[i].equals(words[i])) {
                System.out.println("Original: \"" + originalWords[i] + "\", Changed to: \"" + words[i] + "\"");
            }
        }
    }
}