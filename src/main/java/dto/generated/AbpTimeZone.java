package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.AbpIanaTimeZone;
import dto.generated.AbpWindowsTimeZone;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpTimeZone {

    public AbpIanaTimeZone iana;
    public AbpWindowsTimeZone windows;
}
