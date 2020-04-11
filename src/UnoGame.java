import java.util.ArrayList;

public class UnoGame {

    private ArrayList<Card> cards;


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
                    NumericalCard numericalCard = new NumericalCard(color, i);
                    cards.add(numericalCard);
                } else {
                    NumericalCard numericalCard = new NumericalCard(color, 19 - i);
                    cards.add(numericalCard);
                }
            }
            for (int j = 0; j < 3; j++) {

                String type = " ";
                if (j == 0) type = "Skip";
                if (j == 1) type = "Draw";
                if (j == 2) type = "Reverse";

                MovementCard movementCard = new MovementCard(color, type);
                cards.add(movementCard);
            }

        }

    }
}
