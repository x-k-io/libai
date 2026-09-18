package com.kite.libai.provider.community.model.response;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CommentAdminResponse", description = "评论")
public class CommentAdminResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "回复者id")
    private Long accountId;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "实体id")
    private Long entityId;

    @Schema(description = "作品id")
    private Long entryId;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "点赞数")
    private Integer likes;

    @Schema(description = "回复数")
    private Integer replies;

    @Schema(description = "是否作者")
    private Boolean authored;

    @Schema(description = "回复者昵称")
    private String nickname;

    @Schema(description = "回复者头像")
    private String avatar;

    @Schema(description = "是否点赞")
    private Boolean liked;

    @Schema(description = "时间描述")
    private String timeDescription;

}
