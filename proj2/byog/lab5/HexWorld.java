package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    /** Add Hexagon of side length s to a given position with given pattern type */
    public static void addHexagon(TETile[][] tiles, int s, int posX, int posY, TETile type) {
        int height = 2 * s;
        int width = maxWidth(s);
        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < height; j += 1) {
                Boolean flag = fillType(s, j, i);
                if (flag) {
                    tiles[posX + i][posY + j] = type;
                } else {
                    tiles[posX + i][posY + j] = Tileset.NOTHING;
                }
            }
        }

    }
    /** return the maximum width of the hexagon */
    private static int maxWidth(int s) {
        int max;
        max = s + (s - 1) * 2;
        return max;
    }
    /** return the width of the hexagon for row r */
    private static int width(int s, int r) {
        int width;
        if (r <= s) {
            width = s + r * 2;
        } else {
            width = maxWidth(s) - (r - s) * 2;
        }
        return width;
    }
    /** return boolean value indicating whether to draw or fill with nothing */
    private static boolean fillType(int s, int r, int x) {
        int left, right;
        if (r <= s - 1) {
            left = s - r - 1;
        } else {
            left = r - s;
        }
        right = left + width(s, r) - 1;
        return (x >= left && x <= right);
    }


    public static void main(String[] args) {
        // initialize tile rendering engine with a window of size
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] hexTile = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                hexTile[i][j] = Tileset.NOTHING;
            }
        }


        addHexagon(hexTile, 2, 30, 30, Tileset.FLOWER);
        ter.renderFrame(hexTile);
    }
}
