import java.util.Random;
import java.util.Scanner;

public class SchereSteinPapier {

    //avoid magic numbers
    private static final int SHEARS = 0;
    private static final int STONE = 1;
    private static final int PAPER = 2;
    private static final int GAMEEND = 3;

    public static boolean gameStart = true;
    private static int counter = 0;
    private static int playerScore = 0;
    private static int compScore = 0;
    private static int winnerLastRound = -2;
    private static int seriesCounter = 1;

    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("Willkommen bei Schere, Stein, Papier! das Kultspiel.");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("");
                int menuSet = gameMenu(scanner);
                if (menuSet == GAMEEND)
                    break;

                System.out.println("Deine Wahl: " + showSelectedObject(menuSet));

                int compObj = 2; //randomObject();
                System.out.println("Wahl des Computers: " + showSelectedObject(compObj));
        
                int roundWinner = gameLogic(menuSet, compObj);

                System.out.println((checkSeries(roundWinner)) ? choiceWinner(roundWinner) + " hat sich soeben einen Pluspunkt verdient, durch eine 3er Win-Serie. Glückwunsch" : "");

               
                System.out.println("");
                System.out.println((status() == 0) ?  "Derzeit herscht ein Unentschieden in der Gesamtwertung." : choiceWinner(status()) + " führt zurzeit in der Gesamtwertung.");
                System.out.println("\nGesamtscore: Spieler: " + playerScore + " Computer: " + compScore + " Punkten");

                counter++;
                gameStart = false;
            }

            System.out.println("Spiel wurde beendet.\n");


            System.out.println((status() == 0) ?  "Es wurde ein Unentschieden in der Gesamtwertung erreicht." : choiceWinner(status()) + " gewinnt in der Gesamtwertung.");
            System.out.println("\nGesamtscore: Spieler: " + playerScore + " Computer: " + compScore + " Punkten");
            System.out.println("\nDu hast den Computer insgesamt " + counter + " mal herrausgefordert.");
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

    private static int gameLogic(int playerObj, int compObj) {
    
        if (playerObj == compObj) {
            System.out.println("\nUnentschieden");
            return 0;

        } else if ((playerObj == SHEARS && compObj == PAPER) ||
                (playerObj == STONE && compObj == SHEARS) ||
                (playerObj == PAPER && compObj == STONE)) {

            System.out.println("\nSpieler gewinnt");
            playerScore++;
            return 1;
        } else {
            System.out.println("\nComputer gewinnt");
            compScore++;
            return -1;
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
    private static int status() {
        if (playerScore == compScore) {
            return 0; // draw
        } else if (playerScore > compScore) {
            return 1; //player win
        } else {
            return -1; //computer win
        }
    }

    private static String choiceWinner(int winner) {
        switch (winner) {
            case -1:
                return "Der Computer";
            case 1:
                return "Der Spieler";
            default:
                return "";
        }
    }

    private static boolean checkSeries(int roundWinner) {

        // return true = Series reached, false = no series
        if (gameStart) {
            winnerLastRound = roundWinner;
            return false;
        }
        
        
        if ((roundWinner == winnerLastRound) && roundWinner != 0) {
            if (seriesCounter == 2) {
                if (roundWinner == -1) compScore++;
                else playerScore++;

                seriesCounter = 1;
                return true;
            } else seriesCounter++;

        } else {
            seriesCounter = 1;
            winnerLastRound = roundWinner;
        }
        return false;

    }

}

