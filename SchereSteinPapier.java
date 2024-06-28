import java.util.Scanner;

public class SchereSteinPapier {

    private static final int SHEARS = 0;
    private static final int STONE = 1;
    private static final int PAPER = 2;
    private static final int GAMEEND = 3;

    private static int playerScore = 0;
    private static int compScore = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int menuSet = gameMenu(scanner);
                if (menuSet == GAMEEND)
                    break;
                versus(menuSet);
            }

            System.out.println("Spiel wurde beendet.\n");
            finalWin();
        }
    }

    private static int gameMenu(Scanner scanner) {
        System.out.println("\nWähle aus:");
        System.out
                .println(SHEARS + " = Schere, " + STONE + " = Stein, " + PAPER + " = Papier " + GAMEEND + " = Beenden");

        int set = -1;

        while (true) {
            try {
                set = scanner.nextInt();
                if (set >= SHEARS && set <= GAMEEND) {
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

    private static int randomObject() {
        return (int) (Math.random() * (3));
    }

    private static void versus(int playerObj) {
        int compObj = randomObject();

        if (playerObj == compObj) {
            System.out.println("\nuUentschieden   P: " + playerObj + " C: " + compObj);
        } else if ((playerObj == SHEARS && compObj == PAPER) ||
                (playerObj == STONE && compObj == SHEARS) ||
                (playerObj == PAPER && compObj == STONE)) {

            System.out.println("\nSpieler gewinnt   P: " + playerObj + " C: " + compObj);
            playerScore++;
        } else {
            System.out.println("\nComputer gewinnt   P: " + playerObj + " C: " + compObj);
            compScore++;
        }
    }

    private static void finalWin() {

        if (playerScore == compScore) {
            System.out.println("Es wurde ein Unendschieden in der Gesamtwertung erreicht.");
        } else if (playerScore > compScore) {
            System.out.println("Spieler gewinnt in der Gesamtwertung.");
        } else {
            System.out.println("Computer gewinnt in der Gesamtwertung.");
        }

        System.out.println("Gesamtscore: Spieler: " + playerScore + " Computer: " + compScore + " punkten");

    }
}
