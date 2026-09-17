package com.kite.libai.provider.community.model.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CommentResponse", description = "评论")
public class CommentResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "回复者id")
    private Long accountId;

    @ApiModelProperty(value = "实体类型")
    private String entityType;

    @ApiModelProperty(value = "实体id")
    private Long entityId;

    @ApiModelProperty(value = "作品id")
    private Long entryId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "回复数")
    private Integer replies;

    @ApiModelProperty(value = "是否作者")
    private Boolean authored;

    @ApiModelProperty(value = "回复者昵称")
    private String nickname;

    @ApiModelProperty(value = "回复者头像")
    private String avatar;

    @ApiModelProperty(value = "是否点赞")
    private Boolean liked;

    @ApiModelProperty(value = "时间描述")
    private String timeDescription;

}
