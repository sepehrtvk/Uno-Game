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
    private String nextColor;

    private ArrayList<Player> players;

    Random random = new Random();


    public ThreePlayer() {
        playerOneCards = new ArrayList<Card>();
        playerTwoCards = new ArrayList<Card>();
        playerThreeCards = new ArrayList<Card>();
        onTableCards = new ArrayList<Card>();
        rotate = "Clock-Wise";
        nextColor = "null";

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

            System.out.println(getRotate());

            System.out.println("Last card is :");
            lastCard.printCard();

            if (players.get(0).name.equals("You")) {
                giveCardYou(players.get(0));
            } else {
                giveCard(players.get(0));
            }
            printOnTableCards();
            if(endGame())break;


            System.out.println(getRotate());

            if (players.get(1).name.equals("You")) {
                giveCardYou(players.get(1));
            } else {
                giveCard(players.get(1));
            }


            printOnTableCards();
            if(endGame())break;
            System.out.println(getRotate());

            if (players.get(2).name.equals("You")) {
                giveCardYou(players.get(2));
            } else {
                giveCard(players.get(2));
            }

            printOnTableCards();
            if(endGame())break;

            System.out.println();
            for (int j = 0; j < onTableCards.size(); j++) {
                onTableCards.remove(j);

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
        player.setSkip(false);
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
    }

    public void giveCardYou(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + player.name + " please choose one card :");
        player.printPlayer();
        if (player.isSkip()) {
            System.out.println("! PLAYER BLOCKED !");
            return;
        }
        player.setSkip(false);
        player.printPlayerCards();

        boolean b = false;
        for (Card playerCard : player.playerCards) {
            if (lastCard instanceof NumericalCard && playerCard instanceof NumericalCard) {
                NumericalCard nc1 = (NumericalCard) lastCard;
                NumericalCard nc2 = (NumericalCard) playerCard;
                if (lastCard.color.equals(playerCard.color) || nc1.getNumber() == nc2.getNumber()) {
                    b = true;
                    break;
                }
            }
            if (lastCard instanceof MovementCard && playerCard instanceof MovementCard) {
                MovementCard movementCard1 = (MovementCard) playerCard;
                MovementCard movementCard2 = (MovementCard) lastCard;
                if (movementCard1.color.equals(movementCard2.color) || movementCard1.getMoveType().equals(movementCard2.getMoveType())) {
                    b = true;
                    break;
                }
            }
            if (playerCard instanceof WildCard) {
                b = true;
                break;
            }
            if (lastCard.color.equals(playerCard.color)) {
                b = true;
                break;
            }
        }
        if (!b) {
            int rand = random.nextInt(cards.size());
            player.playerCards.add(cards.get(rand));
            cards.remove(rand);
            System.out.println("One card added to player " + player.name + " from repository.");
        } else {

            while (true) {
                int choosedCard = scanner.nextInt();
                if (checkCard(player.playerCards.get(choosedCard - 1), player, lastCard)) break;
                else {
                    System.out.println("wrong card !");
                }
            }
        }
    }


    public boolean checkCard(Card cardToCheck, Player player, Card lastCardOnTable) {

        if (nextColor.equals("null")) {

            if (lastCardOnTable instanceof NumericalCard && cardToCheck instanceof NumericalCard) {
                NumericalCard nc1 = (NumericalCard) lastCardOnTable;
                NumericalCard nc2 = (NumericalCard) cardToCheck;
                return checkNumericalCard(player, nc2, nc1);
            }

            if (lastCardOnTable instanceof MovementCard && cardToCheck instanceof MovementCard) {
                MovementCard movementCard1 = (MovementCard) lastCardOnTable;
                MovementCard movementCard2 = (MovementCard) cardToCheck;
                return checkMovementCard(player, movementCard2, movementCard1);
            }
            if (cardToCheck instanceof WildCard) {
                WildCard wildCard = (WildCard) cardToCheck;
                return checkWildCard(player, wildCard, lastCardOnTable);
            }
            if (lastCardOnTable.color.equals(cardToCheck.color)) {
                System.out.println("Player "+player.name+" put a card on table. ");
                onTableCards.add(cardToCheck);
                lastCard = cardToCheck;
                player.playerCards.remove(cardToCheck);
                return true;
            }
        } else if (nextColor.equals(cardToCheck.color)) {
            if (lastCardOnTable instanceof NumericalCard && cardToCheck instanceof NumericalCard) {
                NumericalCard nc1 = (NumericalCard) lastCardOnTable;
                NumericalCard nc2 = (NumericalCard) cardToCheck;
                return checkNumericalCard(player, nc2, nc1);
            }

            if (lastCardOnTable instanceof MovementCard && cardToCheck instanceof MovementCard) {
                MovementCard movementCard1 = (MovementCard) lastCardOnTable;
                MovementCard movementCard2 = (MovementCard) cardToCheck;
                return checkMovementCard(player, movementCard2, movementCard1);
            }
            if (cardToCheck instanceof WildCard) {
                WildCard wildCard = (WildCard) cardToCheck;
                return checkWildCard(player, wildCard, lastCardOnTable);
            }
            if (lastCardOnTable.color.equals(cardToCheck.color)) {
                System.out.println("Player "+player.name+" put a card on table. ");
                onTableCards.add(cardToCheck);
                lastCard = cardToCheck;
                player.playerCards.remove(cardToCheck);
                return true;
            }
        }
        nextColor="null";

        return false;
    }

    public boolean checkNumericalCard(Player player, NumericalCard playerCard, NumericalCard lastCardOnTable) {
        if (lastCard.color.equals(playerCard.color) || lastCardOnTable.getNumber() == playerCard.getNumber()) {
            System.out.println("Player "+player.name+" put a card on table. ");
            onTableCards.add(playerCard);
            lastCard = playerCard;
            player.playerCards.remove(playerCard);
            return true;
        }
        return false;
    }

    public boolean checkMovementCard(Player player, MovementCard playerCard, MovementCard lastCardOnTable) {
        if (lastCard.color.equals(playerCard.color) || lastCardOnTable.getMoveType().equals(playerCard.getMoveType())) {
            System.out.println("Player "+player.name+" put a card on table. ");
            onTableCards.add(playerCard);
            lastCard = playerCard;
            player.playerCards.remove(playerCard);
            int indexOfPlayer = players.indexOf(player);
            if (lastCardOnTable.getMoveType().equals("Skip")) {
                if (indexOfPlayer == 2) {
                    players.get(0).setSkip(true);
                }
                if (indexOfPlayer == 1) {
                    players.get(2).setSkip(true);
                }
                if (indexOfPlayer == 0) {
                    players.get(1).setSkip(true);
                }
                return true;
            }
            if (lastCardOnTable.getMoveType().equals("Reverse")) {
                System.out.println("---------------");
                if (rotate.equals("Clock-Wise"))
                    setRotate("Anti-Clock-Wise");
                else setRotate("Clock-Wise");
                if (indexOfPlayer == 0) {
                    Player playerTemp1 = players.get(0);
                    Player playerTemp2 = players.get(1);
                    Player playerTemp3 = players.get(2);
                    players.clear();
                    players.add(playerTemp1);
                    players.add(playerTemp3);
                    players.add(playerTemp2);
                }
                if (indexOfPlayer == 1) {
                    Player playerTemp1 = players.get(0);
                    Player playerTemp2 = players.get(1);
                    Player playerTemp3 = players.get(2);
                    players.clear();
                    players.add(playerTemp3);
                    players.add(playerTemp1);
                    players.add(playerTemp2);
                }
                if (indexOfPlayer == 2) {
                    Player playerTemp1 = players.get(0);
                    Player playerTemp2 = players.get(1);
                    Player playerTemp3 = players.get(2);
                    players.clear();
                    players.add(playerTemp2);
                    players.add(playerTemp1);
                    players.add(playerTemp3);
                }
                return true;
            }
            if (lastCardOnTable.getMoveType().equals("Draw2+")) {
                int rand1 = random.nextInt(cards.size());
                int rand2 = random.nextInt(cards.size());
                if (indexOfPlayer == 0) {
                    players.get(1).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(1).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                    System.out.println("2 cards added to player " + players.get(1).name);
                    players.get(1).setSkip(true);
                }
                if (indexOfPlayer == 1) {
                    players.get(2).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(2).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                    System.out.println("2 cards added to player " + players.get(2).name);
                    players.get(2).setSkip(true);
                }
                if (indexOfPlayer == 2) {
                    players.get(0).playerCards.add(cards.get(rand1));
                    cards.remove(cards.get(rand1));
                    players.get(0).playerCards.add(cards.get(rand2));
                    cards.remove(cards.get(rand2));
                    System.out.println("2 cards added to player " + players.get(0).name);
                    players.get(0).setSkip(true);

                }
                return true;
            }
        }
        return false;
    }

    public boolean checkWildCard(Player player, WildCard playerCard, Card lastCardOnTable) {
        Scanner scanner = new Scanner(System.in);
        for (Card card : player.playerCards) {
            if (card instanceof NumericalCard && lastCardOnTable instanceof NumericalCard) {
                NumericalCard numericalCard = (NumericalCard) card;
                NumericalCard numericalCard2 = (NumericalCard) lastCard;
                if (numericalCard.color.equals(lastCardOnTable.color) || numericalCard.getNumber() == numericalCard2.getNumber())
                    return false;
            }
            if (card instanceof MovementCard && lastCardOnTable instanceof MovementCard) {
                MovementCard movementCard = (MovementCard) card;
                MovementCard movementCard2 = (MovementCard) lastCardOnTable;
                if (movementCard.color.equals(lastCardOnTable.color) || movementCard.getMoveType().equals(movementCard2.getMoveType()))
                    return false;
            }
        }

        if (player.name.equals("You")) {
            System.out.println("Please enter next Color : ");
            nextColor = scanner.next();
            System.out.println("Player " + player.name + " Choosed color <" + nextColor + ">");
        } else {
            int rand = random.nextInt(4);
            switch (rand) {
                case 0:
                    nextColor = "Yellow";
                    System.out.println("Player " + player.name + " Choosed color <" + nextColor + ">");
                    break;
                case 1:
                    nextColor = "Blue";
                    System.out.println("Player " + player.name + " Choosed color <" + nextColor + ">");
                    break;
                case 2:
                    nextColor = "Red";
                    System.out.println("Player " + player.name + " Choosed color <" + nextColor + ">");
                    break;
                case 3:
                    nextColor = "Green";
                    System.out.println("Player " + player.name + " Choosed color <" + nextColor + ">");
                    break;
            }
        }
        System.out.println("Player "+player.name+" put a card on table. ");
        onTableCards.add(playerCard);
        lastCard = playerCard;
        player.playerCards.remove(playerCard);


        int indexOfPlayer = players.indexOf(player);
        if (playerCard.getWildType().equals("Draw4+")) {
            if (indexOfPlayer == 0) {
                players.get(1).setSkip(true);
                int rand1 = random.nextInt(cards.size());
                players.get(1).playerCards.add(cards.get(rand1));
                cards.remove(rand1);
                int rand2 = random.nextInt(cards.size());
                players.get(1).playerCards.add(cards.get(rand2));
                cards.remove(rand2);
                int rand3 = random.nextInt(cards.size());
                players.get(1).playerCards.add(cards.get(rand3));
                cards.remove(rand3);
                int rand4 = random.nextInt(cards.size());
                players.get(1).playerCards.add(cards.get(rand4));
                cards.remove(rand4);
            }
            if (indexOfPlayer == 1) {
                players.get(2).setSkip(true);
                int rand1 = random.nextInt(cards.size());
                players.get(2).playerCards.add(cards.get(rand1));
                cards.remove(rand1);
                int rand2 = random.nextInt(cards.size());
                players.get(2).playerCards.add(cards.get(rand2));
                cards.remove(rand2);
                int rand3 = random.nextInt(cards.size());
                players.get(2).playerCards.add(cards.get(rand3));
                cards.remove(rand3);
                int rand4 = random.nextInt(cards.size());
                players.get(2).playerCards.add(cards.get(rand4));
                cards.remove(rand4);
            }
            if (indexOfPlayer == 2) {
                players.get(0).setSkip(true);
                int rand1 = random.nextInt(cards.size());
                players.get(0).playerCards.add(cards.get(rand1));
                cards.remove(rand1);
                int rand2 = random.nextInt(cards.size());
                players.get(0).playerCards.add(cards.get(rand2));
                cards.remove(rand2);
                int rand3 = random.nextInt(cards.size());
                players.get(0).playerCards.add(cards.get(rand3));
                cards.remove(rand3);
                int rand4 = random.nextInt(cards.size());
                players.get(0).playerCards.add(cards.get(rand4));
                cards.remove(rand4);
            }
            return true;

        }
        if (playerCard.getWildType().equals("NextColor")) {

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

    public void displayScoreBoard() {
        for (Player player : players) {
            System.out.println();
            System.out.println("--------------------------------------");
            if (player.name.equals("You"))
                System.out.println("| player name : " + player.name + "     | Score : " + player.score + " |");
            else System.out.println("| player name : " + player.name + " | Score : " + player.score + " |");

            System.out.println("--------------------------------------");

        }

    }
    public boolean endGame(){
        for(Player player : players){
            if(player.score==0)return true;
            if(player.playerCards.size()==0)return true;
        }
        if(cards.size()==0)return true;

        return false;
    }

}
