package com.andy.spring.springsecurity.repository;


import com.andy.spring.springsecurity.domain.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, Long> {
    @Query(value = "SELECT C.url, A.name " +
            " FROM sys_role AS A LEFT JOIN sys_role_permission B ON A.id=B.role_id LEFT JOIN sys_permission AS C ON B.permission_id=C.id",
            nativeQuery = true)
    List<Object[]> getRolePermissions();
}
