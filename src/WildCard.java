/**
 * the WildCard class represents a card with black color and 2 types : (draw4+ which blocks the next player and
 * adds 4 random cards to her/his cards and next color should be chosen , nextColor card which just chooses the next color
 * to be played).
 * this class inherits from Card class.
 *
 * @author sepehr tavakoli
 * @version 1.0
 * @since 2020.04.10
 */

public class WildCard extends Card {

    //type of the WildCard.
    private String wildType;

    /**
     * this constructor makes a WildCard with the given type and color.
     *
     * @param color    color of the wild card.
     * @param wildType type of the wild card.
     */
    public WildCard(String color, String wildType) {
        super(color);
        this.wildType = wildType;
    }

    /**
     * get the type of the wild card.
     *
     * @return type of the wild card.
     */
    public String getWildType() {
        return wildType;
    }

    /**
     * override the printCard method to print a wild card.
     */

    @Override
    public void printCard() {
        if (super.color.equals("Black")) {
            if (wildType.equals("Draw4+")) {
                System.out.println(Black + ANSI_WHITE + "+4           " + ANSI_RESET + RESET);
                System.out.println(Black + "             " + RESET);
                System.out.println(Black + ANSI_WHITE + "  *-------*  " + RESET);
                System.out.println(Black + ANSI_WHITE + "  |" + RESET + Yellow + "   " + Black + " " + Blue + "   " + Black + ANSI_WHITE + "|  " + RESET);
                System.out.println(Black + ANSI_WHITE + "  |   W   |  " + RESET);
                System.out.println(Black + ANSI_WHITE + "  |" + RESET + Red + "   " + Black + " " + Green + "   " + Black + ANSI_WHITE + "|  " + RESET);
                System.out.println(Black + ANSI_WHITE + "  *-------*  " + RESET);
                System.out.println(Black + "             " + RESET);
                System.out.println(Black + ANSI_WHITE + "           +4" + ANSI_RESET + RESET);
                System.out.println();
            } else {
                System.out.println(Black + Green + "  " + RESET + Black + "         " + Red + "  " + ANSI_RESET + RESET);
                System.out.println(Black + "             " + RESET);
                System.out.println(Black + ANSI_WHITE + "  *-------*  " + RESET);
                System.out.println(Black + ANSI_WHITE + "  |" + RESET + Yellow + "   " + Black + " " + Blue + "   " + Black + ANSI_WHITE + "|  " + RESET);
                System.out.println(Black + ANSI_WHITE + "  |   W   |  " + RESET);
                System.out.println(Black + ANSI_WHITE + "  |" + RESET + Red + "   " + Black + " " + Green + "   " + Black + ANSI_WHITE + "|  " + RESET);
                System.out.println(Black + ANSI_WHITE + "  *-------*  " + RESET);
                System.out.println(Black + "             " + RESET);
                System.out.println(Black + Blue + "  " + RESET + Black + "         " + Yellow + "  " + ANSI_RESET + RESET);
                System.out.println();

            }
        }
    }
}
