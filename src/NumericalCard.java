/**
 * the NumericalCard class represents a Card that has a color ( blue , red , yellow , green and black)
 * and a number for each card.
 * this class inherits from Card class.
 *
 * @author sepehr tavakoli
 * @version 1.0
 * @since 2020.04.10
 */

public class NumericalCard extends Card {

    //number of the card.
    private int number;

    /**
     * this constructor makes a NumericalCard with the given number and color.
     *
     * @param color  color of the card.
     * @param number number of the card.
     */
    public NumericalCard(String color, int number) {
        super(color);
        this.number = number;
    }

    /**
     * get the number of the card.
     *
     * @return number of the card.
     */
    public int getNumber() {
        return number;
    }

    /**
     * override the printCard method to print a numerical card.
     */
    @Override
    public void printCard() {
        if (super.color.equals("Red")) {
            System.out.println(Red + number + "            " + RESET);
            System.out.println(Red + "             " + RESET);
            System.out.println(Red + "  *-------*  " + RESET);
            System.out.println(Red + "  |" + RESET + "       " + Red + "|  " + RESET);
            System.out.println(Red + "  |" + RESET + ANSI_RED + "  (" + number + ")  " + ANSI_RESET + Red + "|  " + RESET);
            System.out.println(Red + "  |" + RESET + "       " + Red + "|  " + RESET);
            System.out.println(Red + "  *-------*  " + RESET);
            System.out.println(Red + "             " + RESET);
            System.out.println(Red + "            " + number + RESET);
            System.out.println();
        }
        if (super.color.equals("Green")) {
            System.out.println(Green + number + "            " + RESET);
            System.out.println(Green + "             " + RESET);
            System.out.println(Green + "  *-------*  " + RESET);
            System.out.println(Green + "  |" + RESET + "       " + Green + "|  " + RESET);
            System.out.println(Green + "  |" + RESET + ANSI_GREEN + "  (" + number + ")  " + ANSI_RESET + Green + "|  " + RESET);
            System.out.println(Green + "  |" + RESET + "       " + Green + "|  " + RESET);
            System.out.println(Green + "  *-------*  " + RESET);
            System.out.println(Green + "             " + RESET);
            System.out.println(Green + "            " + number + RESET);
            System.out.println();
        }
        if (super.color.equals("Blue")) {
            System.out.println(Blue + number + "            " + RESET);
            System.out.println(Blue + "             " + RESET);
            System.out.println(Blue + "  *-------*  " + RESET);
            System.out.println(Blue + "  |" + RESET + "       " + Blue + "|  " + RESET);
            System.out.println(Blue + "  |" + RESET + ANSI_BLUE + "  (" + number + ")  " + ANSI_RESET + Blue + "|  " + RESET);
            System.out.println(Blue + "  |" + RESET + "       " + Blue + "|  " + RESET);
            System.out.println(Blue + "  *-------*  " + RESET);
            System.out.println(Blue + "             " + RESET);
            System.out.println(Blue + "            " + number + RESET);
            System.out.println();
        }
        if (super.color.equals("Yellow")) {
            System.out.println(Yellow + number + "            " + RESET);
            System.out.println(Yellow + "             " + RESET);
            System.out.println(Yellow + "  *-------*  " + RESET);
            System.out.println(Yellow + "  |" + RESET + "       " + Yellow + "|  " + RESET);
            System.out.println(Yellow + "  |" + RESET + ANSI_YELLOW + "  (" + number + ")  " + ANSI_RESET + Yellow + "|  " + RESET);
            System.out.println(Yellow + "  |" + RESET + "       " + Yellow + "|  " + RESET);
            System.out.println(Yellow + "  *-------*  " + RESET);
            System.out.println(Yellow + "             " + RESET);
            System.out.println(Yellow + "            " + number + RESET);
            System.out.println();
        }
    }

}


