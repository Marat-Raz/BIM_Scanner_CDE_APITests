package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import dto.generated.AbpApplicationAuthConfigurationDto;
import dto.generated.AbpApplicationSettingConfigurationDto;
import dto.generated.AbpCurrentTenantDto;
import dto.generated.AbpObjectExtensionsDto;
import dto.generated.AbpMultiTenancyInfoDto;
import dto.generated.AbpApplicationLocalizationConfigurationDto;
import dto.generated.AbpApplicationFeatureConfigurationDto;
import dto.generated.AbpTimingDto;
import dto.generated.AbpClockDto;
import dto.generated.AbpCurrentUserDto;
import dto.generated.AbpApplicationGlobalFeatureConfigurationDto;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpApplicationConfigurationDto {

    public AbpApplicationLocalizationConfigurationDto localization;
    public AbpApplicationAuthConfigurationDto auth;
    public AbpApplicationSettingConfigurationDto setting;
    public AbpCurrentUserDto currentUser;
    public AbpApplicationFeatureConfigurationDto features;
    public AbpApplicationGlobalFeatureConfigurationDto globalFeatures;
    public AbpMultiTenancyInfoDto multiTenancy;
    public AbpCurrentTenantDto currentTenant;
    public AbpTimingDto timing;
    public AbpClockDto clock;
    public AbpObjectExtensionsDto objectExtensions;
}
