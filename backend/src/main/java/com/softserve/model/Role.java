package com.softserve.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Locale;

@Getter
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    private String name;

    public enum Roles {
        BLOGGER, MODERATOR, ADMIN, ANY
    }

    public Role(String name) {
        this.name = Roles.valueOf(name.toUpperCase(Locale.ROOT))
                .toString().toLowerCase(Locale.ROOT);
    }

    public Role() {
    }
}

