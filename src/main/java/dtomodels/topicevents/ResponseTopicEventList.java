package dtomodels.topicevents;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseTopicEventList {

  public int totalCount;
  public ArrayList<ResponseTopicEvent> items;

}
