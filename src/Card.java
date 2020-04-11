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


    public  void printCard() {
//        if (color.equals("Red")) {
//            System.out.println(Red + "          " + RESET);
//            System.out.println(Red + "          " + RESET);
//            System.out.println(Red + "          " + RESET);
//            System.out.println(Red + "          " + RESET);
//            System.out.println(Red + "          " + RESET);
//            System.out.println(Red + "          " + RESET);
//            System.out.println();
//        }
//        if (color.equals("Green")) {
//            System.out.println(Green + "        " + RESET);
//            System.out.println(Green + "        " + RESET);
//            System.out.println(Green + "        " + RESET);
//            System.out.println(Green + "        " + RESET);
//            System.out.println();
//        }
//        if (color.equals("Blue")) {
//            System.out.println(Blue + "        " + RESET);
//            System.out.println(Blue + "        " + RESET);
//            System.out.println(Blue + "        " + RESET);
//            System.out.println(Blue + "        " + RESET);
//            System.out.println();
//        }
//        if (color.equals("Yellow")) {
//            System.out.println(Yellow + "        " + RESET);
//            System.out.println(Yellow + "        " + RESET);
//            System.out.println(Yellow + "        " + RESET);
//            System.out.println(Yellow + "        " + RESET);
//            System.out.println();
//        }
    }
}
