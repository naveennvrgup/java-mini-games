import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class BullsCows {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int secretLen, posSymbols;
        try {
            System.out.println("Input the length of the secret code:");
            secretLen = scanner.nextInt();
            System.out.println("Input the number of possible symbols in the code:");
            posSymbols = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error");
            return;
        }

        if (posSymbols > 36 || secretLen > 36 || secretLen > posSymbols || secretLen==0) {
            System.out.println("Error");
            return;
        }

        StringBuilder secretPrepared = new StringBuilder();
        secretPrepared.append("The secret is prepared: ");
        secretPrepared.append(Character.toString('*').repeat(secretLen));
        if (posSymbols > 10) {
            secretPrepared.append(String.format(" (0,9, a-%c).\n", (char) ('a' + posSymbols - 11)));
        } else {
            secretPrepared.append(String.format(" (0,%d).\n", posSymbols - 1));
        }
        System.out.println(secretPrepared.toString());


        String secretNum = getRandomStart(secretLen, posSymbols);

        System.out.println("Okay, let's start a game!");
        startGame(secretNum);
        System.out.println("Congratulations! You guessed the secret code.");
    }

    static void startGame(String secretNum) {

        int cows = 0, bulls = 0;
        int turnCount = 1;

        while (bulls != secretNum.length()) {
            cows = 0;
            bulls = 0;

            System.out.printf("Turn %d:\n", turnCount);
            String userInput = scanner.next();
            for (int i = 0; i < userInput.length(); i++) {
                int index = secretNum.indexOf(userInput.charAt(i));
                if (index == i) bulls++;
                else if (index >= 0) cows++;
            }

            if (bulls > 0 && cows > 0) System.out.printf("Grade: %d bull(s) and %d cow(s)\n", bulls, cows);
            else if (bulls > 0) System.out.printf("Grade: %d bull(s)\n", bulls);
            else if (cows > 0) System.out.printf("Grade: %d cow(s)\n", cows);
            else System.out.printf("Grade: None\n");
        }
    }

    static String getRandomStart(int secretLen, int posSymbols) {
        StringBuilder secret = new StringBuilder();
        Random random = new Random();
        HashSet<Integer> mem = new HashSet<>();

        while (secret.length() < secretLen) {
            int curr = random.nextInt(36);
            if (curr + 1 > posSymbols || mem.contains(curr)) continue;
            mem.add(curr);

            if (curr <= 9) {
                secret.append(curr);
            } else {
                secret.append((char) ('a' + curr - 10));
            }
        }

        return secret.toString();
    }
}
