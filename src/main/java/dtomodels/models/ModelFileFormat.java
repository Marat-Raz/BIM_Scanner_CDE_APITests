package dtomodels.models;

public enum ModelFileFormat {

  IFC("application/ifc"),
  GIFT_BINARY("application/gift-binary"),
  IFC_XML("bimscanner/ifc-xml"),
  XKT("application/xkt");

  private final String mediaType;

  ModelFileFormat(String mediaType) {
    this.mediaType = mediaType;
  }

  public String getMediaType() {
    return mediaType;
  }

  public String setMediaType() {
    return mediaType;
  }
}
