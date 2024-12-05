package get.to.the.point.pilot01.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CreateMemberDto {

    @Getter
    private Long id;
    private String email;
    private String password;
    private String name;

    public CreateMemberDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
