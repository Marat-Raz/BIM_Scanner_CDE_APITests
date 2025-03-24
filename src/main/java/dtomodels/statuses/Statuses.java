package dtomodels.statuses;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode // todo добавить в другие классы, где нужно сравнить список отправленных и полученных запросов
public class Statuses {

  public String name;
  public String color;

}
