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
 * @Date 2019/9/15  17:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SystemRoleDept", description = "系统角色部门对象")
@TableName("system_role_dept")
public class SystemRoleDept implements Serializable {

    private static final long serialVersionUID = -7843431222674536716L;

    @ApiModelProperty( value = "id", example = "1")
    @TableId
    private Long id;

    @ApiModelProperty(value = "角色ID" , example = "1")
    private Long roleId;

    @ApiModelProperty(value = "部门ID" , example = "1")
    private Long deptId;

}
