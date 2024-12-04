package get.to.the.point.pilot01.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정
    private Long id;

    @Column(nullable = false, length = 50) // 필수 입력 및 최대 길이 제한
    private String name;

    @Column(nullable = false, unique = true, length = 100) // 이메일은 유니크 제약 조건 추가
    private String email;

    @Column(nullable = false) // 필수 입력
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false) // 생성 시 자동 설정, 수정 불가
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false) // 회원 상태 필수
    private boolean isActive = true; // 기본값: 활성 상태

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
