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
            int rand = random.nextInt(cards.size());
            playerOneCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        for (int i = 0; i < 7; i++) {
            int rand = random.nextInt(cards.size());
            playerTwoCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        for (int i = 0; i < 7; i++) {
            int rand = random.nextInt(cards.size());
            playerThreeCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }
        while (true) {
            int rand = random.nextInt(cards.size());
            if (!cards.get(rand).color.equals("Black")) {
                lastCard = cards.get(rand);
                cards.remove(cards.get(rand));
                onTableCards.add(lastCard);
                break;
            }
        }
        players = new ArrayList<Player>();
        players.add(new Player("You", playerOneCards));
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
        while (true) {

            boolean player1;
            boolean player2 ;
            boolean player3 ;

            System.out.println(getRotate());

            System.out.println("Last card is :");
            lastCard.printCard();

            if (players.get(0).name.equals("You")) {
                giveCardYou(players.get(0));
                player1 = true;
            } else {
                giveCard(players.get(0));
                player1 = true;
            }
            printOnTableCards();

            if(onTableCards.size()!=0)
            lastCard = onTableCards.get(onTableCards.size() - 1);

            System.out.println(getRotate());

            if (players.get(1).name.equals("You")) {
                giveCardYou(players.get(1));
                player2 = true;
            } else{
                giveCard(players.get(1));
                player2 = true;
            }


            printOnTableCards();
            if(onTableCards.size()!=0)
                lastCard = onTableCards.get(onTableCards.size() - 1);
            System.out.println(getRotate());

            if (players.get(2).name.equals("You")) {
                giveCardYou(players.get(2));
                player3 = true;
            } else{
                giveCard(players.get(2));
                player3 = true;
            }

            System.out.println();

            if(onTableCards.size()!=0)
            lastCard = onTableCards.get(onTableCards.size() - 1);
            if (player1 && player2 && player3) {
                for (int j = 0; j < onTableCards.size(); j++) {
                    onTableCards.remove(j);
                }
            }
            displayScoreBoard();
//            System.out.println();
//            lastCard.printCard();
//            if (rotate.equals("Clock-Wise")) i++;
//            else i--;
//            if (i > 2 && rotate.equals("Clock-Wise")) {
//                for (int j = 0; j < onTableCards.size(); j++) {
//                    onTableCards.remove(j);
//                }
//
//                i = 0;
//            }
//            if (i < 0 && rotate.equals("Anti-Clock-Wise")) {
//                for (int j = 0; j < onTableCards.size(); j++) {
//                    onTableCards.remove(j);
//                }
//                i = 0;
//            }

        }
    }

    public void giveCard(Player player) {
        System.out.println("Player " + player.name + " please choose one card :");
        player.printPlayer();
        if (player.isSkip()) {
            System.out.println("! PLAYER BLOCKED !");
            return;
        }
        int counter = 0;
        while (true) {
            int choosedCard = random.nextInt(player.playerCards.size());
            if (checkCard(player.playerCards.get(choosedCard), player, lastCard)) break;
            counter++;
            if (counter > player.playerCards.size()) {
                int rand = random.nextInt(cards.size());
                player.playerCards.add(cards.get(rand));
                cards.remove(rand);
                System.out.println("One card added to player " + player.name + " from repository.");
                break;

            }
        }
        player.setSkip(false);
    }

    public void giveCardYou(Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean b = false;
        System.out.println("Player " + player.name + " please choose one card :");
        player.printPlayer();
        if (player.isSkip()) {
            System.out.println("! PLAYER BLOCKED !");
            return;
        }
        player.printPlayerCards();

        while (true) {
            if (player.isSkip()) break;

//            for (int i = 0; i < player.playerCards.size(); i++) {
//                if (checkCard(player.playerCards.get(i), player, lastCard)) {
//                    b = true;
//                    break;
//                }
//            }
//            if (!b) {
//                player.playerCards.add(cards.get(random.nextInt(cards.size())));
//                System.out.println("One card added to player " + player.name + " from repository.");
//                break;
//            }

            int choosedCard = scanner.nextInt();

            if (checkCard(player.playerCards.get(choosedCard - 1), player, lastCard)) break;
            else {
                System.out.println("wrong card !");
            }
        }
        player.setSkip(false);
    }

    public boolean checkCard(Card cardToCheck, Player player, Card lastCard) {


        if (lastCard instanceof NumericalCard && cardToCheck instanceof NumericalCard) {
            NumericalCard nc1 = (NumericalCard) lastCard;
            NumericalCard nc2 = (NumericalCard) cardToCheck;
            return checkNumericalCard(player, nc2, nc1);
        }

        if (lastCard instanceof MovementCard && cardToCheck instanceof MovementCard) {
            MovementCard movementCard1 = (MovementCard) lastCard;
            MovementCard movementCard2 = (MovementCard) cardToCheck;
            return checkMovementCard(player, movementCard2, movementCard1);
        }
        if (cardToCheck instanceof WildCard) {
            WildCard wildCard = (WildCard) cardToCheck;
            return checkWildCard(player, wildCard, lastCard);
        }
        if (lastCard.color.equals(cardToCheck.color)) {
            return true;
        }

        return false;
    }

    public boolean checkNumericalCard(Player player, NumericalCard playerCard, NumericalCard lastCard) {
        if (lastCard.color.equals(playerCard.color) || lastCard.getNumber() == playerCard.getNumber()) {
            onTableCards.add(playerCard);
            player.playerCards.remove(playerCard);
            return true;
        }
        return false;
    }

    public boolean checkMovementCard(Player player, MovementCard playerCard, MovementCard lastCard) {
        if (lastCard.color.equals(playerCard.color) || lastCard.getMoveType().equals(playerCard.getMoveType())) {
            onTableCards.add(playerCard);
            player.playerCards.remove(playerCard);
            int indexOfPlayer = players.indexOf(player);
            if (playerCard.getMoveType().equals("Skip")) {
                if (getRotate().equals("Clock-Wise")) {
                    if (indexOfPlayer == 2) {
                        players.get(0).setSkip(true);
                    } else {
                        players.get(indexOfPlayer + 1).setSkip(true);
                    }
                } else {
                    if (indexOfPlayer == 0) {
                        players.get(2).setSkip(true);
                    } else {
                        players.get(indexOfPlayer - 1).setSkip(true);
                    }
                }
                return true;
            }
            if (playerCard.getMoveType().equals("Reverse")) {
                if (rotate.equals("Clock-Wise"))
                    setRotate("Anti-Clock-Wise");
                else setRotate("Clock-Wise");
                if (indexOfPlayer == 0) {
                    Player playerTemp1 = players.get(1);
                    Player playerTemp2 = players.get(2);
                    players.set(2, playerTemp1);
                    players.set(1, playerTemp2);
                }
                if (indexOfPlayer == 1) {
                    Player playerTemp1 = players.get(0);
                    Player playerTemp2 = players.get(2);
                    players.set(2, playerTemp1);
                    players.set(0, playerTemp2);
                }
                if (indexOfPlayer == 2) {
                    Player playerTemp1 = players.get(0);
                    Player playerTemp2 = players.get(1);
                    players.set(1, playerTemp1);
                    players.set(0, playerTemp2);
                }
                return true;
            }
            if (playerCard.getMoveType().equals("Draw2+")) {
                int rand1 = random.nextInt(cards.size());
                int rand2 = random.nextInt(cards.size());
                if (indexOfPlayer == 0) {
                    players.get(1).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(1).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                    System.out.println("2 cards added to player " + players.get(1).name);
                }
                if (indexOfPlayer == 1) {
                    players.get(2).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(2).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                    System.out.println("2 cards added to player " + players.get(2).name);
                }
                if (indexOfPlayer == 2) {
                    players.get(0).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(0).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                    System.out.println("2 cards added to player " + players.get(0).name);
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkWildCard(Player player, WildCard playerCard, Card lastCard) {
        Scanner scanner = new Scanner(System.in);
        for (Card card : player.playerCards) {
            if (card instanceof NumericalCard && lastCard instanceof NumericalCard) {
                NumericalCard numericalCard = (NumericalCard) card;
                NumericalCard numericalCard2 = (NumericalCard) lastCard;
                if (numericalCard.color.equals(lastCard.color) || numericalCard.getNumber() == numericalCard2.getNumber())
                    return false;
            }
            if (card instanceof MovementCard && lastCard instanceof MovementCard) {
                MovementCard movementCard = (MovementCard) card;
                MovementCard movementCard2 = (MovementCard) lastCard;
                if (movementCard.color.equals(lastCard.color) || movementCard.getMoveType().equals(movementCard2.getMoveType()))
                    return false;
            }
        }
        int indexOfPlayer = players.indexOf(player);
        int rand1 = random.nextInt(cards.size());
        int rand2 = random.nextInt(cards.size());
        int rand3 = random.nextInt(cards.size());
        int rand4 = random.nextInt(cards.size());
        if (playerCard.getWildType().equals("Draw4+")) {
            System.out.println("Please enter next Color : ");
            String color = scanner.next();
            if (indexOfPlayer == 0) {
                players.get(1).setSkip(true);
                players.get(1).playerCards.add(cards.get(rand1));
                players.get(1).playerCards.add(cards.get(rand2));
                players.get(1).playerCards.add(cards.get(rand3));
                players.get(1).playerCards.add(cards.get(rand4));
                cards.remove(rand1);
                cards.remove(rand2);
                cards.remove(rand3);
                cards.remove(rand4);
            }
            if (indexOfPlayer == 1) {
                players.get(2).setSkip(true);
                players.get(2).playerCards.add(cards.get(rand1));
                players.get(2).playerCards.add(cards.get(rand2));
                players.get(2).playerCards.add(cards.get(rand3));
                players.get(2).playerCards.add(cards.get(rand4));
                cards.remove(rand1);
                cards.remove(rand2);
                cards.remove(rand3);
                cards.remove(rand4);
            }
            if (indexOfPlayer == 2) {
                players.get(0).setSkip(true);
                players.get(0).playerCards.add(cards.get(rand1));
                players.get(0).playerCards.add(cards.get(rand2));
                players.get(0).playerCards.add(cards.get(rand3));
                players.get(0).playerCards.add(cards.get(rand4));
                cards.remove(rand1);
                cards.remove(rand2);
                cards.remove(rand3);
                cards.remove(rand4);
            }
            onTableCards.add(playerCard);
            player.playerCards.remove(playerCard);
            return true;

        }
        if (playerCard.getWildType().equals("NextColor")) {
            System.out.println("Please enter next Color : ");
            String color = scanner.next();

            onTableCards.add(playerCard);
            player.playerCards.remove(playerCard);

            return true;
        }
        return false;
    }

    public void printOnTableCards() {
        System.out.println("On table cards are : ");
        for (Card card : onTableCards) {
            card.printCard();
        }
    }


//    public void checkMovementCard(MovementCard movementCard, Player player) {
//        int indexOfPlayer = players.indexOf(player);
//        System.out.println(indexOfPlayer);
//        if (movementCard.getMoveType().equals("Draw2+")) {
//            if (getRotate().equals("Clock-Wise")) {
//                int rand1 = random.nextInt(cards.size());
//                int rand2 = random.nextInt(cards.size());
//                if (indexOfPlayer == 2) {
//                    players.get(0).playerCards.add(cards.get(rand1));
//                    cards.remove(cards.get(rand1));
//                    players.get(0).playerCards.add(cards.get(rand2));
//                    cards.remove(cards.get(rand2));
//                    System.out.println("2 cards added to player " + players.get(0).name);
//                } else {
//                    players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand1));
//                    cards.remove(cards.get(rand1));
//                    players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand2));
//                    cards.remove(cards.get(rand2));
//                    System.out.println("2 cards added to player " + players.get(indexOfPlayer + 1).name);
//                }
//            } else {
//                int rand1 = random.nextInt(cards.size());
//                int rand2 = random.nextInt(cards.size());
//                if (indexOfPlayer == 0) {
//                    players.get(2).playerCards.add(cards.get(rand1));
//                    cards.remove(cards.get(rand1));
//                    players.get(2).playerCards.add(cards.get(rand2));
//                    cards.remove(cards.get(rand2));
//                    System.out.println("2 cards added to player " + players.get(2).name);
//
//                } else {
//                    players.get(indexOfPlayer - 1).playerCards.add(cards.get(rand1));
//                    cards.remove(cards.get(rand1));
//                    players.get(indexOfPlayer - 1).playerCards.add(cards.get(rand2));
//                    cards.remove(cards.get(rand2));
//                    System.out.println("2 cards added to player " + players.get(indexOfPlayer - 1).name);
//
//                }
//            }
//        }
//        if (movementCard.getMoveType().equals("Skip")) {
//            if (getRotate().equals("Clock-Wise")) {
//                if (indexOfPlayer == 2) {
//                    players.get(0).setSkip(true);
//                } else {
//                    players.get(indexOfPlayer + 1).setSkip(true);
//                }
//
//            } else {
//                if (indexOfPlayer == 0) {
//                    players.get(2).setSkip(true);
//                } else {
//                    players.get(indexOfPlayer - 1).setSkip(true);
//                }
//            }
//        }
//        if (movementCard.getMoveType().equals("Reverse")) {
//            if (rotate.equals("Clock-Wise"))
//                setRotate("Anti-Clock-Wise");
////            else setRotate("Clock-Wise");
////            if (indexOfPlayer == 0) {
////                Player playerTemp1 = players.get(1);
////                Player playerTemp2 = players.get(2);
////                players.set(2, playerTemp1);
////                players.set(1, playerTemp2);
////            }
////            if (indexOfPlayer == 1) {
////                Player playerTemp1 = players.get(0);
////                Player playerTemp2 = players.get(2);
////                players.set(2, playerTemp1);
////                players.set(0, playerTemp2);
////            }
////            if (indexOfPlayer == 2) {
////                Player playerTemp1 = players.get(0);
////                Player playerTemp2 = players.get(1);
////                players.set(1, playerTemp1);
////                players.set(0, playerTemp2);
////            }
////
//        }
//    }

    public void displayScoreBoard() {
        for (Player player : players) {
            System.out.println();
            System.out.println("--------------------------------------");
            System.out.println("| player name : " + player.name + " | Score : " + player.score + " |");
            System.out.println("--------------------------------------");

        }

    }

}
