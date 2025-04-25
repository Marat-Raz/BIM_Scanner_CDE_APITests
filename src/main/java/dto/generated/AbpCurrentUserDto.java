package dto.generated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class AbpCurrentUserDto {

    private Boolean isAuthenticated;
    private String id;
    private String tenantId;
    private String impersonatorUserId;
    private String impersonatorTenantId;
    private String impersonatorUserName;
    private String impersonatorTenantName;
    private String userName;
    private String name;
    private String surName;
    private String email;
    private Boolean emailVerified;
    private String phoneNumber;
    private Boolean phoneNumberVerified;
    private ArrayList<String> roles;
    private String sessionId;
}
