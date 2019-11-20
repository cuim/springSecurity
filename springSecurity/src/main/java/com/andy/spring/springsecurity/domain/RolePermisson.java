package com.andy.spring.springsecurity.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class RolePermisson implements Serializable {

    private String url;
    private String roleName;

    public RolePermisson() {
    }

    public RolePermisson(String url, String roleName) {
        super();
        this.url = url;
        this.roleName = roleName;
    }
}
