package com.kite.libai.core.result;

import com.kite.libai.core.utils.ListUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> {
    List<T> records;

    private Long pageCount;

    private Long total;

    public static <T> PageResult<T> success() {
        return PageResult.success(ListUtils.of(), 0L, 0L);
    }

    public static <T> PageResult<T> success(List<T> records, Long pageCount, Long total) {
        return new PageResult<>(records, pageCount, total);
    }
}
