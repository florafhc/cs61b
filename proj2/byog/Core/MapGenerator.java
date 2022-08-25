package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/** MapGenerator is to generate the random map according to the given seed
 * Generate random number of Rooms with random attributes (does not overlap with each other)
 * Connect the Room with the latest created one with L halls*/
public class MapGenerator {
    private long seed;
    private Random r;
    private int count;
    private int roomNum;
    private long roomSeed; // track the seed generating the
    private Room[] roomList;
    public MapGenerator(long s) {
        seed = s;
        r = new Random(seed);
        count = 0;
        roomNum = RandomUtils.uniform(r, 10, 16);
        roomList = new Room[roomNum];
        roomSeed = s;
    }

    public void mapPrint(TETile[][] tiles, TETile type) {
        mapRoomPrint(tiles, type);
        mapHallwayPrint(tiles, type);
    }

    private void mapRoomPrint(TETile[][] tiles, TETile type) {

        for (int i = 0; i < roomNum; i += 1) {
            Room newRoom = newRoom();
            newRoom.printRoom(tiles, type);
        }
    }

    /** Generate room with random size and position based on given seed */
    private Room randomRoom() {
        Random roomRandom = new Random(roomSeed);
        int width = RandomUtils.uniform(roomRandom, 5,16);
        int height = RandomUtils.uniform(roomRandom, 4,15);
        int posX = RandomUtils.uniform(roomRandom, 64);
        int posY = RandomUtils.uniform(roomRandom, 34);
        return new Room(width, height, posX, posY);

    }

    /** Generate new room that is not overlapped with existing rooms
     * Add the room to the list and update the counts
     * Print the room to the map */
    private Room newRoom() {
        Room newRoom = randomRoom();
        roomSeed += 1;
        while (newRoom.overlap(roomList, count)) {
            roomSeed = roomSeed + 1;
            newRoom = randomRoom();
        }
        roomList[count] = newRoom;
        count = count + 1;
        return newRoom;
    }

    private void mapHallwayPrint(TETile[][] tiles, TETile type) {
        for (int i = 0; i < roomNum - 1; i += 1) {
            LHall hallway = hallwayRooms(roomList[i], roomList[i+1]);
            hallway.printLHall(tiles, type);
        }
    }

    /** Generate relatively short random hallways between two given rooms
     * Print the generated hallway */
    private LHall hallwayRooms(Room r1, Room r2) {
        Random pointRandom = new Random();

        int startX = r1.posX + 1;
        int startY = r1.posY + 1;
        int endX = r2.posX + 1;
        int endY = r2.posY + 1;

        int width1 = r1.width - 3;
        int width2 = r2.width - 3;
        int height1 = r1.height - 3;
        int height2 = r2.height - 3;

        LHall[] hallList = new LHall[16];
        hallList[0] = new LHall(startX, startY, endX, endY);
        hallList[1] = new LHall(startX, startY + height1, endX, endY);
        hallList[2] = new LHall(startX + width1, startY, endX, endY);
        hallList[3] = new LHall(startX + width1 , startY + height1, endX, endY);

        hallList[4] = new LHall(startX, startY, endX + width2, endY);
        hallList[5] = new LHall(startX, startY + height1, endX + width2, endY);
        hallList[6] = new LHall(startX + width1, startY, endX + width2, endY);
        hallList[7] = new LHall(startX + width1, startY + height1, endX + width2, endY);

        hallList[8] = new LHall(startX, startY, endX, endY + height2);
        hallList[9] = new LHall(startX, startY + height1, endX, endY + height2);
        hallList[10] = new LHall(startX + width1, startY, endX, endY + height2);
        hallList[11] = new LHall(startX + width1, startY + height1, endX, endY + height2);

        hallList[12] = new LHall(startX, startY, endX + width2, endY + height2);
        hallList[13] = new LHall(startX, startY + height1, endX + width2, endY + height2);
        hallList[14] = new LHall(startX + width1, startY, endX + width2, endY + height2);
        hallList[15] = new LHall(startX + width1, startY + height1, endX + width2, endY + height2);

        LHall minHall = hallList[0];
        for (LHall i : hallList) {
            if (i.hallLength() < minHall.hallLength()) {
                minHall = i;
            }
        }
        return minHall;

    }

    private int length(int sX, int sY, int eX, int eY) {
        return Math.abs(sX - eX) + Math.abs(sY - eY);
    }

/**
    public static void main(String[] args) {
        MapGenerator m = new MapGenerator(103);

        int WIDTH = 80;
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

        m.mapPrint(Tile, Tileset.WALL);
        ter.renderFrame(Tile);

    }
*/



}

