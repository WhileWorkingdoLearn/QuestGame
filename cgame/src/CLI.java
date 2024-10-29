package cgame.src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import cgame.src.GameData.GameDataBuilder;
import cgame.src.GameGraph.INode;

public class CLI {

    public enum Command {
        QUIT("-quit","-q"),
        SAVE("-save","-s"),
        HELP("-help","-h");

        private static final Map<String,Command> ALIAS_MAP = new HashMap<>();
        private final String[] aliasses;

        static {
            for (Command comand : Command.values()){
                for(String aliases : comand.aliasses){
                    ALIAS_MAP.put(aliases.toLowerCase(),comand);
                }
            }
        }

        Command(String... aliasses){
            this.aliasses = aliasses;
        }

        public static Command fromInput(String input){
            return ALIAS_MAP.get(input.toLowerCase());
        }
    }

    private static GameGraph initGame(){
        GameGraph gameGraph = new GameGraph();
        var root = GameDataBuilder.getBuilder().setText("Once upon a time").setPoints(0).setNeagativePoints(0).build();
        var next = GameDataBuilder.getBuilder().setText("Was a carpenter").setPoints(10).setNeagativePoints(0).build();
        var rootNode = GameGraph.CreateNode(root,"1");
        var nextNode = GameGraph.CreateNode(next,"2");
        gameGraph.addNode(rootNode);
        gameGraph.addEdge(rootNode,nextNode);
        return gameGraph;
    }

    private static void processInput(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        System.out.println("Enter Text:");
        while ((line = reader.readLine()) != null ) {
           var msg =  line.trim();
           Command command = Command.fromInput(msg);
            if(command != null) {
                switch (command) {
                    case QUIT: 
                    System.out.println("Console closes");
                    return;
                    case HELP:
                    System.out.println("Help was entered");
                    break;
                    default:
                        break;
                }
            } else {
                System.out.println("Ungültiger befehlt");
            }

        }
        in.close();
    }

    public static void main(String[] args) {

        try {
            processInput(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}