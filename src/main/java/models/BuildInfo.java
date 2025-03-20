package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BuildInfo {

  public int major;
  public int minor;
  public int patch;
  public String preRelease;
  public String buildMetadata;

}
