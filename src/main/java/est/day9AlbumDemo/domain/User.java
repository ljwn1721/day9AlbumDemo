package est.day9AlbumDemo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동 증가
    private Long userId;

    @Column(name = "external_id", unique = true) // 외부 API ID
    private Long externalId;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String company;
    private String phone;
    private String city;

    @Column(nullable = false)
    private String password;

    @Builder
    public User(Long userId, Long externalId, String name, String email,
                String company, String phone, String city, String password) {
        this.userId = userId;
        this.externalId = externalId;
        this.name = name;
        this.email = email;
        this.company = company;
        this.phone = phone;
        this.city = city;
        this.password = password;
    }

    // Spring Security 관련
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}