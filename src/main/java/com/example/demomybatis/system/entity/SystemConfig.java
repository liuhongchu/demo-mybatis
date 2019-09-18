package com.example.demomybatis.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Date 2019/9/15  17:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SystemConfig", description = "系统设置对象")
@TableName("system_config")
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 9173424794843945387L;

    @TableId
    @ApiModelProperty(value = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "参数ky")
    @NotBlank(message = "参数名不能为空")
    private String paramKey;

    @ApiModelProperty(value = "参数velue")
    @NotBlank(message = "参数值不能为空")
    private String paramValue;

    @ApiModelProperty(value = "备注")
    private String remark;
}
