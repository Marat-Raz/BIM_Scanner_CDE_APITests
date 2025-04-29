package dto.generated;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AbpCurrentUserDto {

    public Boolean isAuthenticated;
    public String id;
    public String tenantId;
    public String impersonatorUserId;
    public String impersonatorTenantId;
    public String impersonatorUserName;
    public String impersonatorTenantName;
    public String userName;
    public String name;
    public String surName;
    public String email;
    public Boolean emailVerified;
    public String phoneNumber;
    public Boolean phoneNumberVerified;
    public ArrayList<String> roles;
    public String sessionId;
}
