package models.dao;

import models.entity.Role;

public interface RoleDAO {
    Role findByRoleName(String roleName);
}