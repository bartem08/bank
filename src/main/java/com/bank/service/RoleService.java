package com.bank.service;

import com.bank.model.Role;

public interface RoleService {

    void createRole(Role role);

    void deleteRole(Integer id);

    Role getRoleByRoleName(String role);

}
