package models.classes;

import PlatformGameShared.Enums.InputType;
import PlatformGameShared.Enums.SpriteType;
import PlatformGameShared.Enums.SpriteUpdateType;
import PlatformGameShared.Points.GameLevel;
import PlatformGameShared.Points.GameLevelObject;
import PlatformGameShared.Points.SpriteUpdate;
import PlatformGameShared.Points.Vector2;
import PlatformGameShared.PropertiesLoader;
import models.classes.objects.*;
import models.enums.WeaponType;

import java.util.*;

public class Game implements Observer, IPlayerEventListener {


    private List<MovableObject> movableObjects = new ArrayList<>();
    private List<MovableObject> objectsToSpawn = new ArrayList<>();// we can't add stuff to our movableObjects list when we're looping through it
    private List<Platform> platforms = new ArrayList<>();
    private List<Vector2> weaponSpawnPoints;
    private Map<Integer, Player> playerNrMap;
    private List<SpriteUpdate> spriteUpdates;
    private int spriteCount = 0;
    private boolean firstUpdateComplete = false;
    private float levelWidth = 600;//these are default values for when we start a game without a GameLevel loaded
    private float levelHeight = 600;

    //keeping track of weapon spawning stuff
    private final static int maxTimeBetweenWeaponSpawns = Integer.parseInt(PropertiesLoader.getPropValues("game.maxTimeBetweenWeaponSpawns","game.properties"));;
    private int currentWeaponSpawnTarget;//once we reach this amount, we spawn a new weapon
    private int currentWeaponSpawnTime = 0;

    public Game() {
        playerNrMap = new HashMap<>();
        spriteUpdates = new ArrayList<>();
    }

    public void setUpGame(int[] playerNrs, String[] playerNames) {


        movableObjects = new ArrayList<>();
        weaponSpawnPoints = new ArrayList<>();
        setNewWeaponSpawnTarget();

        //all of these objects are hardcoded, we'll later have this be based on an actual map file


        //base platform
        Platform plat = new Platform(100, 50, 8000, 50, true);
        platforms.add(plat);
        createSprite(plat);
        //mini platforms

        plat = new Platform(100, 200, 100, 20, false);
        platforms.add(plat);
        createSprite(plat);
        plat = new Platform(350, 300, 100, 20, true);
        platforms.add(plat);
        createSprite(plat);
        plat = new Platform(600, 200, 100, 20, false);
        platforms.add(plat);
        createSprite(plat);
        plat = new Platform(800, 200, 100, 20, true);
        platforms.add(plat);
        createSprite(plat);


        for (int i = 0; i < playerNrs.length; i++) {
            Player p = new Player(10 * (i + 1) + 300, 200);
            createSprite(p);
            movableObjects.add(p);
            playerNrMap.put(playerNrs[i], p);
            p.setName(playerNames[i]);
            p.addShootEventListener(this);
        }

        for (int i = 1; i <= 5; i++) {
            WeaponPickup pickup = new WeaponPickup(60 * i, 200 * i, pickRandomWeapon());
            pickup.setVelocity(20 * i, 0);
            createSprite(pickup);
            movableObjects.add(pickup);
        }

    }

    public void setUpGame(int[] playerNrs, String[] playerNames, GameLevel gameLevel) {
        movableObjects = new ArrayList<>();
        weaponSpawnPoints = new ArrayList<>();
        setNewWeaponSpawnTarget();


        List<GameLevelObject> platformlevelobjects = new ArrayList<>();
        List<GameLevelObject> playerspawnobjects = new ArrayList<>();
        levelWidth = gameLevel.getWidth();
        levelHeight = gameLevel.getHeight();
        for (GameLevelObject object : gameLevel.getObjects()) {
            switch (object.getKind()) {
                case platform:
                    platformlevelobjects.add(object);
                    break;
                case playerspawn:
                    playerspawnobjects.add(object);
                    break;
                case weaponspawn:
                    weaponSpawnPoints.add(new Vector2(object.getXpos(), object.getYpos()));
                    break;
            }
        }
        for (GameLevelObject platformlevelobject : platformlevelobjects) {
            Platform plat = new Platform(platformlevelobject.getXpos(), platformlevelobject.getYpos(),
                    platformlevelobject.getWidth(), platformlevelobject.getHeight(), platformlevelobject.isSolid());
            platforms.add(plat);
            createSprite(plat);
        }

        int j = 0;
        if (playerspawnobjects.size() < 1) {
            System.out.println("[Game.java] The map " + gameLevel.getName() + " doesn't seem to have any player spawn points!! Adding one on (100,100).");
            playerspawnobjects.add(new GameLevelObject(GameLevelObject.KindEnum.playerspawn, 100, 100, 100, 100, false));
        }
        for (int i = 0; i < playerNrs.length; i++) {
            if (j >= playerspawnobjects.size()) j = 0;// there might be more players than there are spawnpoints
            Player p = new Player(platformlevelobjects.get(j).getXpos(), platformlevelobjects.get(j).getYpos());
            createSprite(p);
            movableObjects.add(p);
            playerNrMap.put(playerNrs[i], p);
            p.setName(playerNames[i]);
            p.addShootEventListener(this);
            j++;
        }
        if (weaponSpawnPoints.size() < 1) {
            System.out.println("[Game.java] The map " + gameLevel.getName() + " doesn't seem to have any weapon spawn points!! Adding one on (100,100).");
            weaponSpawnPoints.add(new Vector2(100, 100));
        }

    }


