import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * the ThreePlayer class makes a uno game with 3 players.
 * each player have 7 cards at first.
 * a random card should be put on the table and then game stars.
 * this class inherits from UnoGame class.
 *
 * @author sepehr tavakoli
 * @version 1.0
 * @since 2020.04.10
 */

public class ThreePlayer extends UnoGame {

    //cards of player 1 .
    ArrayList<Card> playerOneCards;

    //cards of player 2 .
    ArrayList<Card> playerTwoCards;

    //cards of player 3 .
    ArrayList<Card> playerThreeCards;


    //last card on table.
    Card lastCard;

    //rotate mode of the game.
    String rotate;

    //next color which should be choosed after using wild card.
    String nextColor;

    //players in the game.
    ArrayList<Player> players;

    //random class.
    Random random = new Random();

    /**
     * this constructor makes a ThreePlayer game with 7 cards for each player.
     */
    public ThreePlayer() {

        playerOneCards = new ArrayList<Card>();
        playerTwoCards = new ArrayList<Card>();
        playerThreeCards = new ArrayList<Card>();

        rotate = "Clock-Wise";

        nextColor = "null";

        //adding 7 cards to  player 1 .
        for (int i = 0; i < 7; i++) {
            int rand = random.nextInt(cards.size());
            playerOneCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }

        //adding 7 cards to  player 2 .
        for (int i = 0; i < 7; i++) {
            int rand = random.nextInt(cards.size());
            playerTwoCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }

        //adding 7 cards to  player 3 .
        for (int i = 0; i < 7; i++) {
            int rand = random.nextInt(cards.size());
            playerThreeCards.add(cards.get(rand));
            cards.remove(cards.get(rand));
        }

        //choose the first card.
        while (true) {
            int rand = random.nextInt(cards.size());
            if (!cards.get(rand).color.equals("Black")) {
                lastCard = cards.get(rand);
                cards.remove(cards.get(rand));
                break;
            }
        }

        players = new ArrayList<Player>();

        //creating 3 players.
        players.add(new Player("Sepehr", playerOneCards));
        players.add(new Player("Mohammad", playerTwoCards));
        players.add(new Player("Ali", playerThreeCards));

        //calculate score of each player.
        players.get(0).calculateScore();
        players.get(1).calculateScore();
        players.get(2).calculateScore();
    }

    /**
     * get the rotate mode of the game.
     *
     * @return rotate mode of the game.
     */
    public String getRotate() {
        return rotate;
    }

