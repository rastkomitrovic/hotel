package com.fon.hotel.dto;

public class RoleDTO {

    private long roleId;

    private String roleName;

    public RoleDTO() {

    }

    public RoleDTO(long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO role = (RoleDTO) o;
        return roleId == role.roleId;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
