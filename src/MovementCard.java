/**
 * the MovementCard class represents a movement card that has 3 types : draw2+ , skip and reverse.
 * this class inherits from Card class.
 *
 * @author sepehr tavakoli
 * @version 1.0
 * @since 2020.04.10
 */

public class MovementCard extends Card {

    //type of the movement card.
    private String moveType;

    /**
     * this constructor makes a MovementCard with the given type and color.
     *
     * @param color    color of the Movement Card.
     * @param moveType type of the Movement Card.
     */
    public MovementCard(String color, String moveType) {
        super(color);
        this.moveType = moveType;
    }

    /**
     * get the type of the movement card.
     *
     * @return type of the movement card.
     */
    public String getMoveType() {
        return moveType;
    }

    /**
     * override the printCard method to print a movement card.
     */

    @Override
    public void printCard() {
        if (super.color.equals("Red")) {

            if (moveType.equals("Draw2+")) System.out.println(Red + "+2           " + RESET);
            else System.out.println(Red + "             " + RESET);
            System.out.println(Red + "             " + RESET);
            System.out.println(Red + "  *-------*  " + RESET);
            System.out.println(Red + "  |" + RESET + "       " + Red + "|  " + RESET);
            if (moveType.equals("Reverse")) {
                System.out.println(Red + "  |" + RESET + ANSI_RED + "REVERSE" + ANSI_RESET + Red + "|  " + RESET);
            }
            if (moveType.equals("Skip")) {
                System.out.println(Red + "  |" + RESET + ANSI_RED + " SKIIP " + ANSI_RESET + Red + "|  " + RESET);
            }
            if (moveType.equals("Draw2+")) {
                System.out.println(Red + "  |" + RESET + ANSI_RED + " DRAAW " + ANSI_RESET + Red + "|  " + RESET);
            }
            System.out.println(Red + "  |" + RESET + "       " + Red + "|  " + RESET);
            System.out.println(Red + "  *-------*  " + RESET);
            System.out.println(Red + "             " + RESET);
            if (moveType.equals("Draw2+")) System.out.println(Red + "           +2" + RESET);
            else System.out.println(Red + "             " + RESET);
            System.out.println();
        }
        if (super.color.equals("Green")) {

            if (moveType.equals("Draw2+")) System.out.println(Green + "+2           " + RESET);
            else System.out.println(Green + "             " + RESET);
            System.out.println(Green + "             " + RESET);
            System.out.println(Green + "  *-------*  " + RESET);
            System.out.println(Green + "  |" + RESET + "       " + Green + "|  " + RESET);
            if (moveType.equals("Reverse")) {
                System.out.println(Green + "  |" + RESET + ANSI_GREEN + "REVERSE" + ANSI_RESET + Green + "|  " + RESET);
            }
            if (moveType.equals("Skip")) {
                System.out.println(Green + "  |" + RESET + ANSI_GREEN + " SKIIP " + ANSI_RESET + Green + "|  " + RESET);
            }
            if (moveType.equals("Draw2+")) {
                System.out.println(Green + "  |" + RESET + ANSI_GREEN + " DRAAW " + ANSI_RESET + Green + "|  " + RESET);
            }
            System.out.println(Green + "  |" + RESET + "       " + Green + "|  " + RESET);
            System.out.println(Green + "  *-------*  " + RESET);
            System.out.println(Green + "             " + RESET);
            if (moveType.equals("Draw2+")) System.out.println(Green + "           +2" + RESET);
            else System.out.println(Green + "             " + RESET);
            System.out.println();
        }
        if (super.color.equals("Blue")) {

            if (moveType.equals("Draw2+")) System.out.println(Blue + "+2           " + RESET);
            else System.out.println(Blue + "             " + RESET);
            System.out.println(Blue + "             " + RESET);
            System.out.println(Blue + "  *-------*  " + RESET);
            System.out.println(Blue + "  |" + RESET + "       " + Blue + "|  " + RESET);
            if (moveType.equals("Reverse")) {
                System.out.println(Blue + "  |" + RESET + ANSI_BLUE + "REVERSE" + ANSI_RESET + Blue + "|  " + RESET);
            }
            if (moveType.equals("Skip")) {
                System.out.println(Blue + "  |" + RESET + ANSI_BLUE + " SKIIP " + ANSI_RESET + Blue + "|  " + RESET);
            }
            if (moveType.equals("Draw2+")) {
                System.out.println(Blue + "  |" + RESET + ANSI_BLUE + " DRAAW " + ANSI_RESET + Blue + "|  " + RESET);
            }
            System.out.println(Blue + "  |" + RESET + "       " + Blue + "|  " + RESET);
            System.out.println(Blue + "  *-------*  " + RESET);
            System.out.println(Blue + "             " + RESET);
            if (moveType.equals("Draw2+")) System.out.println(Blue + "           +2" + RESET);
            else System.out.println(Blue + "             " + RESET);
            System.out.println();
        }
        if (super.color.equals("Yellow")) {

            if (moveType.equals("Draw2+")) System.out.println(Yellow + "+2           " + RESET);
            else System.out.println(Yellow + "             " + RESET);
            System.out.println(Yellow + "             " + RESET);
            System.out.println(Yellow + "  *-------*  " + RESET);
            System.out.println(Yellow + "  |" + RESET + "       " + Yellow + "|  " + RESET);
            if (moveType.equals("Reverse")) {
                System.out.println(Yellow + "  |" + RESET + ANSI_YELLOW + "REVERSE" + ANSI_RESET + Yellow + "|  " + RESET);
            }
            if (moveType.equals("Skip")) {
                System.out.println(Yellow + "  |" + RESET + ANSI_YELLOW + " SKIIP " + ANSI_RESET + Yellow + "|  " + RESET);
            }
            if (moveType.equals("Draw2+")) {
                System.out.println(Yellow + "  |" + RESET + ANSI_YELLOW + " DRAAW " + ANSI_RESET + Yellow + "|  " + RESET);
            }
            System.out.println(Yellow + "  |" + RESET + "       " + Yellow + "|  " + RESET);
            System.out.println(Yellow + "  *-------*  " + RESET);
            System.out.println(Yellow + "             " + RESET);
            if (moveType.equals("Draw2+")) System.out.println(Yellow + "           +2" + RESET);
            else System.out.println(Yellow + "             " + RESET);
            System.out.println();
        }
    }

}