    /**
     * set the rotate mode of the game.
     *
     * @param rotate rotate mode.
     */
    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    /**
     * this startThreePlayerGame method starts the three player game and
     * takes input if the player is the user.
     */
    public void startThreePlayerGame() {


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

            //player 1 plays.
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

            //player 2 plays.
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

            System.out.println();
            //show score board.
            displayScoreBoard();

            counter++;

            //enter next round.
            System.out.println("Enter 1 to play next round : ");
            scanner.nextInt();
        }
    }

    /**
     * the giveCardBot method takes a random card from the repository and show it as a player card.
     *
     * @param player player want to take card.
     */

    public void giveCardBot(Player player) {

        System.out.println("Player " + player.name + " please choose one card :");

        //check if the player is blocked or not.
        if (player.isSkip()) {

            System.out.println();
            System.out.println(ANSI_RED + "        ! PLAYER BLOCKED !" + ANSI_RESET);
            System.out.println();

            player.setSkip(false);
            return;
        }
        //print player details.
        player.printPlayer();

        int counter = 0;
        int counter2 = 0;

        while (true) {

            //make a random card.
            int choosedCard = random.nextInt(player.playerCards.size());
            if (checkCard(player.playerCards.get(choosedCard), player, lastCard)) {
                player.playerCards.remove(choosedCard);
                break;
            }

            counter++;

            if (counter > player.playerCards.size() + 10) {

                int rand = random.nextInt(cards.size());
                player.playerCards.add(cards.get(rand));
                cards.remove(rand);

                System.out.println("One card added to player " + player.name + " from repository.");

                while (true) {

                    int choosedCard2 = random.nextInt(player.playerCards.size());
                    if (checkCard(player.playerCards.get(choosedCard2), player, lastCard)) break;

                    counter2++;
                    if (counter2 > player.playerCards.size() + 10) break;
                }
                break;
            }
        }
    }

    /**
     * the giveCardChoose method chooses a card from user.
     *
     * @param player user wants to take a card.
     */
    public void giveCardChoose(Player player) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Player " + player.name + " please choose one card :");

        //check if the player is blocked or not.
        if (player.isSkip()) {

            System.out.println();
            System.out.println(ANSI_RED + "        ! PLAYER BLOCKED !" + ANSI_RESET);
            System.out.println();

            player.setSkip(false);
            return;
        }

        //show player details.
        player.printPlayer();

        //show the player cards.
        player.printPlayerCards();

        //check if the player can put a card on table or not.
        if (!isCardAllowed(player)) {

            //add a random card.
            int rand = random.nextInt(cards.size());
            player.playerCards.add(cards.get(rand));
            System.out.println("This card added to player to Sepehr from repository.");
            cards.get(rand).printCard();

            checkCard(cards.get(rand), player, lastCard);
            cards.remove(rand);
        } else {
            while (true) {

                int choosedCardNumber = scanner.nextInt() - 1;

                if (checkCard(player.playerCards.get(choosedCardNumber), player, lastCard)) {
                    player.playerCards.remove(choosedCardNumber);
                    break;
                } else {

                    System.out.println("Sepehr are not allowed to choose this card !");
                }
            }
        }
    }

    /**
     * this isCardAllowed method checks if the user can put a card on table
     * from their card or not. if not a random card adds to their cards.
     *
     * @param player user.
     * @return true if player can put a card.
     */
    public boolean isCardAllowed(Player player) {

        for (Card playerCard : player.playerCards) {

            //check color.
            if (lastCard.color.equals(playerCard.color)) {
                return true;
            }

            //check wild card.
            if (lastCard instanceof WildCard) {
                if (playerCard.color.equals(nextColor)) {
                    return true;
                }
            }

            //check numerical card.
            if (lastCard instanceof NumericalCard && playerCard instanceof NumericalCard) {
                NumericalCard nc1 = (NumericalCard) lastCard;
                NumericalCard nc2 = (NumericalCard) playerCard;
                if (nc1.getNumber() == nc2.getNumber()) {
                    return true;
                }
            }

            //check movement card.
            if (lastCard instanceof MovementCard && playerCard instanceof MovementCard) {
                MovementCard movementCard1 = (MovementCard) playerCard;
                MovementCard movementCard2 = (MovementCard) lastCard;
                if (movementCard1.getMoveType().equals(movementCard2.getMoveType())) {
                    return true;
                }
            }

            //check wild card.
            if (playerCard instanceof WildCard) {
                return true;
            }

        }
        return false;
    }

    /**
     * this checkCard method checks that a card can be put on table or not.
     *
     * @param cardToCheck     card want to check.
     * @param player          player want to check their card.
     * @param lastCardOnTable the last card on table.
     * @return true if the card can be put on table.
     */
    public boolean checkCard(Card cardToCheck, Player player, Card lastCardOnTable) {

        //check numerical card.
        if (cardToCheck instanceof NumericalCard) {
            NumericalCard nc2 = (NumericalCard) cardToCheck;
            return checkNumericalCard(player, nc2, lastCardOnTable);
        }

        //check movement card.
        if (cardToCheck instanceof MovementCard) {
            MovementCard movementCard2 = (MovementCard) cardToCheck;
            return checkMovementCard(player, movementCard2, lastCardOnTable);
        }

        //check wild card.
        if (cardToCheck instanceof WildCard) {
            WildCard wildCard = (WildCard) cardToCheck;
            return checkWildCard(player, wildCard, lastCardOnTable);
        }

        //check color.
        if (lastCardOnTable.color.equals(cardToCheck.color)) {
            System.out.println("Player " + player.name + " put this card : ");
            cardToCheck.printCard();
            lastCard = cardToCheck;
            return true;
        }
        //check wild card color.
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

    /**
     * this checkNumericalCard method checks numerical card number and color.
     *
     * @param player          player want to check their card.
     * @param playerCard      card want to check.
     * @param lastCardOnTable the last card on table.
     * @return true if the card is allowed.
     */
    public boolean checkNumericalCard(Player player, NumericalCard playerCard, Card lastCardOnTable) {

        //check color.
        if (lastCardOnTable.color.equals(playerCard.color)) {

            //add card on table.
            System.out.println("Player " + player.name + " put this card : ");
            playerCard.printCard();
            lastCard = playerCard;

            return true;
        }
        //check number.
        if (lastCardOnTable instanceof NumericalCard) {

            NumericalCard numericalCard = (NumericalCard) lastCardOnTable;
            if (numericalCard.getNumber() == playerCard.getNumber()) {

                System.out.println("Player " + player.name + " put this card : ");
                playerCard.printCard();
                lastCard = playerCard;
                return true;
            }
        }
        //check next color.
        if (lastCardOnTable instanceof WildCard) {

            if (nextColor.equals(playerCard.color)) {

                //add card on table.
                System.out.println("Player " + player.name + " put this card : ");
                playerCard.printCard();
                lastCard = playerCard;
                nextColor = "null";

                return true;
            }
        }
        return false;
    }

    /**
     * this checkMovementCard method checks movement card type and color.
     *
     * @param player          player want to check their card.
     * @param playerCard      card want to check.
     * @param lastCardOnTable the last card on table.
     * @return true if the card is allowed.
     */
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

            //add card on table.
            System.out.println("Player " + player.name + " put this card : ");
            playerCard.printCard();
            lastCard = playerCard;

            int indexOfPlayer = players.indexOf(player);

            //check skip card.
            if (playerCard.getMoveType().equals("Skip")) {
                skipCard(indexOfPlayer);
                return true;
            }

            //check reverse card.
            if (playerCard.getMoveType().equals("Reverse")) {
                reverseCard(indexOfPlayer);
                return true;
            }

            //check draw card.
            if (playerCard.getMoveType().equals("Draw2+")) {
                drawCard2(indexOfPlayer);
                return true;
            }
        }
        return false;
    }

    /**
     * this checkWildCard method checks wild card type and next color.
     *
     * @param player          player want to check their card.
     * @param playerCard      card want to check.
     * @param lastCardOnTable the last card on table.
     * @return true if the card is allowed.
     */

    public boolean checkWildCard(Player player, WildCard playerCard, Card lastCardOnTable) {

        Scanner scanner = new Scanner(System.in);

        //check all player cards.
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

        //make next color.
        if (player.name.equals("Sepehr")) {

            while (true) {
                //add card on table.
                System.out.println("Please enter next Color : (Blue - Red - Yellow - Green ");
                nextColor = scanner.next();
                if (nextColor.equals("Blue") || nextColor.equals("Red") || nextColor.equals("Green") || nextColor.equals("Yellow"))
                    break;
                else System.out.println("! Wrong input color !");
            }

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

        //add card on table.
        System.out.println("Player " + player.name + " put this card : ");
        playerCard.printCard();
        lastCard = playerCard;

        int indexOfPlayer = players.indexOf(player);

        //check draw4+ card.
        if (playerCard.getWildType().equals("Draw4+")) {
            drawCard4(indexOfPlayer);
            return true;

        }

        //check next color card.
        if (playerCard.getWildType().equals("NextColor")) {
            return true;
        }

        return false;
    }

    /**
     * this displayScoreBoard method show the score of each player.
     */
    public void displayScoreBoard() {

        int[] array = new int[players.size()];

        for (int i = 0; i < players.size(); i++) {
            array[i] = players.get(i).score;
        }
        //sort the scores.
        Arrays.sort(array);

        System.out.println("--------------------------------");
        System.out.println("|          Score Board         |");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < players.size(); j++) {
                if (players.get(j).score == array[i] && array[i] != 0) {
                    System.out.println("--------------------------------");
                    System.out.println("| Player : " + players.get(j).name + " | Score : " + array[i]);
                    System.out.println("--------------------------------");
                }
            }
        }
        //find the winner.
        for (int k = 0; k < players.size(); k++) {

            if (players.get(k).isWinner()) {

                System.out.println();
                System.out.println("  !  The winner is : " + players.get(k).name + "  !");
            }
        }
        System.out.println();
    }

    /**
     * this setWinner method takes finds the winner among the players.
     */
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

    /**
     * the reverseCard makes game a reverse and changes the rotate mode.
     *
     * @param indexOfPlayer index of player in the list.
     */
    public void reverseCard(int indexOfPlayer) {

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

    }

    /**
     * this skipCard method blocks next player from playing.
     *
     * @param indexOfPlayer index of player in the list.
     */
    public void skipCard(int indexOfPlayer) {
        if (indexOfPlayer == players.size() - 1) {
            if (!players.get(players.size() - 1).isSkip())
                players.get(0).setSkip(true);
        } else {

            if (!players.get(indexOfPlayer).isSkip())
                players.get(indexOfPlayer + 1).setSkip(true);
        }

    }

    /**
     * this drawCard2 method checks draw2+ card and adds 2 random card to next player and blocks him/her.
     *
     * @param indexOfPlayer index of player in the list.
     */
    public void drawCard2(int indexOfPlayer) {

        if (cards.size() >= 2) {

            int rand1 = random.nextInt(cards.size());
            int rand2 = random.nextInt(cards.size());

            if (indexOfPlayer == players.size() - 1) {
                players.get(0).playerCards.add(cards.get(rand1));
                cards.remove(cards.get(rand1));
                players.get(0).playerCards.add(cards.get(rand2));
                cards.remove(cards.get(rand2));

                System.out.println("2 cards added to player " + players.get(0).name + " from repository.");

                if (!players.get(2).isSkip())
                    players.get(0).setSkip(true);

            } else {
                players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand1));
                cards.remove(cards.get(rand1));
                players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand2));
                cards.remove(cards.get(rand2));

                System.out.println("2 cards added to player " + players.get(indexOfPlayer + 1).name + " from repository.");

                if (!players.get(indexOfPlayer).isSkip())
                    players.get(indexOfPlayer + 1).setSkip(true);
            }
        }
    }

    /**
     * this drawCard4 method checks the draw4+ wild card and add 4 random cards to next player and block him/her.
     *
     * @param indexOfPlayer index of player in the list.
     */
    public void drawCard4(int indexOfPlayer) {

        if (cards.size() >= 4) {

            //make 4 random cards.
            if (indexOfPlayer == players.size() - 1) {
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

                System.out.println("4 cards added to player " + players.get(0).name + " from repository.");

            } else {

                //make 4 random cards.
                players.get(indexOfPlayer + 1).setSkip(true);
                int rand1 = random.nextInt(cards.size());
                players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand1));
                cards.remove(rand1);
                int rand2 = random.nextInt(cards.size());
                players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand2));
                cards.remove(rand2);
                int rand3 = random.nextInt(cards.size());
                players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand3));
                cards.remove(rand3);
                int rand4 = random.nextInt(cards.size());
                players.get(indexOfPlayer + 1).playerCards.add(cards.get(rand4));
                cards.remove(rand4);

                System.out.println("4 cards added to player " + players.get(indexOfPlayer + 1).name + " from repository.");

            }
        }
    }

    /**
     * the endGame method checks end of the game after each move.
     *
     * @return true if the game has finished.
     */
    public boolean endGame() {

        for (Player player : players) {

            player.calculateScore();

            if (player.score == 0 || player.playerCards.size() == 0) {
                player.setWinner(true);
                displayScoreBoard();
                return true;
            }
        }
        return false;
    }

}
