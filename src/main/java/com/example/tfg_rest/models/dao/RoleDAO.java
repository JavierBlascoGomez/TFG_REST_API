package com.example.tfg_rest.models.dao;

import com.example.tfg_rest.models.entity.Role;

public interface RoleDAO {
    Role findByRoleName(String roleName);
}