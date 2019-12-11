package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.MapReader;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Gamelevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T09:24:57.661Z")

@Controller
public class LevelApiController implements LevelApi {

    private static final Logger log = LoggerFactory.getLogger(LevelApiController.class);

    private static MapReader mapReader = MapReader.getInstance();

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LevelApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Gamelevel> getLevelByName(@ApiParam(value = "Name of the level that needs to be retrieved",required=true) @PathVariable("levelname") String levelname) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                //return new ResponseEntity<Gamelevel>(objectMapper.readValue("{  \"objects\" : [ {    \"ypos\" : 10,    \"solid\" : false,    \"xpos\" : 25,    \"kind\" : \"platform\",    \"width\" : 200,    \"height\" : 50  }, {    \"ypos\" : 10,    \"solid\" : false,    \"xpos\" : 25,    \"kind\" : \"platform\",    \"width\" : 200,    \"height\" : 50  } ],  \"name\" : \"battlefield\",  \"width\" : 600,  \"height\" : 600}", Gamelevel.class), HttpStatus.NOT_IMPLEMENTED);
                return new ResponseEntity<Gamelevel>(objectMapper.readValue(mapReader.readMap(levelname), Gamelevel.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Gamelevel>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch(IllegalArgumentException e){
                return new ResponseEntity<Gamelevel>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<Gamelevel>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<String>> getMaps() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                //return new ResponseEntity<List<String>>(objectMapper.readValue("[ \"facingworlds\", \"facingworlds\" ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                //List<String> list = objectMapper.readValue(mapReader.getAllMapNames(), List.class);
                return new ResponseEntity<List<String>>(mapReader.getAllMapsList(), HttpStatus.OK);
            } /*catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
           */
            catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<String>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
