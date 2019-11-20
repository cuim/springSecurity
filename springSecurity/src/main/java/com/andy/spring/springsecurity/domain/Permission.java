package com.andy.spring.springsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_permission", indexes ={
        @Index(name = "pk_sys_permission_id",  columnList="id"),})
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    private Long id;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "pid", nullable = false)
    private Long pid;
}
