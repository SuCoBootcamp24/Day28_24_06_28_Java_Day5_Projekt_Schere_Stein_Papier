import java.util.Random;
import java.util.Scanner;

public class SchereSteinPapier {

    //avoid magic numbers
    private static final int SHEARS = 0;
    private static final int STONE = 1;
    private static final int PAPER = 2;
    private static final int GAMEEND = 3;

    private static int counter = 0;
    private static int playerScore = 0;
    private static int compScore = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int menuSet = gameMenu(scanner);
                if (menuSet == GAMEEND)
                    break;

                System.out.println("Deine Wahl: " + showSelectedObject(menuSet));
                gameLogic(menuSet);
                counter++;
            }

            System.out.println("Spiel wurde beendet.\n");
            finalWin();
        }
    }

    private static int gameMenu(Scanner scanner) {
        System.out.println("\nWähle aus:");
        System.out.println(SHEARS + " = Schere, " + STONE + " = Stein, " + PAPER + " = Papier " + GAMEEND + " = Beenden");
        int set = -1;

        while (true) {
            try {
                set = scanner.nextInt();
                if (set == SHEARS || set == STONE || set == PAPER || set == GAMEEND) {
                    break;
                } else {
                    System.out.println("Ungültige Spielereingabe");
                }
            } catch (Exception e) {
                System.out.println("Bitte nur Zahlen eingeben");
                scanner.next();
            }
        }
        return set;
    }

    private static void gameLogic(int playerObj) {
        int compObj = randomObject();
        System.out.println("Wahl des Computers: " + showSelectedObject(compObj));

        if (playerObj == compObj) {
            System.out.println("\nUentschieden");

        } else if ((playerObj == SHEARS && compObj == PAPER) ||
                (playerObj == STONE && compObj == SHEARS) ||
                (playerObj == PAPER && compObj == STONE)) {

            System.out.println("\nSpieler gewinnt");
            playerScore++;
        } else {
            System.out.println("\nComputer gewinnt");
            compScore++;
        }
    }

   
    private static int randomObject() {
        Random random = new Random();
        int[] options = {SHEARS, STONE, PAPER};
        return options[random.nextInt(options.length)];
    }

    private static String showSelectedObject(int obj) {
        String objName = "";

        switch (obj) {
            case SHEARS:
                objName = "Schere";
                break;
            case STONE:
                objName = "Stein";
                break;
            case PAPER:
                objName = "Papier";
                break;
        }
       return objName;
    }

    private static void finalWin() {
        if (playerScore == compScore) {
            System.out.println("Es wurde ein Unentschieden in der Gesamtwertung erreicht.");
        } else if (playerScore > compScore) {
            System.out.println("Spieler gewinnt in der Gesamtwertung.");
        } else {
            System.out.println("Computer gewinnt in der Gesamtwertung.");
        }

        System.out.println("Gesamtscore: Spieler: " + playerScore + " Computer: " + compScore + " Punkten");
        System.out.println("\nDu hast den Computer insgesamt " + counter + " mal herrausgefordert.");
    }
}
