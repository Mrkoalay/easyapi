package cn.easyapi.rest.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户查询参数",description="用户查询参数")
public class UserParam {
    @ApiModelProperty(value="邮件内容",name="title",example="test",required=true)
    private String userName;
}
