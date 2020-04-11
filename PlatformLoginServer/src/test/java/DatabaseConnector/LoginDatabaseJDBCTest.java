package DatabaseConnector;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import interfaces.ILoginDatabaseConnector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class LoginDatabaseJDBCTest {

    //Commented because we cannot reach the DB from our VCS CI
    //private ILoginDatabaseConnector databaseConnector = LoginDatabaseJDBC.getInstance();
    private ILoginDatabaseConnector databaseConnector = LoginDatabaseJDBC.getInstance();

    @Before
    public void setUp() {
        System.out.println("Resetting database...");
        databaseConnector.resetData();
    }


    @Test
    public void removeData() {

    }

    @Test
    public void registerPlayer() {
        System.out.println("Testing registering user successfully.");
        String name = "mr test";
        String pw = "password123";
        RegisterState expectedRegisterState = RegisterState.SUCCESS;
        RegisterState actualRegisterState = databaseConnector.registerPlayer(name, pw);
        Assert.assertEquals(expectedRegisterState, actualRegisterState);
        LoginState expectedLoginState = LoginState.SUCCESS;
        LoginState actualLoginState = databaseConnector.loginPlayer(name, pw);
        Assert.assertEquals(expectedLoginState, actualLoginState);
    }

    @Test
    public void testRegisterAlreadyExists() {
        System.out.println("Testing registering an already existing user");
        String name = "mr test";
        String pw = "password123";
        RegisterState expectedRegisterState = RegisterState.SUCCESS;
        RegisterState actualRegisterState = databaseConnector.registerPlayer(name, pw);
        Assert.assertEquals(expectedRegisterState, actualRegisterState);
        expectedRegisterState = RegisterState.ALREADYEXISTS;
        actualRegisterState = databaseConnector.registerPlayer(name, pw);
        Assert.assertEquals(expectedRegisterState, actualRegisterState);
    }

    @Test
    public void testRegisterIncorrectDataName() {
        System.out.println("Testing registering username too short.");
        String name = "a";
        String pw = "password123";
        RegisterState expectedRegisterState = RegisterState.INCORRECTDATA;
        RegisterState actualRegisterState = databaseConnector.registerPlayer(name, pw);
        Assert.assertEquals(expectedRegisterState, actualRegisterState);
    }

    @Test
    public void testRegisterIncorrectDataPassword() {
        System.out.println("Testing registering password too short.");
        String name = "mr test";
        String pw = "a";
        RegisterState expectedRegisterState = RegisterState.INCORRECTDATA;
        RegisterState actualRegisterState = databaseConnector.registerPlayer(name, pw);
        Assert.assertEquals(expectedRegisterState, actualRegisterState);
    }

    @Test
    public void testLoginIncorrectData() {
        System.out.println("Testing registering user successfully.");
        String name = "mr test";
        String pw = "password123";
        String pwIncorrect = "differentpassword";
        RegisterState expectedRegisterState = RegisterState.SUCCESS;
        RegisterState actualRegisterState = databaseConnector.registerPlayer(name, pw);
        Assert.assertEquals(expectedRegisterState, actualRegisterState);
        LoginState expectedLoginState = LoginState.INCORRECTDATA;
        LoginState actualLoginState = databaseConnector.loginPlayer(name, pwIncorrect);
        Assert.assertEquals(expectedLoginState, actualLoginState);
    }

    @Test
    public void testPushGameEnd() {
        String[] names = {"fred", "steve", "coolio"};
        String victor = names[0];
        String map = "ctf_2fort";
        String password = "letmein123";
        for (String name : names) {
            databaseConnector.registerPlayer(name, password);
        }
        databaseConnector.addGame(map, victor, names);

    }

    @Test
    public void testGetPlayersFromMatch() {
        String[] expectedNames = {"fred", "steve", "coolio"};
        String victor = expectedNames[0];
        String map = "ctf_2fort";
        String password = "letmein123";
        for (String name : expectedNames) {
            databaseConnector.registerPlayer(name, password);
        }
        databaseConnector.addGame(map, victor, expectedNames);
        String[] actualNames = databaseConnector.getPlayersInMatch(1);
        Assert.assertEquals(expectedNames, actualNames);
    }

    @Test
    public void testPlayerWins() {
        int expectedWins = 1;
        String[] names = {"fred", "steve", "coolio"};
        String victor = names[0];
        String map = "ctf_2fort";
        String password = "letmein123";
        for (String name : names) {
            databaseConnector.registerPlayer(name, password);
        }
        databaseConnector.addGame(map, victor, names);
        int actualWins = databaseConnector.getPlayerWins(names[0]);
        Assert.assertEquals(expectedWins, actualWins);
    }

    @Test
    public void testPlayerWinsNone() {
        int expectedWins = 0;
        String[] names = {"fred", "steve", "coolio"};
        String victor = names[0];
        String map = "ctf_2fort";
        String password = "letmein123";
        for (String name : names) {
            databaseConnector.registerPlayer(name, password);
        }
        databaseConnector.addGame(map, victor, names);
        int actualWins = databaseConnector.getPlayerWins(names[1]);
        Assert.assertEquals(expectedWins, actualWins);
    }

    @Test
    public void testPlayerMatches() {
        String[] names = {"fred", "steve", "coolio"};
        String victor = names[0];
        String map = "ctf_2fort";
        String password = "letmein123";
        for (String name : names) {
            databaseConnector.registerPlayer(name, password);
        }
        databaseConnector.addGame(map, victor, names);
        databaseConnector.addGame(map, victor, names);
        int[] expectedAmount = {1, 2};
        int[] actualAmount = databaseConnector.getPlayerMatchIds(names[0]);
        Assert.assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void testPlayerMatchesNone() {
        String[] names = {"fred", "steve", "coolio"};
        String victor = names[0];
        String map = "ctf_2fort";
        String password = "letmein123";
        for (String name : names) {
            databaseConnector.registerPlayer(name, password);
        }
        databaseConnector.addGame(map, victor, names);
        databaseConnector.addGame(map, victor, names);
        int[] expectedAmount = {};
        int[] actualAmount = databaseConnector.getPlayerMatchIds(names[0] + "2");
        Assert.assertEquals(expectedAmount, actualAmount);
    }
}