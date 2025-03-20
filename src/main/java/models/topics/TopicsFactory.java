package models.topics;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import models.RandomWord;
import models.customfields.ListOfCustomField;

public class TopicsFactory {

  public String title = RandomWord.randomAllCharacters(1, 256);
  public String description = RandomWord.randomAllCharacters(1, 100); // todo 10000
  public String dueDate = LocalDateTime.now().plusDays(14)
      .atOffset(ZoneOffset.UTC)
      .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
      .replace("+00:00", "Z");
  public String assignedToId;
  public String typeId;
  public String statusId;
  public String priorityId;
  public ListOfCustomField customFields;
  public ArrayList<String> labels;

  public Topics createTopic(TopicType topicType) {
    switch (topicType) {
      case TOPIC_WITHOUT_TITLE:
        return new Topics(null, description, dueDate, assignedToId, typeId, statusId,
            priorityId, customFields, labels);
      case TOPIC_WITHOUT_DATA:
        return new Topics(null, null, null, null, null,
            null, null, null, null);
      case RANDOM_TOPIC:
        return new Topics(RandomWord.randomAllCharacters(1, 256), description, dueDate,
            assignedToId, typeId, statusId, priorityId, customFields, labels);
      case DEFAULT_TOPIC:
      default:
        return new Topics(title, description, dueDate, assignedToId, typeId, statusId, priorityId,
            customFields, labels);
    }
  }
}
