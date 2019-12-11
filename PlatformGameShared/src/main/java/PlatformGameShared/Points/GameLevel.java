package PlatformGameShared.Points;

import java.util.List;

public class GameLevel {

    private String name;
    private int width;
    private int height;
    private List<GameLevelObject> objects;

    public GameLevel(String name, int width, int height, List<GameLevelObject> objects) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.objects = objects;
    }

    public GameLevel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<GameLevelObject> getObjects() {
        return objects;
    }

    public void setObjects(List<GameLevelObject> objects) {
        this.objects = objects;
    }

    public void addObject(GameLevelObject gameLevelObject){
        objects.add(gameLevelObject);
    }

    public void removeObject(GameLevelObject gameLevelObject){
        objects.remove(gameLevelObject);
    }
}
