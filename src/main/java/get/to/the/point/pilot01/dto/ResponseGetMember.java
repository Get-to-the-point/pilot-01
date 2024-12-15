package get.to.the.point.pilot01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseGetMember {
        private Long id;
        private String email;
        private String name;
}
