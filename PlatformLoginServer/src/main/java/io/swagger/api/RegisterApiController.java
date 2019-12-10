package io.swagger.api;

import PlatformGameShared.Enums.RegisterState;
import interfaces.ILoginDatabaseConnector;
import interfaces.LoginDatabaseConnectorMock;
import io.swagger.model.PlayerLoginData;
import io.swagger.model.PlayerLoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T09:24:57.661Z")

@Controller
public class RegisterApiController implements RegisterApi {

    private static final Logger log = LoggerFactory.getLogger(RegisterApiController.class);

    private static final ILoginDatabaseConnector databaseConnector = LoginDatabaseConnectorMock.getInstance();

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RegisterApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PlayerLoginResponse> registerPlayer(@ApiParam(value = "Login data to add"  )  @Valid @RequestBody PlayerLoginData playerLogin) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                //return new ResponseEntity<PlayerLoginResponse>(objectMapper.readValue("{  \"playerNr\" : 1,  \"name\" : \"Mr Test\"}", PlayerLoginResponse.class), HttpStatus.NOT_IMPLEMENTED);
                RegisterState registerState = databaseConnector.registerPlayer(playerLogin.getName(),playerLogin.getPassword());
                switch (registerState){

                    case SUCCESS:
                        return new ResponseEntity<PlayerLoginResponse>(objectMapper.readValue("{  \"playerNr\" : 1,  \"name\" : \""+playerLogin.getName()+"\"}", PlayerLoginResponse.class), HttpStatus.OK);
                    case INCORRECTDATA:
                        return new ResponseEntity<PlayerLoginResponse>(HttpStatus.FORBIDDEN);
                    case ALREADYEXISTS:
                        return new ResponseEntity<PlayerLoginResponse>(HttpStatus.CONFLICT);
                }
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PlayerLoginResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlayerLoginResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
