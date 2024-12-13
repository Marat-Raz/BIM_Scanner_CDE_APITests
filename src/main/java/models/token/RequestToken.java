package models.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestToken {
private String grand_type, username, password, scope, client_id, client_secret;
}
