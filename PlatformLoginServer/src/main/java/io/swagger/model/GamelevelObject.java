package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * GamelevelObject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T09:24:57.661Z")

public class GamelevelObject   {
  /**
   * Gets or Sets kind
   */
  public enum KindEnum {
    PLATFORM("platform"),
    
    PLAYERSPAWN("playerspawn"),
    
    WEAPONSPAWN("weaponspawn");

    private String value;

    KindEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static KindEnum fromValue(String text) {
      for (KindEnum b : KindEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("kind")
  private KindEnum kind = null;

  @JsonProperty("xpos")
  private Integer xpos = null;

  @JsonProperty("ypos")
  private Integer ypos = null;

  @JsonProperty("width")
  private Integer width = null;

  @JsonProperty("height")
  private Integer height = null;

  @JsonProperty("solid")
  private Boolean solid = null;

  public GamelevelObject kind(KindEnum kind) {
    this.kind = kind;
    return this;
  }

  /**
   * Get kind
   * @return kind
  **/
  @ApiModelProperty(example = "platform", required = true, value = "")
  @NotNull


  public KindEnum getKind() {
    return kind;
  }

  public void setKind(KindEnum kind) {
    this.kind = kind;
  }

  public GamelevelObject xpos(Integer xpos) {
    this.xpos = xpos;
    return this;
  }

  /**
   * Get xpos
   * @return xpos
  **/
  @ApiModelProperty(example = "25", required = true, value = "")
  @NotNull


  public Integer getXpos() {
    return xpos;
  }

  public void setXpos(Integer xpos) {
    this.xpos = xpos;
  }

  public GamelevelObject ypos(Integer ypos) {
    this.ypos = ypos;
    return this;
  }

  /**
   * Get ypos
   * @return ypos
  **/
  @ApiModelProperty(example = "10", required = true, value = "")
  @NotNull


  public Integer getYpos() {
    return ypos;
  }

  public void setYpos(Integer ypos) {
    this.ypos = ypos;
  }

  public GamelevelObject width(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * Get width
   * @return width
  **/
  @ApiModelProperty(example = "200", value = "")


  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public GamelevelObject height(Integer height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * @return height
  **/
  @ApiModelProperty(example = "50", value = "")


  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public GamelevelObject solid(Boolean solid) {
    this.solid = solid;
    return this;
  }

  /**
   * Get solid
   * @return solid
  **/
  @ApiModelProperty(example = "false", value = "")


  public Boolean isSolid() {
    return solid;
  }

  public void setSolid(Boolean solid) {
    this.solid = solid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GamelevelObject gamelevelObject = (GamelevelObject) o;
    return Objects.equals(this.kind, gamelevelObject.kind) &&
        Objects.equals(this.xpos, gamelevelObject.xpos) &&
        Objects.equals(this.ypos, gamelevelObject.ypos) &&
        Objects.equals(this.width, gamelevelObject.width) &&
        Objects.equals(this.height, gamelevelObject.height) &&
        Objects.equals(this.solid, gamelevelObject.solid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(kind, xpos, ypos, width, height, solid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GamelevelObject {\n");
    
    sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
    sb.append("    xpos: ").append(toIndentedString(xpos)).append("\n");
    sb.append("    ypos: ").append(toIndentedString(ypos)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    solid: ").append(toIndentedString(solid)).append("\n");
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

