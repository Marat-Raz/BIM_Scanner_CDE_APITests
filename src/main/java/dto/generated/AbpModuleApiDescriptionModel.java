package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbpModuleApiDescriptionModel {

    private String rootPath;
    private String remoteServiceName;
    private Object controllers;
}
