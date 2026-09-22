package com.kite.libai.provider.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kite.libai.common.exception.ServiceException;
import com.kite.libai.common.utils.BeanUtils;
import com.kite.libai.provider.account.assemble.TripartiteAppAssemble;
import com.kite.libai.provider.account.model.entity.TripartiteApp;
import com.kite.libai.provider.account.model.request.TripartiteAppRequest;
import com.kite.libai.provider.account.repository.TripartiteAppRepository;
import org.springframework.stereotype.Service;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TripartiteAppService {

    private final TripartiteAppAssemble tripartiteAppAssemble;

    private final TripartiteAppRepository tripartiteAppRepository;


    public boolean create(TripartiteAppRequest request) {
        TripartiteApp tripartiteApp = tripartiteAppAssemble.toEntity(request);
        return tripartiteAppRepository.insert(tripartiteApp);
    }

    public boolean updateById(Long id, TripartiteAppRequest request) {
        TripartiteApp tripartiteApp = tripartiteAppRepository.getById(id);
        if (tripartiteApp == null) {
            throw new ServiceException("应用不存在");
        }
        BeanUtils.copy(request, tripartiteApp);
        return tripartiteAppRepository.updateById(tripartiteApp);
    }

    public boolean deleteById(Long id) {
        return tripartiteAppRepository.deleteById(id);
    }


    public TripartiteApp getById(Long id) {
        return tripartiteAppRepository.getById(id);
    }

    public Map<Long, TripartiteApp> batchGet(List<Long> ids) {
        List<TripartiteApp> list = tripartiteAppRepository.batchGetByIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().collect(Collectors.toMap(TripartiteApp::getId, Function.identity()));
        }
        return new HashMap<>(16);
    }

    public TripartiteApp getByAppCode(String appCode) {
        return tripartiteAppRepository.getByAppCode(appCode);
    }
}
