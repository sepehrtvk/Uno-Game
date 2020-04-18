import java.util.ArrayList;
import java.util.Scanner;

/**
 * the FivePlayer class makes a uno game with 5 players.
 * each player have 7 cards at first.
 * a random card should be put on the table and then game stars.
 * this class inherits from FourPlayer class.
 *
 * @author sepehr tavakoli
 * @version 1.0
 * @since 2020.04.10
 */

public class FivePlayer extends FourPlayer {

    //cards of player 5 .
    private ArrayList<Card> playerFiveCards;

    /**
     * this constructor makes a FivePlayer game with 7 cards for each player.
     */

    public FivePlayer() {

        super();
        playerFiveCards = new ArrayList<Card>();

        //adding 7 cards to  player 5 .
        for (int i = 0; i < 7; i++) {
            int rand = random.nextInt(cards.size());
            playerFiveCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }

        //create player 5 .
        players.add(new Player("Sina", playerFiveCards));

        //calculate score of each player.
        players.get(4).calculateScore();
    }

    /**
     * override the reverseCard method in class ThreePlayer.
     *
     * @param indexOfPlayer index of player in the list.
     */
    @Override
    public void reverseCard(int indexOfPlayer) {
        if (indexOfPlayer == 0) {
            Player playerTemp1 = players.get(0);
            Player playerTemp2 = players.get(1);
            Player playerTemp3 = players.get(2);
            Player playerTemp4 = players.get(3);
            Player playerTemp5 = players.get(4);
            players.clear();
            players.add(playerTemp1);
            players.add(playerTemp5);
            players.add(playerTemp4);
            players.add(playerTemp3);
            players.add(playerTemp2);
        }
        if (indexOfPlayer == 1) {
            Player playerTemp1 = players.get(0);
            Player playerTemp2 = players.get(1);
            Player playerTemp3 = players.get(2);
            Player playerTemp4 = players.get(3);
            Player playerTemp5 = players.get(4);
            players.clear();
            players.add(playerTemp3);
            players.add(playerTemp2);
            players.add(playerTemp1);
            players.add(playerTemp5);
            players.add(playerTemp4);
        }
        if (indexOfPlayer == 2 || indexOfPlayer == 4) {
            Player playerTemp1 = players.get(0);
            Player playerTemp2 = players.get(1);
            Player playerTemp3 = players.get(2);
            Player playerTemp4 = players.get(3);
            Player playerTemp5 = players.get(4);
            players.clear();
            players.add(playerTemp5);
            players.add(playerTemp4);
            players.add(playerTemp3);
            players.add(playerTemp2);
            players.add(playerTemp1);
        }
        if (indexOfPlayer == 3) {
            Player playerTemp1 = players.get(0);
            Player playerTemp2 = players.get(1);
            Player playerTemp3 = players.get(2);
            Player playerTemp4 = players.get(3);
            Player playerTemp5 = players.get(4);
            players.clear();
            players.add(playerTemp2);
            players.add(playerTemp1);
            players.add(playerTemp5);
            players.add(playerTemp4);
            players.add(playerTemp3);
        }
    }

    public void startFivePlayerGame() {

        //first card if be movement card.
        if (lastCard instanceof MovementCard) {

            MovementCard movementCard = (MovementCard) lastCard;
            if (movementCard.getMoveType().equals("Reverse")) {
                setRotate("Anti-Clock-Wise");
            }

            if (movementCard.getMoveType().equals("Skip")) {
                players.get(0).setSkip(true);
            }

            if (movementCard.getMoveType().equals("Draw2+")) {
                int rand1 = random.nextInt(cards.size());
                int rand2 = random.nextInt(cards.size());
                players.get(0).playerCards.add(cards.get(rand1));
                players.get(0).playerCards.add(cards.get(rand2));
                cards.remove(rand1);
                cards.remove(rand2);
                players.get(0).setSkip(true);
            }
        }
        Scanner scanner = new Scanner(System.in);

        //count the number of round.
        int counter = 0;

        while (true) {

            //show rotate mode.
            System.out.println(getRotate());

            //show last card.
            System.out.println("Last card is :");
            lastCard.printCard();

            if (players.get(0).name.equals("Sepehr")) {
                giveCardChoose(players.get(0));
            } else {
                giveCardBot(players.get(0));
            }

            //next color.
            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);

            //check end game.
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }

            //show rotate mode.
            System.out.println(getRotate());

            if (players.get(1).name.equals("Sepehr")) {
                giveCardChoose(players.get(1));
            } else {
                giveCardBot(players.get(1));
            }

            //next color.
            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);

            //check end game.
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }

            //show rotate mode.
            System.out.println(getRotate());

            if (players.get(2).name.equals("Sepehr")) {
                giveCardChoose(players.get(2));
            } else {
                giveCardBot(players.get(2));
            }

            //next color.
            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);

            //check end game.
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }

            //show rotate mode.
            System.out.println(getRotate());

            if (players.get(3).name.equals("Sepehr")) {
                giveCardChoose(players.get(3));
            } else {
                giveCardBot(players.get(3));
            }

            //next color.
            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);

            //check end game.
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }

            //show rotate mode.
            System.out.println(getRotate());

            if (players.get(4).name.equals("Sepehr")) {
                giveCardChoose(players.get(4));
            } else {
                giveCardBot(players.get(4));
            }

            //next color.
            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);

            //check end game.
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }


            System.out.println();

            //show score board.
            displayScoreBoard();

            counter++;

            //enter next round.
            System.out.println("Enter 1 to play next round : ");
            scanner.nextInt();
        }


    }

}
