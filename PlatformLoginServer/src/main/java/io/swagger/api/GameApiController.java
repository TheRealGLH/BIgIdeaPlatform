package io.swagger.api;

import DatabaseConnector.LoginDatabaseJPA;
import DatabaseConnector.jpa.GameRepository;
import DatabaseConnector.jpa.PlayerRepository;
import RESTObjects.GameData;
import interfaces.ILoginDatabaseConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GameApiController {

    private ILoginDatabaseConnector databaseConnector;

    public GameApiController(PlayerRepository playerRepository, GameRepository gameRepository) {
        databaseConnector = LoginDatabaseJPA.getInstance(playerRepository, gameRepository);
    }


    @RequestMapping(value = "game/{matchId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<GameData> getGameData(@PathVariable(name = "matchID") Integer matchID) {
        GameData gameData = databaseConnector.getGameData(matchID);
        if (gameData == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(gameData, HttpStatus.OK);
    }


    @RequestMapping(value = "game/{matchId}/players/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<String[]> getPlayersFromMatch(@PathVariable(name = "matchID") Integer matchID) {
        String[] names = databaseConnector.getPlayersInMatch(matchID);
        if (names.length == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @RequestMapping(value = "game/players/{playerName}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Integer> getWinnerFromMatch(@PathVariable(name = "playerName") String playerName) {
        int wins = databaseConnector.getPlayerWins(playerName);
        return new ResponseEntity<>(wins, HttpStatus.OK);
    }

    @PostMapping(value = "/game",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<String> submitGameStats(@RequestBody GameData JSON, HttpServletRequest servletRequest) {
        if (!GameData.isValid(JSON)) return new ResponseEntity<String>("Invalid gamedata sent", HttpStatus.BAD_REQUEST);
        //TODO a more intelligent system to determine post statuses or something?
        databaseConnector.addGame(JSON.getMapName(), JSON.getWinnerName(), JSON.getPlayerNames());
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
