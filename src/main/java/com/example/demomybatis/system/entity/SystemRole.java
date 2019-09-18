package com.example.demomybatis.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SystemRole", description = "系统角色对象")
@TableName("system_role")
public class SystemRole implements Serializable {

    private static final long serialVersionUID = 713521404831435041L;

    @ApiModelProperty(value = "角色ID",example = "1")
    @TableId
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;


    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "部门ID",example = "1")
    @NotNull(message = "部门不能为空")
    private Long deptId;

    @ApiModelProperty(value = "部门名称")
    @TableField(exist = false)
    private String deptName;

    @ApiModelProperty(value = "菜单列表")
    @TableField(exist = false)
    private List<Long> menuIdList;

    @ApiModelProperty(value = "部门列表")
    @TableField(exist = false)
    private List<Long> deptIdList;


    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
