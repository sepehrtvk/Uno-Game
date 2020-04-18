import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ThreePlayer extends UnoGame {

    private ArrayList<Card> playerOneCards;
    private ArrayList<Card> playerTwoCards;
    private ArrayList<Card> playerThreeCards;

    private Card lastCard;

    private String rotate;
    private String nextColor;

    private ArrayList<Player> players;

    Random random = new Random();


    public ThreePlayer() {
        playerOneCards = new ArrayList<Card>();
        playerTwoCards = new ArrayList<Card>();
        playerThreeCards = new ArrayList<Card>();
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

            System.out.println();
            displayScoreBoard();
            counter++;
            System.out.println("Enter 1 to play next round : ");
            scanner.nextInt();
        }
    }

    public void giveCard(Player player) {
        System.out.println("Player " + player.name + " please choose one card :");
        player.printPlayer();
        if (player.isSkip()) {
            System.out.println("! PLAYER BLOCKED !");
            player.setSkip(false);
            return;
        }
        int counter = 0;
        int counter2 = 0;
        while (true) {
            int choosedCard = random.nextInt(player.playerCards.size());
            if (checkCard(player.playerCards.get(choosedCard), player, lastCard)) break;
            counter++;
            if (counter > player.playerCards.size() + 10) {
                int rand = random.nextInt(cards.size());
                player.playerCards.add(cards.get(rand));
                cards.remove(rand);
                System.out.println("One card added to player " + player.name + " from repository.");
                while (true){
                    int choosedCard2 = random.nextInt(player.playerCards.size());
                    if (checkCard(player.playerCards.get(choosedCard2), player, lastCard)) break;
                    counter2++;
                    if (counter2 > player.playerCards.size() + 10) break;
                }
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
            player.setSkip(false);
            return;
        }
        player.printPlayerCards();

        boolean b = false;
        for (Card playerCard : player.playerCards) {
            if (lastCard.color.equals(playerCard.color)) {
                b = true;
                break;
            }
            if (lastCard instanceof WildCard) {
                if (playerCard.color.equals(nextColor)) {
                    b = true;
                    break;
                }
            }
            if (lastCard instanceof NumericalCard && playerCard instanceof NumericalCard) {
                NumericalCard nc1 = (NumericalCard) lastCard;
                NumericalCard nc2 = (NumericalCard) playerCard;
                if (nc1.getNumber() == nc2.getNumber()) {
                    b = true;
                    break;
                }
            }
            if (lastCard instanceof MovementCard && playerCard instanceof MovementCard) {
                MovementCard movementCard1 = (MovementCard) playerCard;
                MovementCard movementCard2 = (MovementCard) lastCard;
                if (movementCard1.getMoveType().equals(movementCard2.getMoveType())) {
                    b = true;
                    break;
                }
            }
            if (playerCard instanceof WildCard) {
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
                int choosedCardNumber = scanner.nextInt() - 1;
                Card choosedCard = player.playerCards.get(choosedCardNumber);
                if (checkCard(choosedCard, player, lastCard)) {
                    break;
                } else {
                    System.out.println("You are not allowed to choose this card !");
                }
            }
            player.playerCards.remove(lastCard);
        }
    }


    public boolean checkCard(Card cardToCheck, Player player, Card lastCardOnTable) {


        if (cardToCheck instanceof NumericalCard) {
            NumericalCard nc2 = (NumericalCard) cardToCheck;
            return checkNumericalCard(player, nc2, lastCardOnTable);
        }

        if (cardToCheck instanceof MovementCard) {
            MovementCard movementCard2 = (MovementCard) cardToCheck;
            return checkMovementCard(player, movementCard2, lastCardOnTable);
        }
        if (cardToCheck instanceof WildCard) {
            WildCard wildCard = (WildCard) cardToCheck;
            return checkWildCard(player, wildCard, lastCardOnTable);
        }

        if (lastCardOnTable.color.equals(cardToCheck.color)) {
            System.out.println("Player " + player.name + " put this card : ");
            cardToCheck.printCard();
            lastCard = cardToCheck;

            return true;
        }
        if (lastCardOnTable instanceof WildCard) {
            if (cardToCheck.color.equals(nextColor)) {
                System.out.println("Player " + player.name + " put this card : ");
                cardToCheck.printCard();
                lastCard = cardToCheck;
                nextColor = "null";
                return true;
            } else return false;
        }

        nextColor = "null";

        return false;
    }

    public boolean checkNumericalCard(Player player, NumericalCard playerCard, Card lastCardOnTable) {
        if (lastCardOnTable.color.equals(playerCard.color)) {
            System.out.println("Player " + player.name + " put this card : ");
            playerCard.printCard();
            //onTableCards.add(playerCard);
            lastCard = playerCard;
            return true;
        }
        if (lastCardOnTable instanceof NumericalCard) {
            NumericalCard numericalCard = (NumericalCard) lastCardOnTable;
            if (numericalCard.getNumber() == playerCard.getNumber()) {
                System.out.println("Player " + player.name + " put this card : ");
                playerCard.printCard();
                lastCard = playerCard;
                return true;

            }
        }
        if (lastCardOnTable instanceof WildCard) {
            if (nextColor.equals(playerCard.color)) {
                System.out.println("Player " + player.name + " put this card : ");
                playerCard.printCard();
                lastCard = playerCard;
                nextColor = "null";
                return true;
            }
        }
        return false;
    }

    public boolean checkMovementCard(Player player, MovementCard playerCard, Card lastCardOnTable) {
        boolean enter1 = false;
        boolean enter2 = false;
        boolean enter3 = false;
        if (lastCardOnTable instanceof WildCard) {
            if (nextColor.equals(playerCard.color))
                enter3 = true;
        }
        if (lastCardOnTable.color.equals(playerCard.color)) {
            enter1 = true;
        }
        if (lastCardOnTable instanceof MovementCard) {
            MovementCard movementCard = (MovementCard) lastCardOnTable;
            if (movementCard.getMoveType().equals(playerCard.getMoveType())) {
                enter2 = true;
            }
        }
        if (enter1 || enter2 || enter3) {
            System.out.println("Player " + player.name + " put this card : ");
            playerCard.printCard();
            lastCard = playerCard;
            int indexOfPlayer = players.indexOf(player);
            if (playerCard.getMoveType().equals("Skip")) {
                if (indexOfPlayer == 2) {
                    if (!players.get(2).isSkip())
                        players.get(0).setSkip(true);
                }
                if (indexOfPlayer == 1) {
                    if (!players.get(1).isSkip())
                        players.get(2).setSkip(true);
                }
                if (indexOfPlayer == 0) {
                    if (!players.get(0).isSkip())
                        players.get(1).setSkip(true);
                }
                return true;
            }
            if (playerCard.getMoveType().equals("Reverse")) {
                if (rotate.equals("Clock-Wise"))
                    setRotate("Anti-Clock-Wise");
                if (rotate.equals("Anti-Clock-Wise"))
                    setRotate("Clock-Wise");
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
            if (playerCard.getMoveType().equals("Draw2+")) {
                if (cards.size() >= 2) {
                    int rand1 = random.nextInt(cards.size());
                    int rand2 = random.nextInt(cards.size());
                    if (indexOfPlayer == 0) {
                        players.get(1).playerCards.add(cards.get(rand1));
                        cards.remove(cards.get(rand1));
                        players.get(1).playerCards.add(cards.get(rand2));
                        cards.remove(cards.get(rand2));
                        System.out.println("2 cards added to player " + players.get(1).name+" from repository.");
                        if (!players.get(0).isSkip())
                            players.get(1).setSkip(true);
                    }
                    if (indexOfPlayer == 1) {
                        players.get(2).playerCards.add(cards.get(rand1));
                        cards.remove(cards.get(rand1));
                        players.get(2).playerCards.add(cards.get(rand2));
                        cards.remove(cards.get(rand2));
                        System.out.println("2 cards added to player " + players.get(2).name+" from repository.");
                        if (!players.get(1).isSkip())
                            players.get(2).setSkip(true);
                    }
                    if (indexOfPlayer == 2) {
                        players.get(0).playerCards.add(cards.get(rand1));
                        cards.remove(cards.get(rand1));
                        players.get(0).playerCards.add(cards.get(rand2));
                        cards.remove(cards.get(rand2));
                        System.out.println("2 cards added to player " + players.get(0).name+" from repository.");
                        if (!players.get(2).isSkip())
                            players.get(0).setSkip(true);

                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWildCard(Player player, WildCard playerCard, Card lastCardOnTable) {
        Scanner scanner = new Scanner(System.in);
        for (Card card : player.playerCards) {
            if (card.color.equals(lastCardOnTable.color) && !card.color.equals("Black"))
                return false;
            if (card instanceof NumericalCard && lastCardOnTable instanceof NumericalCard) {
                NumericalCard numericalCard = (NumericalCard) card;
                NumericalCard numericalCard2 = (NumericalCard) lastCard;
                if (numericalCard.getNumber() == numericalCard2.getNumber())
                    return false;
            }
            if (card instanceof MovementCard && lastCardOnTable instanceof MovementCard) {
                MovementCard movementCard = (MovementCard) card;
                MovementCard movementCard2 = (MovementCard) lastCardOnTable;
                if (movementCard.getMoveType().equals(movementCard2.getMoveType()))
                    return false;
            }
        }

        if (player.name.equals("You")) {
            System.out.println("Please enter next Color : ");
            nextColor = scanner.next();
            System.out.println();
            if (nextColor.equals("Blue"))
                System.out.println(ANSI_BLUE + "Player " + player.name + " Choosed color <" + nextColor + ">" + ANSI_RESET);
            if (nextColor.equals("Green"))
                System.out.println(ANSI_GREEN + "Player " + player.name + " Choosed color <" + nextColor + ">" + ANSI_RESET);
            if (nextColor.equals("Red"))
                System.out.println(ANSI_RED + "Player " + player.name + " Choosed color <" + nextColor + ">" + ANSI_RESET);
            if (nextColor.equals("Yellow"))
                System.out.println(ANSI_YELLOW + "Player " + player.name + " Choosed color <" + nextColor + ">" + ANSI_RESET);
        } else {
            int rand = random.nextInt(4);
            switch (rand) {
                case 0:
                    nextColor = "Yellow";
                    System.out.println(ANSI_YELLOW + "Player " + player.name + " Choosed color <" + nextColor + ">" + ANSI_RESET);
                    break;
                case 1:
                    nextColor = "Blue";
                    System.out.println();
                    System.out.println(ANSI_BLUE + "Player " + player.name + " Choosed color <" + nextColor + ">" + ANSI_RESET);
                    break;
                case 2:
                    nextColor = "Red";
                    System.out.println();
                    System.out.println(ANSI_RED + "Player " + player.name + " Choosed color <" + nextColor + ">" + ANSI_RESET);
                    break;
                case 3:
                    nextColor = "Green";
                    System.out.println();
                    System.out.println(ANSI_GREEN + "Player " + player.name + " Choosed color <" + nextColor + ">" + ANSI_RESET);
                    break;
            }
        }
        System.out.println("Player " + player.name + " put this card : ");
        playerCard.printCard();
        lastCard = playerCard;

        int indexOfPlayer = players.indexOf(player);
        if (playerCard.getWildType().equals("Draw4+")) {
            if (cards.size() >= 4) {
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
                    System.out.println("4 cards added to player " + players.get(1).name+" from repository.");

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
                    System.out.println("4 cards added to player " + players.get(2).name+" from repository.");
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
                    System.out.println("4 cards added to player " + players.get(0).name+" from repository.");

                }
                return true;
            }

        }
        if (playerCard.getWildType().equals("NextColor")) {

            return true;
        }
        return false;
    }


    public void displayScoreBoard() {
        int[] array = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            array[i] = players.get(i).score;
        }
        Arrays.sort(array);
        System.out.println("--------------------------------");
        System.out.println("|          Score Board         |");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < players.size(); j++) {
                if (players.get(j).score == array[i]) {
                    System.out.println("--------------------------------");
                    System.out.println("| Player : " + players.get(j).name + " | Score : " + array[i]);
                    System.out.println("--------------------------------");
                }
            }
        }
        for (int k = 0; k < players.size(); k++) {
            if (players.get(k).isWinner()) {
                System.out.println();
                System.out.println("  !  The winner is : " + players.get(k).name + "  !");
            }

        }
        System.out.println();
    }


    public void setWinner() {
        int[] array = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            array[i] = players.get(i).score;
        }
        Arrays.sort(array);
        for (Player player : players) {
            if (player.score == array[0]) player.setWinner(true);
        }
    }

    public boolean endGame() {

        for (Player player : players) {
            if (player.score == 0 || player.playerCards.size() == 0) {
                player.setWinner(true);
                player.calculateScore();
                displayScoreBoard();
                return true;
            }
        }
        return false;
    }

}
