package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;


    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        mainMenu();
        modeSelection();
    }

    /** Initialize and display title and instructions of the game */
    private void mainMenu() {
        ter.initialize(WIDTH, HEIGHT);
        textPrint("CS61B proj2", WIDTH / 2, HEIGHT * 7 / 10, 60);
        textPrint("New Game(N) Load Game(L)  Quit(Q)", WIDTH / 2, HEIGHT / 4, 25 );
    }
    /** Print text with given size at given position (x, y) */
    private void textPrint(String text, int x, int y, int size) {
        Font titleFont = new Font("Monaco", Font.BOLD, size);
        StdDraw.setFont(titleFont);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x, y, text);
        StdDraw.show();
    }
    private void modeSelection(){
        String mode = solicitNCharsInput(1);
        while (!mode.equals("N")  & !mode.equals("L") & !mode.equals("Q")) {
            textPrint("Please select a mode according to instruction!", WIDTH / 2, HEIGHT / 5, 20);
            mode = solicitNCharsInput(1);
        }
        switch (mode) {
            case "N":
                newGame();
        }
    }
    /** Set the operation of pressing "N" to start a new game and print the new map */
    private void newGame() {
        drawNewGame();
        long seed = solicitSeedInput();

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        tileInitialize(finalWorldFrame);
        mapGenerator(finalWorldFrame, seed, Tileset.WALL);
        ter.renderFrame(finalWorldFrame);
    }
    /** Draw the UI interface for a new game */
    private void drawNewGame() {
        StdDraw.clear(StdDraw.BLACK);
        textPrint("Enter a number: (End your input with \'S\')", 40, 25, 30);
        StdDraw.show();
    }

    /** Return the seed input by the player */
    private long solicitSeedInput() {
        String letter = solicitNCharsInput(1);
        long seed = 0;
        while(!letter.equals("S")) {
            char x = letter.charAt(0);
            if (x >= 'A' & x <= 'Z') {
                textPrint("Please input number or \"S\" to end your typing!", 40, 20, 20);
                letter = solicitNCharsInput(1);
                continue;
            }
            drawNewGame();
            int num = Integer.valueOf(letter);
            seed = seed * 10 + num;
            textPrint("Your input: " + String.valueOf(seed), WIDTH / 2, 20, 20);
            letter = solicitNCharsInput(1);
        }
        return seed;
    }
    /** Solicit n input of the user */
    private String solicitNCharsInput(int n) {
        int count = 0;
        String input = new String("");
        while (count < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char letter = StdDraw.nextKeyTyped();
            input = input + letter;
            count += 1;
        }
        input = input.toUpperCase();
        return input;
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
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        tileInitialize(finalWorldFrame);

        // generate the map
        long seed = seedSeeker(input);
        mapGenerator(finalWorldFrame, seed, Tileset.WALL);

        ter.renderFrame(finalWorldFrame);
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

    /** Print the map with given seed and type  */
    private void mapGenerator(TETile[][] Tile, long seed, TETile type) {
        MapGenerator map = new MapGenerator(seed);
        map.mapPrint(Tile, type);
    }
    public static void main(String[] args) {
        Game g = new Game();
        g.playWithKeyboard();
    }

}
