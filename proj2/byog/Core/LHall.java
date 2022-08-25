package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


/** */
public class LHall {
    private int startX, startY, endX, endY;
    private int length;
    // Constructor
    public LHall(int sX, int sY, int eX, int eY) {
        if (sX <= eX) {
            startX = sX;
            startY = sY;
            endX = eX;
            endY = eY;
        } else {
            startX = eX;
            startY = eY;
            endX = sX;
            endY = sY;
        }
        length = 0;
    }

    public int hallLength() {
        length = Math.abs(startX - endX) + Math.abs(startY - endY);
        return length;
    }
    /** print the LHall to the map with provided Tile Engine*/
    public void printLHall(TETile[][] tiles, TETile type) {
        // print the hallway
        printL(tiles, Tileset.FLOOR, startX, startY, endX, endY);

        //print the side way
        if (startY == endY) {
            printL(tiles, type, startX, startY + 1, endX, endY + 1);
            printL(tiles, type, startX, startY - 1, endX, endY - 1);
        } else if (startX == endX){
            printL(tiles, type, startX + 1, startY, endX + 1, endY);
            printL(tiles, type, startX - 1, startY, endX - 1, endY);
        } else if (startY > endY) {
            printL(tiles, type, startX - 1, startY, endX, endY - 1);
            printL(tiles, type, startX + 1, startY, endX, endY + 1);
        } else if (startY < endY) {
            printL(tiles, type, startX - 1, startY, endX, endY + 1);
            printL(tiles, type, startX + 1, startY, endX, endY - 1);
        }

    }

    private void printL(TETile[][] tiles, TETile type, int sX, int sY, int eX, int eY) {
        int x1 = Math.min(sX, eX);
        int x2 = Math.max(sX, eX);
        int y1 = Math.min(sY, eY);
        int y2 = Math.max(sY, eY);
        int sideNum = 0;

        // print vertical line
        for (int i = y1; i <= y2; i += 1) {
            if (tiles[sX][i] == type & sideNum == 0) {
                sideNum += 1;
                tiles[sX][i] = type;
            } else if(tiles[sX][i] == type & sideNum == 1 | tiles[sX][i] == Tileset.FLOOR){
                continue;
            } else {
                tiles[sX][i] = type;
            }

        }
        // print horizontal line
        for (int j = x1; j <= x2; j += 1) {
            if (tiles[j][eY] == Tileset.FLOOR) {
                continue;
            } else {
                tiles[j][eY] = type;
            }
        }
    }
    /**
    public static void main(String[] args) {
        int WIDTH = 50;
        int HEIGHT = 50;
        // initialize tile rendering engine with a window of size
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] Tile = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                Tile[i][j] = Tileset.NOTHING;
            }
        }


        //LHall h = new LHall(5,10,3,4);
        //h.printLHall(Tile, Tileset.WALL);

        LHall l = new LHall(3,10,3,4);
        l.printLHall(Tile, Tileset.WALL);

        //l.printL(Tile,Tileset.WALL,3, 10, 3, 4);

        ter.renderFrame(Tile);
    }
     */

}
