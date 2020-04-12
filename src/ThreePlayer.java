import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThreePlayer extends UnoGame {

    private ArrayList<Card> playerOneCards;
    private ArrayList<Card> playerTwoCards;
    private ArrayList<Card> playerThreeCards;
    private ArrayList<Card> onTableCards;

    private String rotate;

    private ArrayList<Player> players;

    Random random = new Random();


    public ThreePlayer() {
        playerOneCards = new ArrayList<Card>();
        playerTwoCards = new ArrayList<Card>();
        playerThreeCards = new ArrayList<Card>();
        onTableCards = new ArrayList<Card>();
        rotate = "Clock-Wise";

        for (int i = 0; i < 7; i++) {
            int bound = 108 - i - 1;
            int rand = random.nextInt(bound);
            playerOneCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        for (int i = 0; i < 7; i++) {
            int bound = 101 - i - 1;
            int rand = random.nextInt(bound);
            playerTwoCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        for (int i = 0; i < 7; i++) {
            int bound = 94 - i - 1;
            int rand = random.nextInt(bound);
            playerThreeCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        while (true) {
            int rand = random.nextInt(87);
            if (!cards.get(rand).color.equals("Black")) {
                onTableCards.add(cards.get(rand));
                cards.remove(cards.get(rand));
                break;
            }
        }
        players = new ArrayList<Player>();
        players.add(new Player("player1", playerOneCards, 1));
        players.add(new Player("player2", playerTwoCards, 2));
        players.add(new Player("player3", playerThreeCards, 3));
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public void startThreePlayerGame() {
        Scanner scanner = new Scanner(System.in);

        printOnTableCards();

        Player starter = players.get(random.nextInt(3));
        System.out.println("Player " + starter.name + " starts the game.");

        if (onTableCards.get(0) instanceof MovementCard) {
            MovementCard movementCard = (MovementCard) onTableCards.get(0);
            if (movementCard.getMoveType().equals("REVERSE")) {
                setRotate("Anti-Clock-Wise");
            }
        }
        System.out.println(getRotate());

        starter.printPlayer();
        starter.printPlayerCards();
        int choosedCard = 0;

        while (true) {

            System.out.println("Player " + starter.name + " please choose one card :");
            choosedCard = scanner.nextInt();

            if (checkCard(choosedCard, starter, 0)) break;
            else System.out.println("wrong card !");


        }

        printOnTableCards();

    }


    public boolean checkCard(int choosedCard, Player player, int index) {
        if (onTableCards.get(index).color.equals(player.playerCards.get(choosedCard - 1).color)) {
            onTableCards.add(player.playerCards.get(choosedCard - 1));
            player.playerCards.remove(choosedCard - 1);
            return true;
        }
        if (onTableCards.get(index) instanceof NumericalCard && player.playerCards.get(choosedCard - 1) instanceof NumericalCard) {
            NumericalCard nc1 = (NumericalCard) onTableCards.get(index);
            NumericalCard nc2 = (NumericalCard) player.playerCards.get(choosedCard - 1);
            if (nc1.getNumber() == nc2.getNumber()) {
                onTableCards.add(nc2);
                player.playerCards.remove(choosedCard - 1);
                return true;
            }

        }
        if (onTableCards.get(index) instanceof MovementCard && player.playerCards.get(choosedCard - 1) instanceof MovementCard) {
            MovementCard movementCard1 = (MovementCard) onTableCards.get(index);
            MovementCard movementCard2 = (MovementCard) player.playerCards.get(choosedCard - 1);
            if (movementCard1.getMoveType().equals(movementCard2.getMoveType())) {
                onTableCards.add(player.playerCards.get(choosedCard - 1));
                player.playerCards.remove(choosedCard - 1);
                return true;
            }
        }
        if (player.playerCards.get(choosedCard - 1) instanceof WildCard) {
            for (Card card : player.playerCards) {
                if (card.color.equals(onTableCards.get(index))) return false;
                if (card instanceof NumericalCard && onTableCards.get(index) instanceof NumericalCard) {
                    NumericalCard nc1 = (NumericalCard) onTableCards.get(index);
                    NumericalCard nc2 = (NumericalCard) card;
                    if (nc1.getNumber() == nc2.getNumber()) return false;
                }
                if (card instanceof MovementCard && onTableCards.get(index) instanceof MovementCard) {
                    MovementCard movementCard1 = (MovementCard) onTableCards.get(index);
                    MovementCard movementCard2 = (MovementCard) card;
                    if (movementCard1.getMoveType().equals(movementCard2.getMoveType())) return false;
                }
            }
            onTableCards.add(player.playerCards.get(choosedCard - 1));
            player.playerCards.remove(choosedCard - 1);
        }
        return false;
    }

    public void printOnTableCards() {
        for (Card card : onTableCards) {
            card.printCard();
        }
    }
}
