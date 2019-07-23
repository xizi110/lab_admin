package xyz.yuelai.pojo.domain;

import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 李泽众
 * @date 2019/7/23-9:50
 */

@ToString
@Entity
@Table(name = "auth_permission")
@Setter
public class PermissionDO implements Serializable {

    private Long permissionId;
    private String permissionURI;
    private String permissionName;
    private String permissionDesc;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Byte enable;

    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPermissionId() {
        return permissionId;
    }

    @Column(name = "permission_uri")
    public String getPermissionURI() {
        return permissionURI;
    }

    @Column(name = "permission_name")
    public String getPermissionName() {
        return permissionName;
    }

    @Column(name = "permission_desc")
    public String getPermissionDesc() {
        return permissionDesc;
    }

    @Column(name = "gmt_create")
    public Timestamp getGmtCreate() {
        return (Timestamp) gmtCreate.clone();
    }

    @Column(name = "gmt_modified")
    public Timestamp getGmtModified() {
        return (Timestamp) gmtModified.clone();
    }

    @Column(name = "is_enable")
    public Byte getEnable() {
        return enable;
    }
}
