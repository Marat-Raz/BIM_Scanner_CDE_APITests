package projectmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Project {

  String createdAt;
  String description;
  String id;
  String imageUrl;
  String name;
  String owner;
  String updateAt;

}
