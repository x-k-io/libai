package com.kite.libai.webapp.controller.app.community;

import com.kite.libai.core.result.Result;
import com.kite.libai.provider.community.model.request.TopicRequest;
import com.kite.libai.provider.community.model.response.TopicCategoryResponse;
import com.kite.libai.provider.community.model.response.TopicResponse;
import com.kite.libai.provider.community.presenter.TopicPresenter;
import com.kite.libai.webapp.annotation.KitePermission;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/topics")
public class TopicController {

    private final TopicPresenter topicPresenter;

    @PostMapping
    @KitePermission
    public Result<Boolean> create(@Valid @RequestBody TopicRequest request) {
        boolean res = topicPresenter.create(request);
        return Result.success(res);
    }

    @GetMapping
    @KitePermission
    public Result<List<TopicCategoryResponse>> getTopics() {
        List<TopicCategoryResponse> list = topicPresenter.getTopics();
        return Result.success(list);
    }

    @GetMapping("/search")
    @KitePermission
    public Result<List<TopicResponse>> search(@RequestParam String name) {
        List<TopicResponse> list = topicPresenter.search(name);
        return Result.success(list);
    }
}
