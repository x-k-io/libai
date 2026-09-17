package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.community.model.entity.Channel;
import com.kite.libai.provider.community.model.request.ChannelRequest;
import com.kite.libai.provider.community.model.response.ChannelResponse;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ChannelAssemble {

    public Channel toEntity(ChannelRequest channelRequest) {
        if (channelRequest == null) {
            return null;
        }
        Channel channel = BeanUtils.copy(channelRequest, Channel.class);
        return channel;
    }

    public ChannelResponse toResponse(Channel channel) {
        if (channel == null) {
            return null;
        }
        ChannelResponse response = BeanUtils.copy(channel, ChannelResponse.class);
        return response;
    }

    public List<ChannelResponse> toResponse(List<Channel> channels) {
        if (CollectionUtils.isEmpty(channels)) {
            return new ArrayList<>();
        }
        List<ChannelResponse> responses = channels.stream().map(channel -> {
            ChannelResponse response = BeanUtils.copy(channel, ChannelResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }
}
