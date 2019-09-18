package com.example.demomybatis.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SystemUser", description = "系统用户对象")
@TableName("system_user")
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 6134902315227379214L;

    @TableId
    @ApiModelProperty(value = "用户ID",example = "1")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passWord;

    @ApiModelProperty(value = "salt值")
    private String salt;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "状态  0：禁用   1：正常",example = "1")
    private Integer status;

    @TableField(exist=false)
    @ApiModelProperty(value = "角色ID列表")
    private List<Long> roleIdList;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "部门ID",example = "1")
    private Long deptId;

    @ApiModelProperty(value = " 部门名称")
    @TableField(exist=false)
    private String deptName;
}