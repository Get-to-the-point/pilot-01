package get.to.the.point.pilot01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// 게터, 세터, 이퀄, 해쉬, 투스트링을 컴파일때 자동으로 만들어줌
@Data
@AllArgsConstructor
public class UpdateMemberDto {
    private String email;
    private String password;
    private String name;
}
