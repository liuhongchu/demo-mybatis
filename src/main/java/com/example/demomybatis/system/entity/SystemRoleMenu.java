package com.example.demomybatis.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SystemRoleMenu", description = "系统用户角色菜单对象")
@TableName("system_role_menu")
public class SystemRoleMenu implements Serializable {

    private static final long serialVersionUID = -5864391975380406011L;

    @ApiModelProperty(value = "id", example = "1")
    @TableId
    private Long id;

    @ApiModelProperty(value = "角色ID", example = "1")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID", example = "1")
    private Long menuId;
}
