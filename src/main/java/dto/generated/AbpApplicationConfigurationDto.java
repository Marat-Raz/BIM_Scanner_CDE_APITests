package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpApplicationConfigurationDto {

    private AbpApplicationLocalizationConfigurationDto localization;
    private AbpApplicationAuthConfigurationDto auth;
    private AbpApplicationSettingConfigurationDto setting;
    private AbpCurrentUserDto currentUser;
    private AbpApplicationFeatureConfigurationDto features;
    private AbpApplicationGlobalFeatureConfigurationDto globalFeatures;
    private AbpMultiTenancyInfoDto multiTenancy;
    private AbpCurrentTenantDto currentTenant;
    private AbpTimingDto timing;
    private AbpClockDto clock;
    private AbpObjectExtensionsDto objectExtensions;
    private Object extraProperties;
}
