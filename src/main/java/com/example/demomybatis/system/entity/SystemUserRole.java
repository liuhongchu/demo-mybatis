package com.example.demomybatis.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
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
@ApiModel(value = "SystemUserRole", description = "系统用户角色对象")
@TableName("system_user_role")
public class SystemUserRole implements Serializable {

    private static final long serialVersionUID = 8380651406185215832L;

    @ApiModelProperty(value = "id", example = "1")
    @TableId
    private Long id;

    @ApiModelProperty(value = "用户id", example = "1")
    private Long userId;

    @ApiModelProperty(value = "菜单ID", example = "1")
    private Long roleId;
}
