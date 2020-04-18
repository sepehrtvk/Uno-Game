import java.util.ArrayList;
import java.util.Scanner;

public class FivePlayer extends ThreePlayer {

    private ArrayList<Card> playerFiveCards;


    public FivePlayer() {
    }

    public void startFivePlayerGame(){

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
        int counter = 0;
        while (true) {

            System.out.println(getRotate());

            System.out.println("Last card is :");
            lastCard.printCard();

            if (players.get(0).name.equals("You")) {
                giveCardYou(players.get(0));
            } else {
                giveCard(players.get(0));
            }
            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }


            System.out.println(getRotate());

            if (players.get(1).name.equals("You")) {
                giveCardYou(players.get(1));
            } else {
                giveCard(players.get(1));
            }


            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);

            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }

            System.out.println(getRotate());

            if (players.get(2).name.equals("You")) {
                giveCardYou(players.get(2));
            } else {
                giveCard(players.get(2));
            }

            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }
            System.out.println(getRotate());

            if (players.get(3).name.equals("You")) {
                giveCardYou(players.get(3));
            } else {
                giveCard(players.get(3));
            }

            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }


            System.out.println();
            displayScoreBoard();
            counter++;
            System.out.println("Enter 1 to play next round : ");
            scanner.nextInt();
        }




    }

}
