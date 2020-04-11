import java.util.ArrayList;

public class Player {

    protected String name;
    protected ArrayList<Card>playerCards;

    public Player(String name, ArrayList<Card> playerCards) {
        this.name = name;
        this.playerCards = playerCards;
    }
}
