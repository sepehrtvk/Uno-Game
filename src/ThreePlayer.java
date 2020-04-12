import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThreePlayer extends UnoGame {

    private ArrayList<Card> playerOneCards;
    private ArrayList<Card> playerTwoCards;
    private ArrayList<Card> playerThreeCards;
    private ArrayList<Card> onTableCards;

    private Card lastCard;

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
                lastCard = cards.get(rand);
                cards.remove(cards.get(rand));
                break;
            }
        }
        players = new ArrayList<Player>();
        players.add(new Player("player1", playerOneCards));
        players.add(new Player("player2", playerTwoCards));
        players.add(new Player("player3", playerThreeCards));
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
        lastCard.printCard();

        if (lastCard instanceof MovementCard) {
            MovementCard movementCard = (MovementCard) lastCard;
            if (movementCard.getMoveType().equals("REVERSE")) {
                setRotate("Anti-Clock-Wise");
            }
        }
        while (true) {
            System.out.println(getRotate());

            players.get(0).printPlayer();
            players.get(0).printPlayerCards();
            int choosedCard = 0;

            while (true) {
                if (players.get(0).skip) break;
                System.out.println("Player " + players.get(0).name + " please choose one card :");
                choosedCard = scanner.nextInt();

                if (checkCard(choosedCard, players.get(0), lastCard,0)) break;
                else System.out.println("wrong card !");


            }

            printOnTableCards();
            System.out.println(getRotate());
            players.get(1).printPlayer();

            System.out.println("Player " + players.get(1).name + " please choose one card :");
            while (true) {
                if (players.get(1).skip) break;

                choosedCard = random.nextInt(players.get(1).playerCards.size()) + 1;

                if (checkCard(choosedCard, players.get(1), lastCard,1)) break;


            }
            printOnTableCards();
            System.out.println(getRotate());
            players.get(2).printPlayer();
            System.out.println("Player " + players.get(2).name + " please choose one card :");
            while (true) {

                if (players.get(2).skip) break;

                choosedCard = random.nextInt(players.get(2).playerCards.size()) + 1;

                if (checkCard(choosedCard, players.get(2), lastCard,2)) break;


            }
            System.out.println();

            lastCard = onTableCards.get(onTableCards.size() - 1);
            printOnTableCards();
            displayScoreBooard();
            lastCard.printCard();
            for (int i = 0; i < onTableCards.size(); i++) {
                onTableCards.remove(i);
            }

            System.out.println("Enter 1 to continue the game : ");
            scanner.nextInt();
        }
    }

    public boolean checkCard(int choosedCard, Player player, Card lastCard,int index) {
        if (lastCard.color.equals(player.playerCards.get(choosedCard - 1).color)) {
            onTableCards.add(player.playerCards.get(choosedCard - 1));
            player.playerCards.remove(choosedCard - 1);
            return true;
        }
        if (player.playerCards.get(choosedCard - 1) instanceof WildCard) {
            for (Card card : player.playerCards) {
                if (card.color.equals(lastCard)) return false;
                if (card instanceof NumericalCard && lastCard instanceof NumericalCard) {
                    NumericalCard nc1 = (NumericalCard) lastCard;
                    NumericalCard nc2 = (NumericalCard) card;
                    if (nc1.getNumber() == nc2.getNumber()) return false;
                }
                if (card instanceof MovementCard && lastCard instanceof MovementCard) {
                    MovementCard movementCard1 = (MovementCard) lastCard;
                    MovementCard movementCard2 = (MovementCard) card;
                    if (movementCard1.getMoveType().equals(movementCard2.getMoveType())) return false;
                }
            }
            onTableCards.add(player.playerCards.get(choosedCard - 1));
            player.playerCards.remove(choosedCard - 1);
            return true;
        }

        if (lastCard instanceof NumericalCard && player.playerCards.get(choosedCard - 1) instanceof NumericalCard) {
            NumericalCard nc1 = (NumericalCard) lastCard;
            NumericalCard nc2 = (NumericalCard) player.playerCards.get(choosedCard - 1);
            if (nc1.getNumber() == nc2.getNumber()) {
                onTableCards.add(nc2);
                player.playerCards.remove(choosedCard - 1);
                return true;
            }

        }
        if (lastCard instanceof MovementCard && player.playerCards.get(choosedCard - 1) instanceof MovementCard) {
            MovementCard movementCard1 = (MovementCard) lastCard;
            MovementCard movementCard2 = (MovementCard) player.playerCards.get(choosedCard - 1);
            if (movementCard1.getMoveType().equals(movementCard2.getMoveType())) {
                checkMovementCard(player.playerCards.get(choosedCard - 1), index);
                onTableCards.add(player.playerCards.get(choosedCard - 1));
                player.playerCards.remove(choosedCard - 1);
                return true;
            }
        }
        return false;
    }

    public void printOnTableCards() {
        System.out.println("On table cards are : ");
        for (Card card : onTableCards) {
            card.printCard();
        }
    }


    public void checkMovementCard(Card cardToCheck, int indexOfPlayer) {
        MovementCard movementCard = (MovementCard) cardToCheck;
        if (movementCard.getMoveType().equals("Draw2+")) {
            if (getRotate().equals("Clock-Wise")) {
                int rand1 = random.nextInt(cards.size());
                int rand2 = random.nextInt(cards.size());
                if (indexOfPlayer == 2) {
                    players.get(0).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(0).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                } else {
                    players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                }
            } else {
                int rand1 = random.nextInt(cards.size());
                int rand2 = random.nextInt(cards.size());
                if (indexOfPlayer == 0) {
                    players.get(1).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(1).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                } else {
                    players.get(indexOfPlayer - 1).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(indexOfPlayer - 1).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                }
            }
        }
        if (movementCard.getMoveType().equals("Skip")) {
            if (getRotate().equals("Clock-Wise")) {
                if (indexOfPlayer == 2) {
                    players.get(0).skip = true;
                } else {
                    players.get(indexOfPlayer + 1).skip = true;
                }

            } else {
                if (indexOfPlayer == 0) {
                    players.get(1).skip = true;
                } else {
                    players.get(indexOfPlayer - 1).skip = true;
                }
            }
        }

    }

    public void displayScoreBooard() {
        for (Player player : players) {
            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("| player name : " + player.name + " | Score : " + player.score + " |");
            System.out.println("--------------------------------------");

        }

    }

}
