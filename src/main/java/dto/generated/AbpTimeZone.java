package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpTimeZone {

    private AbpIanaTimeZone iana;
    private AbpWindowsTimeZone windows;
}
