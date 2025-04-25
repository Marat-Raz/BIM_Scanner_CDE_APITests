package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpDateTimeFormatDto {

    private String calendarAlgorithmType;
    private String dateTimeFormatLong;
    private String shortDatePattern;
    private String fullDateTimePattern;
    private String dateSeparator;
    private String shortTimePattern;
    private String longTimePattern;
}
