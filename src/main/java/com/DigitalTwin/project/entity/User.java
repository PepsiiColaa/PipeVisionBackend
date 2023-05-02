package com.DigitalTwin.project.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.ID_WORKER)
    @ApiModelProperty(value = "用户ID")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(required = true, value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(required = true, value = "密码")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 类别
     */
    @ApiModelProperty(value = "用户类别（0是管理员，1是普通用户）")
    private Integer type;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态（0是禁用，1是启用）")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true, value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true, value = "更新时间")
    private Date updateTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(hidden = true, value = "是否删除")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}