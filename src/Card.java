/**
 * the Card abstract class represents a Card that has a color ( blue , red , yellow , green and black).
 *
 * @author sepehr tavakoli
 * @version 1.0
 * @since 2020.04.10
 */

public abstract class Card {

    //color of the card.
    protected String color;

    //colorful strings.
    public static final String Black = "\u001B[40m";
    public static final String Red = "\u001B[41m";
    public static final String Green = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String Blue = "\u001B[44m";
    public static final String Yellow = "\u001B[45m";
    public static final String RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";


    /**
     * this constructor sets the color of each Card.
     *
     * @param color of the card.
     */

    public Card(String color) {
        this.color = color;
    }

    /**
     * get the color of the color.
     *
     * @return color of the color.
     */
    public String getColor() {
        return color;
    }

    //override equals and hashCode methods.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return getColor() != null ? getColor().equals(card.getColor()) : card.getColor() == null;
    }

    @Override
    public int hashCode() {
        return getColor() != null ? getColor().hashCode() : 0;
    }

    /**
     * this method prints a card with details.
     */
    public abstract void printCard();

}
