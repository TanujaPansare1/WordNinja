import java.util.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class Textutils {

    // Count Characters
    public static int countCharacters(String text) {
        return text.length();
    }

    // Count Words
    public static int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    // Count Sentences
    public static int countSentences(String text) {
        String[] sentences = text.split("[.!?]");
        return sentences.length;
    }

    // Convert Text to Uppercase
    public static String convertToUppercase(String text) {
        return text.toUpperCase();
    }

    // Convert Text to Lowercase
    public static String convertToLowercase(String text) {
        return text.toLowerCase();
    }

    // Convert Text to Title Case
    public static String convertToTitleCase(String text) {
        String[] words = text.split("\\s+");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                titleCase.append(word.substring(0, 1).toUpperCase())
                          .append(word.substring(1).toLowerCase())
                          .append(" ");
            }
        }
        return titleCase.toString().trim();
    }

    // Convert Text to Sentence Case
    public static String convertToSentenceCase(String text) {
        if (text == null || text.isEmpty()) return text;
        text = text.toLowerCase();
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    // Remove Special Characters
    public static String removeSpecialCharacters(String text) {
        return text.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    // Remove Extra Spaces
    public static String removeExtraSpaces(String text) {
        return text.replaceAll("\\s+", " ").trim();
    }

    // Copy Text to Clipboard
    public static void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    // Display Text Utilities Menu
    public static void displayMenu() {
        System.out.println("\nText Utilities Menu:");
        System.out.println("1. Count Characters");
        System.out.println("2. Count Words");
        System.out.println("3. Count Sentences");
        System.out.println("4. Convert to Uppercase");
        System.out.println("5. Convert to Lowercase");
        System.out.println("6. Convert to Title Case");
        System.out.println("7. Convert to Sentence Case");
        System.out.println("8. Remove Special Characters");
        System.out.println("9. Remove Extra Spaces");
        System.out.println("10. Copy Text to Clipboard");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText;
        int choice;
        String continueOption;

        System.out.println("Enter the text to work with:");
        inputText = scanner.nextLine();

        // Display the menu only once
        displayMenu();

        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Character Count: " + countCharacters(inputText));
                    break;
                case 2:
                    System.out.println("Word Count: " + countWords(inputText));
                    break;
                case 3:
                    System.out.println("Sentence Count: " + countSentences(inputText));
                    break;
                case 4:
                    System.out.println("Uppercase: " + convertToUppercase(inputText));
                    break;
                case 5:
                    System.out.println("Lowercase: " + convertToLowercase(inputText));
                    break;
                case 6:
                    System.out.println("Title Case: " + convertToTitleCase(inputText));
                    break;
                case 7:
                    System.out.println("Sentence Case: " + convertToSentenceCase(inputText));
                    break;
                case 8:
                    System.out.println("Remove Special Characters: " + removeSpecialCharacters(inputText));
                    break;
                case 9:
                    System.out.println("Remove Extra Spaces: " + removeExtraSpaces(inputText));
                    break;
                case 10:
                    copyToClipboard(inputText);
                    System.out.println("Text copied to clipboard!");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            // Ask the user if they want to continue or exit
            if (choice != 0) {
                System.out.print("Do you want to perform another operation? (yes/no): ");
                continueOption = scanner.nextLine();
            } else {
                continueOption = "no";
            }

        } while (continueOption.equalsIgnoreCase("yes"));

        scanner.close();
    }
}
