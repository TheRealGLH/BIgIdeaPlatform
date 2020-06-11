package DatabaseConnector;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import RESTObjects.GameData;
import interfaces.ILoginDatabaseConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDatabaseConnectorMock implements ILoginDatabaseConnector {

    List<MockGameStorage> mockGameStorageList;
    Map<String, String> usernamePasswordMap;
    static LoginDatabaseConnectorMock instance = null;
    static GameData dummyGameData = new GameData();

    private LoginDatabaseConnectorMock() {
        usernamePasswordMap = new HashMap<>();
        usernamePasswordMap.put("test", "letmein123");
        usernamePasswordMap.put("fred", "letmein123");

        mockGameStorageList = new ArrayList<>();
        //We add this because our DB starts counting at 1, so this is effectively dummy data and we want our tests to
        //work with both the DB and our mock implementation.
        dummyGameData.setMapName("a");
        dummyGameData.setPlayerNames(new String[]{"a", "b"});
        dummyGameData.setWinnerName("a");
        mockGameStorageList.add(new MockGameStorage(dummyGameData));
    }

    public static LoginDatabaseConnectorMock getInstance() {
        if (instance == null) instance = new LoginDatabaseConnectorMock();
        return instance;
    }

    @Override
    public LoginState loginPlayer(String name, String password) {
        LoginState loginState = LoginState.INCORRECTDATA;
        String pw = usernamePasswordMap.get(name);
        if (pw == null) return loginState;
        if (pw.equals(password)) {
            loginState = LoginState.SUCCESS;
        }
        return loginState;
    }

    @Override
    public RegisterState registerPlayer(String name, String password) {
        RegisterState registerState = RegisterState.ALREADYEXISTS;
        if (name.length() <= 3 || password.length() < 6) {
            registerState = RegisterState.INCORRECTDATA;
            return registerState;
        }
        if (usernamePasswordMap.get(name) == null) {
            registerState = RegisterState.SUCCESS;
            usernamePasswordMap.put(name, password);
        }
        return registerState;
    }

    @Override
    public void addGame(String map, String victor, String[] players) {
        mockGameStorageList.add(new MockGameStorage(map, players, victor));
    }

    @Override
    public GameData getGameData(int ID) {
        return mockGameStorageList.get(ID).gameData;
    }

    @Override
    public String[] getPlayersInMatch(int ID) {
        return mockGameStorageList.get(ID).gameData.getPlayerNames();
    }

    @Override
    public int getPlayerWins(String name) {
        int count = 0;
        for (MockGameStorage mockGameStorage : mockGameStorageList) {
            if (mockGameStorage.gameData.getWinnerName().equals(name)) count++;
        }
        return count;
    }

    @Override
    public int[] getPlayerMatchIds(String name) {
        List<Integer> matchIDs = new ArrayList<>();
        for (MockGameStorage mockGameStorage : mockGameStorageList) {
            for (String playerName : mockGameStorage.gameData.getPlayerNames()) {
                if (playerName == name) matchIDs.add(mockGameStorageList.indexOf(mockGameStorage));
            }
        }
        int[] array = new int[matchIDs.size()];
        for (Integer matchID : matchIDs) {
            array[matchIDs.indexOf(matchID)] = matchID;
        }
        return array;
    }

    @Override
    public void resetData() {
        usernamePasswordMap.clear();
        mockGameStorageList.clear();
        //We add this because our DB starts counting at 1, so this is effectively dummy data and we want our tests to
        //work with both the DB and our mock implementation.
        mockGameStorageList.add(new MockGameStorage(dummyGameData));
    }
}


class MockGameStorage {

    GameData gameData;

    public MockGameStorage(String map, String[] players, String victor) {
        gameData = new GameData(map, victor, players);
    }

    public MockGameStorage(GameData gameData) {
        this.gameData = gameData;
    }
}