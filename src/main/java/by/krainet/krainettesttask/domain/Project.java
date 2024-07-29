package by.krainet.krainettesttask.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tb_user")
public class Project extends BaseDomain {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
