package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/** Room class store the attribute of room
 * Include a method to detect overlap with other rooms in a list of Room objects */
public class Room {
    public int width;
    public int height;
    public int posX; // denote the left bottom position of the room (posX & posY)
    public int posY;

    /** Constructor */
    public Room(int w, int h, int x, int y) {
        width = w;
        height = h;
        posX = x;
        posY = y;
    }

    /** Check the generated Room with existed room objects in list r */
    public boolean overlap(Room[] r, int count) {
        if (count == 0) {
            return false;
        } else {
            for (int i = 0; i < count; i += 1) {
                int leftX = r[i].posX;
                int rightX = leftX + r[i].width;
                int bottomY = r[i].posY;
                int upY = bottomY + r[i].height;
                if (!(posX > rightX | posX + width < leftX | posY > upY | posY + height < bottomY)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Print the room onto the map with provided Tile Engine */
    public void printRoom(TETile[][] tiles, TETile type) {
        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < height; j += 1) {
                if (i == 0 | j == 0 | i == width - 1 | j == height - 1) {
                    tiles[posX + i][posY + j] = type;
                } else {
                    tiles[posX + i][posY + j] = Tileset.FLOOR;
                }
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

        Room r = new Room(20, 30, 0, 0);
        r.printRoom(Tile, Tileset.WALL);
        ter.renderFrame(Tile);
    }
     */
}
