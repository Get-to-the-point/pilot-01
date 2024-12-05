package get.to.the.point.pilot01.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberVo {
    private Long id;
    private String email;
    private String name;
    private String test;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
}
