package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        long seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);

        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        //TODO: Initialize random number generator
        this.width = width;
        this.height = height;
        this.rand = new Random(seed);
        playerTurn = false;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.WHITE);


    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String randStr = "";
        for (int i = 0; i < n; i += 1) {
            char x = CHARACTERS[rand.nextInt(26)];
            randStr = randStr + x;
        }
        return randStr;

    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);

        // User interface
        StdDraw.text(width * 0.5,height * 0.5 , s);
        StdDraw.text(width * 0.15, height * 0.9, "Round: " + String.valueOf(round));

        if (!playerTurn) {
            StdDraw.text(width * 0.5, height * 0.95, "Watch!");
        } else {
            StdDraw.text(width * 0.5, height * 0.95, "Type");
        }
        StdDraw.show();


    }

    public void flashSequence(String letters) {
        playerTurn = false;
        drawFrame("");
        //TODO: Display each character in letters, making sure to blank the screen between letters
        StdDraw.clear(StdDraw.BLACK);
        for (int i = 0; i < letters.length(); i += 1) {
            StdDraw.pause(500);
            char letter = letters.charAt(i);
            drawFrame(String.valueOf(letter));
            StdDraw.show();
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.show();
        }



    }

    public String solicitNCharsInput(int n) {
        playerTurn = true;
        drawFrame("");
        //TODO: Read n letters of player input
        String input = "";
        int count = 0;
        while(count < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char letter = StdDraw.nextKeyTyped();
            input = input + String.valueOf(letter);
            drawFrame(input);
            count = count + 1;
        }
        StdDraw.pause(1000);
        return input;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        //TODO: Establish Game loop
        gameOver = false;
        round = 1;
        while (!gameOver) {
            drawFrame("Round: " + String.valueOf(round));
            String randStr = generateRandomString(round);
            flashSequence(randStr);
            String input = solicitNCharsInput(round);
            if (!randStr.equals(input)) {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + String.valueOf(round));
            }
            round += 1;
        }









    }

}
