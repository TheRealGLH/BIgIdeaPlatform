package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.GamelevelObject;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Gamelevel
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-10T09:24:57.661Z")

public class Gamelevel   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("width")
  private Integer width = null;

  @JsonProperty("height")
  private Integer height = null;

  @JsonProperty("objects")
  @Valid
  private List<GamelevelObject> objects = new ArrayList<GamelevelObject>();

  public Gamelevel name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "battlefield", required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gamelevel width(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * Get width
   * @return width
  **/
  @ApiModelProperty(example = "600", value = "")


  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Gamelevel height(Integer height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * @return height
  **/
  @ApiModelProperty(example = "600", value = "")


  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Gamelevel objects(List<GamelevelObject> objects) {
    this.objects = objects;
    return this;
  }

  public Gamelevel addObjectsItem(GamelevelObject objectsItem) {
    this.objects.add(objectsItem);
    return this;
  }

  /**
   * Get objects
   * @return objects
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<GamelevelObject> getObjects() {
    return objects;
  }

  public void setObjects(List<GamelevelObject> objects) {
    this.objects = objects;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Gamelevel gamelevel = (Gamelevel) o;
    return Objects.equals(this.name, gamelevel.name) &&
        Objects.equals(this.width, gamelevel.width) &&
        Objects.equals(this.height, gamelevel.height) &&
        Objects.equals(this.objects, gamelevel.objects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, width, height, objects);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Gamelevel {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
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

