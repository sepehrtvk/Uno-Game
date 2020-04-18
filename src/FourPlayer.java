import java.util.ArrayList;
import java.util.Scanner;

public class FourPlayer extends ThreePlayer {

    private ArrayList<Card> playerFourCards;


    public FourPlayer() {
        super();
        playerFourCards = new ArrayList<Card>();
        for (int i = 0; i < 7; i++) {
            int rand = random.nextInt(cards.size());
            playerFourCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        players.add(new Player("Hosein", playerFourCards));
        players.get(3).calculateScore();
    }

    @Override
    public void reverseCard(int indexOfPlayer) {
        if(indexOfPlayer==0||indexOfPlayer==2){
            Player playerTemp1 = players.get(0);
            Player playerTemp2 = players.get(1);
            Player playerTemp3 = players.get(2);
            Player playerTemp4 = players.get(3);
            players.clear();
            players.add(playerTemp1);
            players.add(playerTemp4);
            players.add(playerTemp3);
            players.add(playerTemp2);
        }
        if(indexOfPlayer==1||indexOfPlayer==3){
            Player playerTemp1 = players.get(0);
            Player playerTemp2 = players.get(1);
            Player playerTemp3 = players.get(2);
            Player playerTemp4 = players.get(3);
            players.clear();
            players.add(playerTemp3);
            players.add(playerTemp2);
            players.add(playerTemp1);
            players.add(playerTemp4);

        }
    }

    public void startFourPlayerGame() {
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

            if (players.get(0).name.equals("Sepehr")) {
                giveCardChoose(players.get(0));
            } else {
                giveCardBot(players.get(0));
            }
            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }


            System.out.println(getRotate());

            if (players.get(1).name.equals("Sepehr")) {
                giveCardChoose(players.get(1));
            } else {
                giveCardBot(players.get(1));
            }


            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);

            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }

            System.out.println(getRotate());

            if (players.get(2).name.equals("Sepehr")) {
                giveCardChoose(players.get(2));
            } else {
                giveCardBot(players.get(2));
            }

            if (!nextColor.equals("null")) System.out.println("Next color is : " + nextColor);
            if (counter > 0) if (endGame()) {
                setWinner();
                System.out.println();
                break;
            }
            System.out.println(getRotate());

            if (players.get(3).name.equals("Sepehr")) {
                giveCardChoose(players.get(3));
            } else {
                giveCardBot(players.get(3));
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
