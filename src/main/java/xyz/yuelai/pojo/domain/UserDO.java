package xyz.yuelai.pojo.domain;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 李泽众
 * @date 2019/7/11-17:38
 *
 * 用户表
 */

@ToString
@Entity
@Table(name = "auth_user")
public class UserDO implements Serializable {

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Byte forbidden;


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    @Column(name = "is_forbidden")
    public Byte getForbidden() {
        return forbidden;
    }

    public void setForbidden(Byte forbidden) {
        this.forbidden = forbidden;
    }
}
