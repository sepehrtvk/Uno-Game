import java.util.ArrayList;
import java.util.Random;

public class Player extends ThreePlayer {

    protected String name;
    protected ArrayList<Card> playerCards;
    private int score;
    private int turn;

    public Player(String name, ArrayList<Card> playerCards,int turn) {
        this.name = name;
        this.turn=turn;
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
    public Card giveCrad(Player player){
        Random random = new Random();
        while (true){
            int rand = random.nextInt(player.playerCards.size());
            if(checkCard(rand,player,0))return player.playerCards.get(rand);break;
        }
        return player.playerCards.get(0);
    }
}
