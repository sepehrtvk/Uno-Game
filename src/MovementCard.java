public class MovementCard extends Card {

    private String moveType;

    public MovementCard(String color, String moveType) {
        super(color);
        this.moveType = moveType;
    }

    public String getMoveType() {
        return moveType;
    }
}
