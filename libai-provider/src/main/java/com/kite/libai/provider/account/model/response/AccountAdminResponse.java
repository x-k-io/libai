package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AccountAdminResponse", description = "用户信息")
public class AccountAdminResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户类型：正常-normal,马甲号-vest")
    private String type;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "昵称首字母")
    private String initials;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别:unknown-未知,female-女,male-男")
    private String gender;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区：朝阳区")
    private String district;

    @ApiModelProperty(value = "实名认证")
    private Boolean identified;

    @ApiModelProperty(value = "个人介绍")
    private String introduction;

    @ApiModelProperty(value = "关注数")
    private Integer following;

    @ApiModelProperty(value = "粉丝数")
    private Integer followers;

    @ApiModelProperty(value = "作品数")
    private Integer entries;

    @ApiModelProperty(value = "回复数")
    private Integer replies;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "阅读数")
    private Integer browses;

    @ApiModelProperty(value = "播放数")
    private Integer plays;

    @ApiModelProperty(value = "注册时间")
    private LocalDateTime registeredAt;

    @ApiModelProperty(value = "用户状态: normal-正常,locked-锁定,forbidden-禁言")
    private String status;

    @ApiModelProperty(value = "禁言类型")
    private String forbiddenType;

    @ApiModelProperty(value = "禁言原因")
    private String reason;

    @ApiModelProperty(value = "是否关注")
    private boolean follower;

    @ApiModelProperty(value = "是否拉黑")
    private boolean blacklist;
}
