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
 * PlayerInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T09:24:57.661Z")

public class PlayerInfo   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("wins")
  private Integer wins = null;

  public PlayerInfo name(String name) {
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

  public PlayerInfo wins(Integer wins) {
    this.wins = wins;
    return this;
  }

  /**
   * Get wins
   * @return wins
  **/
  @ApiModelProperty(example = "17", value = "")


  public Integer getWins() {
    return wins;
  }

  public void setWins(Integer wins) {
    this.wins = wins;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayerInfo playerInfo = (PlayerInfo) o;
    return Objects.equals(this.name, playerInfo.name) &&
        Objects.equals(this.wins, playerInfo.wins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, wins);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlayerInfo {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    wins: ").append(toIndentedString(wins)).append("\n");
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

