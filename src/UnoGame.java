import java.util.ArrayList;

public class UnoGame {

    protected ArrayList<Card> cards;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String Black = "\u001B[40m";
    public static final String Red = "\u001B[41m";
    public static final String Green = "\u001B[42m";
    public static final String Yellow = "\u001B[43m";
    public static final String Blue = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String RESET = "\u001B[0m";


    public UnoGame() {

        cards = new ArrayList<Card>(108);

        for (int i = 0; i < 5; i++) {

            String color = " ";
            if (i == 0) color = "Red";
            if (i == 1) color = "Yellow";
            if (i == 2) color = "Green";
            if (i == 3) color = "Blue";

            for (int k = 0; k < 19; k++) {
                if (k < 10) {
                    NumericalCard numericalCard = new NumericalCard(color, k);
                    cards.add(numericalCard);
                } else {
                    NumericalCard numericalCard = new NumericalCard(color, 19 - k);
                    cards.add(numericalCard);
                }
            }
            for (int j = 0; j < 6; j++) {

                String type = " ";
                if (j == 0 || j == 3) type = "Skip";
                if (j == 1 || j == 4) type = "Draw2+";
                if (j == 2 || j == 5) type = "Reverse";

                MovementCard movementCard = new MovementCard(color, type);
                cards.add(movementCard);
            }

        }

        for (int i = 0; i < 4; i++) {

            WildCard wildCard = new WildCard("Black", "Draw4+");
            WildCard wildCard1 = new WildCard("Black", "NextColor");
            cards.add(wildCard);
            cards.add(wildCard1);

        }
    }

    public void printCard() {


        for (Card card : cards) {
            card.printCard();
        }
    }
}
