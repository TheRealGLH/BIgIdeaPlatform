package DatabaseConnector;

import PlatformGameShared.Enums.LoginState;
import PlatformGameShared.Enums.RegisterState;
import interfaces.ILoginDatabaseConnector;
import org.apache.juli.logging.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import static org.junit.Assert.*;

public class LoginDatabaseJDBCTest {

    private ILoginDatabaseConnector databaseConnector = LoginDatabaseConnectorMock.getInstance();

    @Before
    public void setUp() {
        System.out.println("Resetting database...");
        databaseConnector.resetData();
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
}