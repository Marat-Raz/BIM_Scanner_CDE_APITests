package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpDateTimeFormatDto {

    public String calendarAlgorithmType;
    public String dateTimeFormatLong;
    public String shortDatePattern;
    public String fullDateTimePattern;
    public String dateSeparator;
    public String shortTimePattern;
    public String longTimePattern;
}
