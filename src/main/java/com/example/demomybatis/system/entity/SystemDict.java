package com.example.demomybatis.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SystemDict", description = "系统字典对象")
@TableName("system_dict")
public class SystemDict implements Serializable {

    private static final long serialVersionUID = 8710067949725233192L;

    @ApiModelProperty(value = "id", example = "1")
    @TableId
    private Long id;

    @ApiModelProperty(value = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    private String name;

    @ApiModelProperty(value = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    private String type;

    @ApiModelProperty(value = "字典码")
    @NotBlank(message = "字典码不能为空")
    private String code;

    @ApiModelProperty(value = "字典值")
    @NotBlank(message = "字典值不能为空")
    private String value;

    @ApiModelProperty(value = "排序", example = "123")
    private Integer orderNum;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "删除标记  -1：已删除  0：正常", example = "123")
    @TableLogic
    private Integer delFlag;
}
