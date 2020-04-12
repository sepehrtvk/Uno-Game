import java.util.ArrayList;
import java.util.Random;

public class Player  {

    protected String name;
    protected ArrayList<Card> playerCards;
    private int score;
    protected boolean skip;

    public Player(String name, ArrayList<Card> playerCards) {
        this.name = name;
        skip=false;
        this.playerCards = playerCards;
        for (Card card : playerCards) {
            if (card instanceof NumericalCard) {
                NumericalCard numericalCard = (NumericalCard) card;
                score += numericalCard.getNumber();
            }
            if (card instanceof MovementCard) {
                score += 20;
            }
            if (card instanceof WildCard) {
                score += 50;
            }
        }
    }

    public void printPlayer() {
        System.out.println("Player name : " + name);
        System.out.println("Number of cards : " + playerCards.size());
        System.out.println("Score : " + score);
    }
    public void printPlayerCards(){
        int i =1;
        for (Card card : playerCards) {

            System.out.println(i+") ");
            card.printCard();
            i++;
        }

    }
}
