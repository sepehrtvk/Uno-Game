import java.util.ArrayList;
import java.util.Random;

public class Player  {

    protected String name;
    protected ArrayList<Card> playerCards;
    protected int score;
    private boolean skip;
    private boolean winner;

    public Player(String name, ArrayList<Card> playerCards) {
        this.name = name;
        skip=false;
        this.playerCards = playerCards;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public void printPlayer() {
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

        System.out.println("Player name : " + name);
        System.out.println("Number of cards : " + playerCards.size());
        System.out.println("Score : " + score);
        if(score==0)winner=true;
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
