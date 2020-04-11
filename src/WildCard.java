public class WildCard extends Card {

    private String wildType;
    private String nextColor;

    public WildCard(String color, String wildType) {
        super(color);
        this.wildType = wildType;
    }
}
