package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "AccountAdminResponse", description = "用户信息")
public class AccountAdminResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户类型：正常-normal,马甲号-vest")
    private String type;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "昵称首字母")
    private String initials;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别:unknown-未知,female-女,male-男")
    private String gender;

    @Schema(description = "生日")
    private String birthday;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "省")
    private String province;

    @Schema(description = "市")
    private String city;

    @Schema(description = "区：朝阳区")
    private String district;

    @Schema(description = "实名认证")
    private Boolean identified;

    @Schema(description = "个人介绍")
    private String introduction;

    @Schema(description = "关注数")
    private Integer following;

    @Schema(description = "粉丝数")
    private Integer followers;

    @Schema(description = "作品数")
    private Integer entries;

    @Schema(description = "回复数")
    private Integer replies;

    @Schema(description = "点赞数")
    private Integer likes;

    @Schema(description = "阅读数")
    private Integer browses;

    @Schema(description = "播放数")
    private Integer plays;

    @Schema(description = "注册时间")
    private LocalDateTime registeredAt;

    @Schema(description = "用户状态: normal-正常,locked-锁定,forbidden-禁言")
    private String status;

    @Schema(description = "禁言类型")
    private String forbiddenType;

    @Schema(description = "禁言原因")
    private String reason;

    @Schema(description = "是否关注")
    private boolean follower;

    @Schema(description = "是否拉黑")
    private boolean blacklist;
}
