package kr.co.ict.project.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="use")
@Table(name="users")
public class UserEntity {
    @Id   
    private String userId;
    private String password;
    private String email;
    private String type;
    private String role;
}