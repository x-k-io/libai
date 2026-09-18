package com.kite.libai.provider.community.model.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleSection extends BaseSection {

    private Long id;

    private Long authorId;

    private String type;

    private String authorType;

    private String title;

    private String subtitle;

    private String cover;

    private String pictures;

    private String excerpt;

    private String detail;

    private String video;

    private Integer videoLength;
}
