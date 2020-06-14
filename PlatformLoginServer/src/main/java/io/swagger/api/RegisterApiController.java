package io.swagger.api;

import DatabaseConnector.LoginDatabaseJPA;
import DatabaseConnector.jpa.GameRepository;
import DatabaseConnector.jpa.PlayerRepository;
import PlatformGameShared.Enums.RegisterState;
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
public class RegisterApiController implements RegisterApi {

    private static final Logger log = LoggerFactory.getLogger(RegisterApiController.class);

    private static ILoginDatabaseConnector databaseConnector;
    private PlayerRepository playerRepository;


    public RegisterApiController(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        databaseConnector = LoginDatabaseJPA.getInstance(playerRepository, gameRepository);
    }

    public ResponseEntity<PlayerLoginResponse> registerPlayer(@ApiParam(value = "Login data to add") @Valid @RequestBody PlayerLoginData playerLogin, HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            //return new ResponseEntity<PlayerLoginResponse>(objectMapper.readValue("{  \"playerNr\" : 1,  \"name\" : \"Mr Test\"}", PlayerLoginResponse.class), HttpStatus.NOT_IMPLEMENTED);
            RegisterState registerState = databaseConnector.registerPlayer(playerLogin.getName(), playerLogin.getPassword());
            switch (registerState) {

                case SUCCESS:
                    return new ResponseEntity<PlayerLoginResponse>(HttpStatus.OK);
                case INCORRECTDATA:
                    return new ResponseEntity<PlayerLoginResponse>(HttpStatus.NOT_ACCEPTABLE);
                case ALREADYEXISTS:
                    return new ResponseEntity<PlayerLoginResponse>(HttpStatus.CONFLICT);
            }

        }

        return new ResponseEntity<PlayerLoginResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
