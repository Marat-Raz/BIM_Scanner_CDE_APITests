package dtomodels.topicevents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class TopicEvents {

  public String type;
  public Object value;

}
