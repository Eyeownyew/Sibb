package com.sibb.world;

import com.sibb.Main;
import com.sibb.Window;
import tiled.core.*;
import tiled.io.TMXMapReader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Tiled Map Loader
 *
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */

public class MapLoader {
    private static MapLoader instance = null;

    /**
     * Method getInstance.
     *
     * @return MapLoader
     */
    public static MapLoader getInstance() {
        if (instance == null)
            new MapLoader();
        return instance;
    }

    private void createMinimap() {
        Graphics2D mapGraphics = null;
        int mapWidth = (map.getWidth() * map.getTileWidth());
        int mapHeight = (map.getHeight() * map.getTileHeight());

        minimap = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
        mapGraphics = (Graphics2D) minimap.getGraphics();

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                for (int z = 0; z < map.getLayerCount(); z++) {
                    Tile t = ((TileLayer) map.getLayer(z)).getTileAt(x, y);
                    if (t != null) {
                        mapGraphics.drawImage(t.getImage(), x << 5, y << 5, null);
                    }
                }
            }
        }
        minimap = minimap.getScaledInstance((int) (Main.app.getWidth() * .25f),
                (int) (Main.app.getHeight() * .25f), Image.SCALE_SMOOTH);
        minimapCreated = true;
    }

    /**
     * Method drawMap.
     *
     * @param g Graphics2D
     */
    public void drawMap(Graphics2D g) {
        startX = Window.getCamera().getX() / map.getTileWidth();
        startY = Window.getCamera().getY() / map.getTileHeight();
        endX = (Window.getFrame().getWidth() / map.getTileWidth()) + startX + 1;
        endY = (Window.getFrame().getHeight() / map.getTileHeight()) + startY + 2;
        if (startX < 0)
            startX = 0;
        else if (endX > mapObjects.length)
            endX = mapObjects.length;
        if (startY < 0)
            startY = 0;
        else if (endY > mapObjects[0].length)
            endY = mapObjects[0].length;

        if (mapLoaded) {
            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    for (int i = 0; i < mapObjects[x][y].length; i++) {
                        Object currentObject = mapObjects[x][y][i];
                        if (currentObject == null)
                            continue;
                        if (currentObject instanceof Tile)
                            g.drawImage(((Tile) currentObject).getImage(), (x * map.getTileWidth()) - Window.getCamera().getX(),
                                    (y * map.getTileHeight()) - Window.getCamera().getY(), null);
                        if (currentObject instanceof MapObject)
                            g.drawImage(((MapObject) currentObject).getImage(1),
                                    (x * map.getTileWidth()) - Window.getCamera().getX(), (y * map.getTileHeight()) - Window.getCamera().getY(), null);

                    }
                }
            }
        }
    }

    int startX = 0, startY = 0;
    int endX = 0, endY = 0;

    /**
     * Method getMapSize.
     *
     * @return Dimension
     */
    public Dimension getMapSize() {
        return new Dimension(map.getWidth() * map.getTileWidth(), map.getHeight()
                * map.getTileHeight());
    }

    /**
     * Method getMinimap.
     *
     * @return Image
     */
    public Image getMinimap() {
        if (!minimapCreated)
            createMinimap();
        return minimap;
    }

    /**
     * Method loadMap.
     *
     * @param mapLocation String
     * @return Map
     */
    public Map loadMap(String mapLocation) {
        try {
            TMXMapReader mapReader = new TMXMapReader();
            map = mapReader.readMap(mapLocation);
            mapObjects = new Object[map.getWidth()][map.getHeight()][map.getLayerCount()];
            createLayers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(map.toString() + " loaded");

        return map;
    }

    public void createLayers() {
        final int tileWidth = map.getTileWidth();
        final int tileHeight = map.getTileHeight();

        int startX = 0, startY = 0;
        int endX = Window.getFrame().getWidth() / tileWidth;
        int endY = Window.getFrame().getHeight() / tileHeight;
        for (int i = 0; i < map.getLayerCount(); i++) {
            if (map.getLayer(i) instanceof TileLayer) {
                TileLayer layer = (TileLayer) map.getLayer(i);
                for (int x = startX; x < map.getWidth(); x++) {
                    for (int y = startY; y < map.getHeight(); y++) {
                        Tile tile = layer.getTileAt(x, y);
                        if (tile == null)
                            continue;
                        final Image image = tile.getImage();
                        if (image == null)
                            continue;
                        mapObjects[x][y][i] = tile;
                    }
                }
            } else if (map.getLayer(i) instanceof ObjectGroup) {
                ObjectGroup layerObjects = (ObjectGroup) map.getLayer(i);
                for (int x = startX; x < endX; x++) {
                    for (int y = startY; y < endY; y++) {
                        MapObject object = layerObjects.getObjectAt(x, y);
                        if (object == null)
                            continue;
                        if (object.getImage(1D) == null)
                            continue;
                        mapObjects[x][y][i] = object;
                    }
                }
            }
        }
        mapLoaded = true;
    }

    public MapLoader() {
        instance = this;
    }

    /**
     * Constructor for MapLoader.
     *
     * @param map Map
     */
    public MapLoader(Map map) {
        this.map = map;
    }

    private boolean mapLoaded = false;

    private Map map;

    /**
     * Method getMap.
     *
     * @return Map
     */
    public Map getMap() {
        return map;
    }

    private Image minimap;

    boolean minimapCreated = false;

    private Object[][][] mapObjects = null;
}
