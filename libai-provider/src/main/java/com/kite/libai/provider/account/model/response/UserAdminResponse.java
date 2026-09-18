package com.kite.libai.provider.account.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UserAdminResponse", description = "账号信息")
public class UserAdminResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "登录名")
    private String loginName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别:UNKNOWN-未知,FEMALE-女,MALE-男")
    private String gender;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "生日")
    private String birthday;

    @Schema(description = "用户状态:NORMAL-正常,LOCKING-锁定")
    private String status;

    @Schema(description = "创建者")
    private String createdBy;

    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新者")
    private String updatedBy;

    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    @Schema(description = "是否删除0:否,1:是")
    private Boolean isDeleted;

}
