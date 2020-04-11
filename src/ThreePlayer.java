import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThreePlayer extends UnoGame {

    private ArrayList<Card> playerOneCards;
    private ArrayList<Card> playerTwoCards;
    private ArrayList<Card> playerThreeCards;
    private ArrayList<Card> onTabelCrads;

    private ArrayList<Player> players;

    Random random = new Random();


    public ThreePlayer() {
        playerOneCards = new ArrayList<Card>();
        playerTwoCards = new ArrayList<Card>();
        playerThreeCards = new ArrayList<Card>();
        onTabelCrads = new ArrayList<Card>();

        for (int i = 0; i < 7; i++) {
            int bound = 108-i-1;
            int rand = random.nextInt(bound);
            playerOneCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        for (int i = 0; i < 7; i++) {
            int bound = 101-i-1;
            int rand = random.nextInt(bound);
            playerTwoCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        for (int i = 0; i < 7; i++) {
            int bound = 94-i-1;
            int rand = random.nextInt(bound);
            playerThreeCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        while (true) {
            int rand = random.nextInt(87);
            if (!cards.get(rand).color.equals("Black")) {
                onTabelCrads.add(cards.get(rand));
                cards.remove(cards.get(rand));
                break;
            }
        }
        players = new ArrayList<Player>();
        players.add(new Player("player1", playerOneCards));
        players.add(new Player("player2", playerTwoCards));
        players.add(new Player("player3", playerThreeCards));
    }

    public void startThreePlayerGame() {
        Scanner scanner = new Scanner(System.in);
        onTabelCrads.get(0).printCard();
        Player starter = players.get(random.nextInt(3));
        System.out.println("Player " + starter.name + " starts the game.");
        System.out.println("Clock-Wise");
        for (int i = 0; i < starter.playerCards.size(); i++) {
            System.out.println((i + 1) + ") ");
            starter.playerCards.get(i).printCard();
        }

        int choosedCard = 0;

        while (true) {

            System.out.println("Player " + starter.name + " please choose one card :");
            choosedCard = scanner.nextInt();

            if (onTabelCrads.get(0).color.equals(starter.playerCards.get(choosedCard - 1).color)) {
                onTabelCrads.add(starter.playerCards.get(choosedCard - 1));
                starter.playerCards.remove(choosedCard - 1);
                break;
            }
            if (onTabelCrads.get(0) instanceof NumericalCard && starter.playerCards.get(choosedCard - 1) instanceof NumericalCard) {
                NumericalCard nc1 = (NumericalCard) onTabelCrads.get(0);
                NumericalCard nc2 = (NumericalCard) starter.playerCards.get(choosedCard - 1);
                if (nc1.getNumber() == nc2.getNumber()){
                    onTabelCrads.add(nc2);
                    starter.playerCards.remove(choosedCard-1);
                    break;
                }

            }
            if(onTabelCrads.get(0) instanceof MovementCard && starter.playerCards.get(choosedCard-1) instanceof MovementCard){
                onTabelCrads.add(starter.playerCards.get(choosedCard-1));
                starter.playerCards.remove(choosedCard-1);
                break;
            }
            System.out.println("wrong card !");


        }
        for(Card card : onTabelCrads){
            card.printCard();
        }


    }
}
