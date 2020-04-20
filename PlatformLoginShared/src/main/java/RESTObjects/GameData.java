package RESTObjects;

public class GameData {

    private String mapName;
    private String winnerName;
    private String[] playerNames;

    public GameData(String mapName, String winnerName, String[] playerNames) {
        this.mapName = mapName;
        this.winnerName = winnerName;
        this.playerNames = playerNames;
    }

    public GameData() {

    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public static boolean isValid(GameData gameData) {
        return gameData.mapName != null && gameData.winnerName != null && gameData.playerNames != null;
    }
}
