package com.andy.spring.springsecurity.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "sys_role", indexes = {@Index(name = "pk_sys_role_id", columnList = "id")})
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    /**
     * 唯一标识
     */
    private Long id;
    /**
     * 名字
     */
    @Column(name = "name", nullable = false)
    private String name;


    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    @ManyToMany
    @JoinTable(name = "sys_role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    //1、关系维护端，负责多对多关系的绑定和解除
    //2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
    //3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Role)
    //4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为user_role
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即role_id
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
    private List<Permission> permissions;

    @Override
    public String getAuthority() {
        return name;
    }
}
