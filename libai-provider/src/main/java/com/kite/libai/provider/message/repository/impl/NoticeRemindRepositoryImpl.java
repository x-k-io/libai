package com.kite.libai.provider.message.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kite.libai.core.repository.MybatisBaseRepository;
import com.kite.libai.provider.message.mapper.NoticeRemindMapper;
import com.kite.libai.provider.message.model.entity.NoticeRemind;
import com.kite.libai.provider.message.repository.NoticeRemindRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeRemindRepositoryImpl extends MybatisBaseRepository<NoticeRemindMapper, NoticeRemind> implements NoticeRemindRepository {
    @Override
    public NoticeRemind getByAccountId(Long accountId) {
        LambdaQueryWrapper<NoticeRemind> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NoticeRemind::getAccountId, accountId);
        return this.baseMapper.selectOne(wrapper);
    }
}
