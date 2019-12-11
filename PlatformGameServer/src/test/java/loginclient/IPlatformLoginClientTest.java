package loginclient;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Points.GameLevel;
import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IPlatformLoginClientTest {

    IPlatformLoginClient client = new PlatformLoginClientMock();//we can't actually test connection to the REST server in CI

    @Test
    public void attemptLoginSuccess() {
        assertEquals(LoginState.SUCCESS,client.attemptLogin("test","123456"));
    }

    @Test
    public void attemptLoginInvalidDetails() {
        assertEquals(LoginState.INCORRECTDATA,client.attemptLogin("test","incorrectpassword"));
    }

    @Test
    public void getPlayerWins() {
    }

    @Test
    public void attemptRegistration() {
    }

    @Test
    public void testLevel() {
        System.out.println(client.getLevelContent("dustbowl"));
    }

    @Test
    public void convertLevel(){

        Gson gson = new Gson();

        GameLevel gameLevel = gson.fromJson(client.getLevelContent("battlefield"),GameLevel.class);
        System.out.println("a");
    }

}