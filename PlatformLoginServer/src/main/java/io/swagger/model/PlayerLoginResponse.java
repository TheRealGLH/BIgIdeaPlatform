package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PlayerLoginResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T09:24:57.661Z")

public class PlayerLoginResponse   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("playerNr")
  private Integer playerNr = null;

  public PlayerLoginResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Mr Test", required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PlayerLoginResponse playerNr(Integer playerNr) {
    this.playerNr = playerNr;
    return this;
  }

  /**
   * Get playerNr
   * @return playerNr
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull


  public Integer getPlayerNr() {
    return playerNr;
  }

  public void setPlayerNr(Integer playerNr) {
    this.playerNr = playerNr;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayerLoginResponse playerLoginResponse = (PlayerLoginResponse) o;
    return Objects.equals(this.name, playerLoginResponse.name) &&
        Objects.equals(this.playerNr, playerLoginResponse.playerNr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, playerNr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlayerLoginResponse {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    playerNr: ").append(toIndentedString(playerNr)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

