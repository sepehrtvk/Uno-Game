public class WildCard extends Card {

    private String wildType;
    private String nextColor;

    public WildCard(String color, String wildType) {
        super(color);
        this.wildType = wildType;
    }

    public String getWildType() {
        return wildType;
    }

    @Override
    public void printCard() {
        if (super.color.equals("Black")) {
            if (wildType.equals("Draw4+")) {
                System.out.println(Black + ANSI_WHITE + "+4           " + ANSI_RESET + RESET);
                System.out.println(Black + "             " + RESET);
                System.out.println(Black + ANSI_WHITE+ "   *-----*   " + RESET);
                System.out.println(Black +ANSI_WHITE+ "   |" + RESET+Yellow + "  "+Black+" "+Blue+"  " + Black + ANSI_WHITE + "|   " + RESET);
                System.out.println(Black + ANSI_WHITE+"   |  W  |   " + RESET);
                System.out.println(Black + ANSI_WHITE+"   |" + RESET +Red +"  "+Black+" "+Green+"  " + Black +  ANSI_WHITE +"|   " + RESET);
                System.out.println(Black + ANSI_WHITE+"   *-----*   " + RESET);
                System.out.println(Black + "             " + RESET);
                System.out.println(Black + ANSI_WHITE + "           +4" + ANSI_RESET + RESET);
                System.out.println();
            }
            else {
                System.out.println(Black + Green + "  "+RESET+Black+"         "+Red+"  " + ANSI_RESET + RESET);
                System.out.println(Black + "             " + RESET);
                System.out.println(Black + ANSI_WHITE+ "   *-----*   " + RESET);
                System.out.println(Black +ANSI_WHITE+ "   |" + RESET+Yellow + "  "+Black+" "+Blue+"  " + Black + ANSI_WHITE + "|   " + RESET);
                System.out.println(Black + ANSI_WHITE+"   |  W  |   " + RESET);
                System.out.println(Black + ANSI_WHITE+"   |" + RESET +Red +"  "+Black+" "+Green+"  " + Black +  ANSI_WHITE +"|   " + RESET);
                System.out.println(Black + ANSI_WHITE+"   *-----*   " + RESET);
                System.out.println(Black + "             " + RESET);
                System.out.println(Black + Blue + "  "+RESET+Black+"         "+Yellow+"  " + ANSI_RESET + RESET);
                System.out.println();

            }
        }
    }
}
