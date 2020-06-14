package io.swagger.api;

import DatabaseConnector.LoginDatabaseJPA;
import DatabaseConnector.jpa.GameRepository;
import DatabaseConnector.jpa.PlayerRepository;
import PlatformGameShared.Enums.LoginState;
import interfaces.ILoginDatabaseConnector;
import io.swagger.annotations.ApiParam;
import io.swagger.model.PlayerLoginData;
import io.swagger.model.PlayerLoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T09:24:57.661Z")

@Controller
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private PlayerRepository playerRepository;

    private static ILoginDatabaseConnector databaseConnector;

    public LoginApiController(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        databaseConnector = LoginDatabaseJPA.getInstance(playerRepository, gameRepository);
    }

    public ResponseEntity<PlayerLoginResponse> loginPlayer(@ApiParam(value = "Login data to check") @Valid @RequestBody PlayerLoginData playerLogin, HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            //return new ResponseEntity<PlayerLoginResponse>(objectMapper.readValue("{  \"playerNr\" : 1,  \"name\" : \"Mr Test\"}", PlayerLoginResponse.class), HttpStatus.NOT_IMPLEMENTED);
            LoginState loginState = databaseConnector.loginPlayer(playerLogin.getName(), playerLogin.getPassword());
            switch (loginState) {
                case SUCCESS:
                    return new ResponseEntity<PlayerLoginResponse>(HttpStatus.OK);
                case INCORRECTDATA:
                    return new ResponseEntity<PlayerLoginResponse>(HttpStatus.FORBIDDEN);
                case BANNED:
                    return new ResponseEntity<PlayerLoginResponse>(HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<PlayerLoginResponse>(HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<PlayerLoginResponse>(HttpStatus.NOT_IMPLEMENTED);
    }


}
