package dtomodels.topicevents;

import dto.generated.CdeTopicEventDto;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseTopicEventList { // todo заменить на PaginatedResponse

  public int totalCount;
  public ArrayList<CdeTopicEventDto> items;

}
