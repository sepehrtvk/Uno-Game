public class Card {

    protected String color;

    public static final String Black = "\u001B[40m";
    public static final String Red = "\u001B[41m";
    public static final String Green = "\u001B[42m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String Blue = "\u001B[44m";
    public static final String Yellow = "\u001B[43m";
    public static final String RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";



    public Card(String color) {
        this.color = color;
    }


    public String getColor() {
        return color;
    }

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

    public  void printCard() {
    }
}
