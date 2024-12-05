package get.to.the.point.pilot01.dto.controller.request;

import lombok.Data;

@Data
public class UpdateMemberRequest {
    private Long id; // ID가 필요하다면 추가
    private String email;
    private String password;
    private String name;

    public UpdateMemberRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
