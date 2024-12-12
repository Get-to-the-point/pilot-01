package get.to.the.point.pilot01.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정
    private Long id;

    @Column(nullable = false, length = 50) // 필수 입력 및 최대 길이 제한
    private String name;

    @Column(nullable = false, unique = true, length = 100) // 이메일은 유니크 제약 조건 추가
    private String email;

    @Column(nullable = false) // 필수 입력
    private String password;
}
