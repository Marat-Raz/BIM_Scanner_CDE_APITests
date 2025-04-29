package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import dto.generated.AbpInterfaceMethodApiDescriptionModel;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpControllerInterfaceApiDescriptionModel {

    public String type;
    public String name;
    public ArrayList<AbpInterfaceMethodApiDescriptionModel> methods;
}
