package xyz.yuelai.pojo.domain;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 李泽众
 * @date 2019/7/15-12:56
 */

@ToString
@Entity
@Table(name = "auth_role")
public class RoleDO implements Serializable {
    private Long roleId;
    private String roleName;
    private String roleDesc;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Byte enable;

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "role_desc")
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }


    @Column(name = "gmt_create")
    public Timestamp getGmtCreate() {
        return (Timestamp) gmtCreate.clone();
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = (Timestamp) gmtCreate.clone();
    }

    @Column(name = "gmt_modified")
    public Timestamp getGmtModified() {
        return (Timestamp) gmtModified.clone();
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = (Timestamp) gmtModified.clone();
    }

    @Column(name = "is_enable")
    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }
}