    private void spawnWeapon() {
        Vector2 point = weaponSpawnPoints.get(new Random().nextInt(weaponSpawnPoints.size()));
        WeaponPickup pickup = new WeaponPickup(point.getX(), point.getY(), pickRandomWeapon());
        createSprite(pickup);
        objectsToSpawn.add(pickup);
    }

    /**
     * Should speak for itself
     *
     * @return A randomly selected weapontype
     */
    private WeaponType pickRandomWeapon() {
        WeaponType type = null;
        Random r = new Random();
        type = WeaponType.values()[r.nextInt(WeaponType.values().length)];
        if (type == WeaponType.NONE) type = pickRandomWeapon();
        return type;
    }

    private void setNewWeaponSpawnTarget() {
        currentWeaponSpawnTarget = new Random().nextInt(maxTimeBetweenWeaponSpawns);
        System.out.println("{Game.java] Current weapon spawn target is " + currentWeaponSpawnTarget);
    }


    public void updateState() {
        //System.out.println("[Game.java] Update cycle: " + new java.util.Date());

        //Moving
        if (firstUpdateComplete) {
            spriteUpdates.clear();
        }
        firstUpdateComplete = true;
        //we loop through all of our movables and then do our update things
        for (MovableObject movableObject : movableObjects) {
            movableObject.update();
        }


        //Colliding
        for (MovableObject movableObject : movableObjects) {
            for (MovableObject colliding : movableObjects) {
                if (movableObject.collidesWith(colliding)) {
                    movableObject.onCollide(colliding, movableObject.getPosition());
                }
            }
        }

        //Checking if we're on a platform
        for (MovableObject movableObject : movableObjects) {
            for (Platform platform : platforms) {
                if (platform.collidesWith(movableObject)) {
                    movableObject.setGrounded(true);

                    //reallign if we're somewhat underneath the top of the platform
                    float pY = platform.getTopRight().getY();
                    float objY = movableObject.getPosition().getY();
                    float diff = objY - pY;
                    if (diff < -0.1f) {//can't seemingly compare exact float values
                        System.out.println(movableObject + " Diff: " + diff + " platform y " + pY + " obj " + objY);
                        movableObject.setPosition(movableObject.getPosition().getX(), pY, true);
                        movableObject.setAcceleration(movableObject.getAcceleration().getX(), 0);
                        movableObject.setVelocity(movableObject.getVelocity().getX(), 0);
                        movableObject.setTimeInAir(0);
                        if (platform.isSolid()) movableObject.invertVelocity();
                    }
                    break;//we don't want to keep looping through the other ones if we already found our platform
                } else {
                    movableObject.setGrounded(false);
                }
            }
        }

        //Creating new weapon pickup stuff
        currentWeaponSpawnTime++;
        if (currentWeaponSpawnTime >= currentWeaponSpawnTarget) {
            currentWeaponSpawnTime = 0;
            spawnWeapon();
        }


        //Spawning
        movableObjects.addAll(objectsToSpawn);
        objectsToSpawn.clear();


        //Cleaning

        //For objects in void
        for (MovableObject movableObject : movableObjects) {
            Vector2 pos = movableObject.getPosition();
            if (pos.getX() < 0 || pos.getY() < 0) movableObject.onOutOfBounds();
        }

        //Deleting of unneeded objects
        Iterator<MovableObject> i = movableObjects.iterator();
        while (i.hasNext()) {
            MovableObject object = i.next();
            if (object.isShouldBeCleaned()) {
                deleteSprite(object);
                i.remove();
            }
        }
    }

    public List<SpriteUpdate> getSpriteUpdates() {
        return spriteUpdates;
    }

    public void sendInput(int playerNr, InputType inputType) {
        playerNrMap.get(playerNr).handleInput(inputType);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof SpriteUpdate) {
            spriteUpdates.add((SpriteUpdate) o);
        }
    }

    private void createSprite(GameObject gameObject) {
        Vector2 pos = gameObject.getPosition();
        Vector2 scale = gameObject.getSize();
        spriteUpdates.add(new SpriteUpdate(spriteCount, pos, scale, SpriteUpdateType.CREATE, gameObject.getSpriteType(), false, "Y:" + gameObject.getTopRight().getY()));
        gameObject.addObserver(this);
        gameObject.setObjectNr(spriteCount);
        spriteCount++;
    }

    private void deleteSprite(GameObject gameObject) {
        spriteUpdates.add(new SpriteUpdate(gameObject.getObjectNr(), Vector2.Zero(), Vector2.Zero(), SpriteUpdateType.DESTROY, gameObject.getSpriteType(), false));
    }

    @Override
    public void onShootEvent(Player firer) {
        if (firer.getCurrentWeapon() != WeaponType.NONE) {
            Projectile p = ProjectileFactory.spawnProjectile(firer);
            objectsToSpawn.add(p);
            createSprite(p);
        }
    }

    @Override
    public void onDeathEvent(Player deadPlayer) {
        throw new UnsupportedOperationException("The method <> has not yet been implemented");
    }
}
