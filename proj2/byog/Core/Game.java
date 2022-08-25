package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;


    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        // initialize the tile
        //ter.initialize(WIDTH, HEIGHT);
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        tileInitialize(finalWorldFrame);

        // generate the map
        long seed = seedSeeker(input);
        MapGenerator map = new MapGenerator(seed);
        TETile type = Tileset.WALL;
        map.mapPrint(finalWorldFrame, type);

        // ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    /** Extract the seed number from a given string */
    private long seedSeeker(String input) {
        int strLength = input.length();
        long seed = 0;
        for (int i = 0; i < strLength; i += 1) {
            if (input.charAt(i) >= '0' & input.charAt(i) <= '9') {
                int num = input.charAt(i) - '0';
                seed = seed * 10 + num;
            } else {
                continue;
            }
        }
        return seed;
    }
    /** Initialize the tile with Tileset.NOTHING */
    private void tileInitialize(TETile[][] Tile) {
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                Tile[i][j] = Tileset.NOTHING;
            }
        }
    }
    /**
    public static void main(String[] args) {
        Game g = new Game();
        g.playWithInputString("N123ss");
    }
     */
}
