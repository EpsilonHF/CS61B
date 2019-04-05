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
    private static final int WIDTH = 29;
    private static final int HEIGHT = 30;
    private static final Random RANDOM = new Random(123);

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    private static void init(TETile[][] world) {
        int x = world.length;
        int y = world[0].length;
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                world[i][j] = Tileset.NOTHING;
    }

    public static void addHexagon(TETile[][] world, int x, int y, int size, TETile pattern) {
        int length = size;
        for (int i = 0; i < size; i++) {
            addline(world, x--, y++, length, pattern);
            length += 2;
        }
        for (int i = 0; i < size; i++) {
            length -= 2;
            addline(world, ++x, y++, length, pattern);
        }

    }

    private static void addline(TETile[][] world, int x, int y, int length, TETile pattern) {
        for (int i  = 0; i < length; i++)
            world[x++][y] = pattern;
    }

    public static void draw(TETile[][] world, int x, int y, int size, int num) {
        for (int i = 0; i < num; i++) {
            addHexagon(world, x, y, size, randomTile());
            y += size * 2;
            if (y > world[0].length)
                break;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        init(world);

        draw(world, 3, 6, 3, 3);
        draw(world, 8, 3, 3, 4);
        draw(world, 13, 0, 3, 5);
        draw(world, 18, 3, 3, 4);
        draw(world, 23, 6, 3, 3);

        ter.renderFrame(world);
    }

}
