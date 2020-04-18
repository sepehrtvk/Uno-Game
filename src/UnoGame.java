import java.util.ArrayList;

/**
 * UNO game is a board game that includes 100 card in 4 different colors and 8 Wild cards.
 * in each round the player has to put a card on table ( the card should be similar in color or
 * number or move ) and the first player whose cards finishes , wins the game.
 *
 * @author sepehr tavakoli
 * @version 1.0
 * @since 2020.04.10
 */

public class UnoGame {

    //all cards in each game.
    protected ArrayList<Card> cards;

    //colorful strings.
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     * this constructor prepares a uno game with 108 cards.
     */
    public UnoGame() {

        //all the cards.
        cards = new ArrayList<Card>(108);

        for (int i = 0; i < 4; i++) {

            //set colors.
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

                //set moves.
                String type = " ";
                if (j == 0 || j == 3) type = "Skip";
                if (j == 1 || j == 4) type = "Draw2+";
                if (j == 2 || j == 5) type = "Reverse";

                MovementCard movementCard = new MovementCard(color, type);
                cards.add(movementCard);
            }

        }

        for (int i = 0; i < 4; i++) {

            //set wild cards.
            WildCard wildCard = new WildCard("Black", "Draw4+");
            WildCard wildCard1 = new WildCard("Black", "NextColor");
            cards.add(wildCard);
            cards.add(wildCard1);

        }
    }

}

