package com.example.demomybatis.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SystemDept", description = "系统部门对象")
@TableName("system_dept")
public class SystemDept implements Serializable {

    private static final long serialVersionUID = 5875929603909579439L;

    @ApiModelProperty(value = "部门ID", example = "1")
    @TableId
    private Long deptId;

    @ApiModelProperty(value = "上级部门ID，一级部门为0", example = "1")
    private Long parentId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "上级部门名称")
    @TableField(exist = false)
    private String parentName;

    @ApiModelProperty(value = "id", example = "123")
    private Integer orderNum;

    @ApiModelProperty(value = "删除标志", example = "123")
    @TableLogic
    private Integer delFlag;

    @ApiModelProperty(value = "ztree属性")
    @TableField(exist = false)
    private Boolean open;

    @ApiModelProperty(value = "列表")
    @TableField(exist = false)
    private List<?> list;
}
