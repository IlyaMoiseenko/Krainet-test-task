package by.krainet.krainettesttask.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {
    USER ("USER"),
    ADMIN ("ADMIN");

    private final String name;
}
