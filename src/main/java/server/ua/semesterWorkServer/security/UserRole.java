package server.ua.semesterWorkServer.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import server.ua.semesterWorkServer.entities.User;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    Roles role = Roles.USER;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
