package cgame.src;

public class GameData {
    private String text;
    private String optionText;
    private int points;
    private int neagativePoints; 

    private GameData(String text, String optionText, int points, int neagativePoints) {
        this.text = text;
        this.optionText = optionText;
        this.points = points;
        this.neagativePoints = neagativePoints;
    }


    public String getText() {
        return text;
    }



    public String getOptionText() {
        return optionText;
    }



    public int getPoints() {
        return points;
    }



    public int getNeagativePoints() {
        return neagativePoints;
    }



    public static class GameDataBuilder {
        private String text;
        private String optionText;
        private int points;
        private int neagativePoints;

        public static GameDataBuilder nodeBuilder = new GameDataBuilder();;

        public static GameDataBuilder getBuilder(){
            return nodeBuilder;
        }

        public GameDataBuilder setText(String text) {
            this.text = text;
            return this;
        }
        public GameDataBuilder setOptionText(String optionText) {
            this.optionText = optionText;
            return this;
        }
        public GameDataBuilder setPoints(int points) {
            this.points = points;
            return this;
        }
        public GameDataBuilder setNeagativePoints(int neagativePoints) {
            this.neagativePoints = neagativePoints;
            return this;
        } 

        public GameData build(){
            
            return new GameData(optionText, optionText, neagativePoints, neagativePoints);
        }

        
    }
}
