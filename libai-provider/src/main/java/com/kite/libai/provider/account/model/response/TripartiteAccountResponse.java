package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TripartiteAccountResponse", description = "三方绑定信息信息")
public class TripartiteAccountResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "appCode")
    private String appCode;

    @Schema(description = "appId")
    private String appId;

    @Schema(description = "open_id")
    private String openId;

    @Schema(description = "union_id")
    private String unionId;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "账号id")
    private Long accountId;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "创建者")
    private String createdBy;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    @Schema(description = "更新者")
    private String updatedBy;

    @Schema(description = "是否删除0:否,1:是")
    private Boolean isDeleted;

}
