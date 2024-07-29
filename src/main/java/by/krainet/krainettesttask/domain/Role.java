package by.krainet.krainettesttask.domain;

import by.krainet.krainettesttask.common.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Role extends BaseDomain {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Roles name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;
}
