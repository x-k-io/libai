package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.community.model.entity.Favorite;
import com.kite.libai.provider.community.model.request.FavoriteRequest;
import com.kite.libai.provider.community.model.response.FavoriteInternalResponse;
import com.kite.libai.provider.community.model.response.FavoriteResponse;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FavoriteAssemble {

    public Favorite toEntity(FavoriteRequest favoriteRequest) {
        if (favoriteRequest == null) {
            return null;
        }
        Favorite favorite = BeanUtils.copy(favoriteRequest, Favorite.class);
        return favorite;
    }

    public FavoriteResponse toResponse(Favorite favorite) {
        if (favorite == null) {
            return null;
        }
        FavoriteResponse response = BeanUtils.copy(favorite, FavoriteResponse.class);
        return response;
    }

    public List<FavoriteResponse> toResponse(List<Favorite> favorites) {
        if (CollectionUtils.isEmpty(favorites)) {
            return new ArrayList<>();
        }
        List<FavoriteResponse> responses = favorites.stream().map(favorite -> {
            FavoriteResponse response = BeanUtils.copy(favorite, FavoriteResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }

    public FavoriteInternalResponse toInternalResponse(Favorite favorite) {
        if (favorite == null) {
            return null;
        }
        FavoriteInternalResponse response = BeanUtils.copy(favorite, FavoriteInternalResponse.class);
        return response;
    }

    public List<FavoriteInternalResponse> toInternalResponse(List<Favorite> favorites) {
        if (CollectionUtils.isEmpty(favorites)) {
            return new ArrayList<>();
        }
        List<FavoriteInternalResponse> responses = favorites.stream().map(favorite -> {
            FavoriteInternalResponse response = BeanUtils.copy(favorite, FavoriteInternalResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }
}
