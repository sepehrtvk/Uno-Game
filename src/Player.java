import java.util.ArrayList;

/**
 * the Player class represents some players for each game set.
 *
 * @author sepehr tavakoli
 * @version 1.0
 * @since 2020.04.10
 */

public class Player {

    //name of the player.
    String name;

    //cards of the player.
    ArrayList<Card> playerCards;

    //score of the player.
    int score;

    //if skip is true , player cannot play.
    private boolean skip;

    //if the player wins , this winner become true.
    private boolean winner;

    /**
     * this constructor makes a player with the given name and cards.
     *
     * @param name        name of the player.
     * @param playerCards all the cards of the player.
     */
    public Player(String name, ArrayList<Card> playerCards) {
        this.name = name;
        skip = false;
        this.playerCards = playerCards;
        winner = false;
    }

    /**
     * get winner.
     *
     * @return true if the player wins the game.
     */
    public boolean isWinner() {
        return winner;
    }

    /**
     * set winner.
     *
     * @param winner to show the player wins the game.
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    /**
     * show that a player blocked or not.
     *
     * @return true if player blocked.
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     * set the player block.
     *
     * @param skip show that a player blocked or not.
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     * this printPlayer method prints player score , name and number of cards.
     */
    public void printPlayer() {

        calculateScore();
        System.out.println("Player name : " + name);
        System.out.println("Number of cards : " + playerCards.size());
        System.out.println("Score : " + score);
        if (score == 0) winner = true;
    }

    /**
     * this method prints all of the player cards.
     */
    public void printPlayerCards() {
        calculateScore();
        int i = 1;
        for (Card card : playerCards) {

            System.out.println(i + ") ");
            card.printCard();
            i++;
        }
    }

    /**
     * this calculateScore method  calculates the score of each player.
     */
    public void calculateScore() {
        score = 0;
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
        if (score == 0) winner = true;

    }
}
