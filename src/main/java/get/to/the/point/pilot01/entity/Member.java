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

    @Column(length = 100, columnDefinition = "VARCHAR(100) DEFAULT 'ABC'")
    private String test;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // 생성 시 자동 설정
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP") // 수정 시 자동 설정
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1") // 기본값: 1
    private Boolean isActive;
}
