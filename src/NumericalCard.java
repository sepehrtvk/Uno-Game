public class NumericalCard extends Card {

    private int number;

    public NumericalCard(String color, int number) {
        super(color);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }


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


