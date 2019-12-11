package loginclient;

import PlatformGameShared.Enums.LoginState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IPlatformLoginClientTest {

    IPlatformLoginClient client = new PlatformLoginClientREST();

    @Test
    public void attemptLoginSuccess() {
        assertEquals(LoginState.SUCCESS,client.attemptLogin("test","letmein123"));
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

}