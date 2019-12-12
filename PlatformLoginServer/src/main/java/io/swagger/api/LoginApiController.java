package io.swagger.api;

import DatabaseConnector.LoginDatabaseConnectorMock;
import PlatformGameShared.Enums.LoginState;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T09:24:57.661Z")

@Controller
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private static final ILoginDatabaseConnector databaseConnector = LoginDatabaseConnectorMock.getInstance();

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PlayerLoginResponse> loginPlayer(@ApiParam(value = "Login data to check"  )  @Valid @RequestBody PlayerLoginData playerLogin) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                //return new ResponseEntity<PlayerLoginResponse>(objectMapper.readValue("{  \"playerNr\" : 1,  \"name\" : \"Mr Test\"}", PlayerLoginResponse.class), HttpStatus.NOT_IMPLEMENTED);
                LoginState loginState = databaseConnector.loginPlayer(playerLogin.getName(),playerLogin.getPassword());
                switch (loginState){
                    case SUCCESS:
                        return new ResponseEntity<PlayerLoginResponse>(objectMapper.readValue("{  \"playerNr\" : 1,  \"name\" : \""+playerLogin.getName()+"\"}", PlayerLoginResponse.class), HttpStatus.OK);
                    case INCORRECTDATA:
                        return new ResponseEntity<PlayerLoginResponse>(HttpStatus.FORBIDDEN);
                    case BANNED:
                        return new ResponseEntity<PlayerLoginResponse>(HttpStatus.UNAUTHORIZED);
                }

                return new ResponseEntity<PlayerLoginResponse>(objectMapper.readValue("{  \"playerNr\" : 1,  \"name\" : \"Mr Test\"}", PlayerLoginResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PlayerLoginResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlayerLoginResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
