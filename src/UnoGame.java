import java.util.ArrayList;

public class UnoGame {

    private ArrayList<Card> cards;


    public UnoGame() {

        cards = new ArrayList<Card>(108);

        for (int i = 0; i < 19; i++) {
            if (i < 10) {
                NumericalCard numericalCard = new NumericalCard("Red", i);
                cards.add(numericalCard);
            } else {
                NumericalCard numericalCard = new NumericalCard("Red", 19 - i);
                cards.add(numericalCard);
            }
        }
        for (int i = 0; i < 19; i++) {
            if (i < 10) {
                NumericalCard numericalCard = new NumericalCard("Yellow", i);
                cards.add(numericalCard);
            } else {
                NumericalCard numericalCard = new NumericalCard("Yellow", 19 - i);
                cards.add(numericalCard);
            }
        }
        for (int i = 0; i < 19; i++) {
            if (i < 10) {
                NumericalCard numericalCard = new NumericalCard("Green", i);
                cards.add(numericalCard);
            } else {
                NumericalCard numericalCard = new NumericalCard("Green", 19 - i);
                cards.add(numericalCard);
            }
        }
        for (int i = 0; i < 19; i++) {
            if (i < 10) {
                NumericalCard numericalCard = new NumericalCard("Blue", i);
                cards.add(numericalCard);
            } else {
                NumericalCard numericalCard = new NumericalCard("Blue", 19 - i);
                cards.add(numericalCard);
            }
        }

        
    }
}
