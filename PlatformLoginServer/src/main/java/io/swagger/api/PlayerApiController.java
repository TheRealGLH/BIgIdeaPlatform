package io.swagger.api;

import DatabaseConnector.LoginDatabaseJDBC;
import RESTObjects.GameData;
import interfaces.ILoginDatabaseConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.InvalidParameterException;

@Controller
public class PlayerApiController {

    private ILoginDatabaseConnector databaseConnector = LoginDatabaseJDBC.getInstance();


    @RequestMapping(value = "player/{playerName}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Integer> getGameData(@PathVariable(name = "playerId") String playerName) {
        try {
            int wins = databaseConnector.getPlayerWins(playerName);
            return new ResponseEntity<>(wins, HttpStatus.OK);
        } catch (InvalidParameterException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
