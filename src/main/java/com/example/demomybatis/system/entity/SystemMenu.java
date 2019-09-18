package com.example.demomybatis.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SystemMenu", description = "系统菜单对象")
@TableName("system_menu")
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 6277768994318674369L;

    @ApiModelProperty( value = "菜单id", example = "1")
    @TableId
    private Long menuId;

    @ApiModelProperty( value = "父菜单ID，一级菜单为0", example = "1")
    private Long parentId;

    @ApiModelProperty ( value = "菜单父节点名称")
    @TableField(exist=false)
    private String parentName;

    @ApiModelProperty( value = "菜单名称")
    private String name;

    @ApiModelProperty( value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    @ApiModelProperty(value = " 类型     0：目录   1：菜单   2：按钮",example = "123")
    private Integer type;

    @ApiModelProperty(value = "菜单图标" )
    private String icon;

    @ApiModelProperty(value = "排序", example = "123")
    private Integer orderNum;

    @ApiModelProperty(value = "ztree属性")
    @TableField(exist=false)
    private Boolean open;

    @ApiModelProperty(value = "列表")
    @TableField(exist=false)
    private List<?> list;
}
