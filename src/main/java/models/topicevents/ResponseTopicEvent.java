package models.topicevents;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import models.user.Author;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseTopicEvent {

  public String topicId;
  public String creationTime;
  public Author author;
  public ArrayList<TopicEvents> events;

}
