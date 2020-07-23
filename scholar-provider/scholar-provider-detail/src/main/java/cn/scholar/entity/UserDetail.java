package cn.scholar.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户详情表对应实体类
 */
@Data
public class UserDetail {
    private Integer userId;

    private String userImage;

    private String userName;

    private String userSex;

    private Integer userAge;

    private String userMajor;

    private String userField;

    private String userType;

    private String userSchool;

    private String description;

    private Date createTime;

}
