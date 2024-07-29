package by.krainet.krainettesttask.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tb_user")
public class User extends BaseDomain {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "fk_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
