package com.kite.libai.provider.community.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kite.libai.core.utils.BeanUtils;
import com.kite.libai.provider.community.model.entity.Place;
import com.kite.libai.provider.community.model.request.PlaceRequest;
import com.kite.libai.provider.community.model.response.PlaceInternalResponse;
import com.kite.libai.provider.community.model.response.PlaceResponse;
import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PlaceAssemble {

    public Place toEntity(PlaceRequest placeRequest) {
        if (placeRequest == null) {
            return null;
        }
        Place place = BeanUtils.copy(placeRequest, Place.class);
        return place;
    }

    public PlaceResponse toResponse(Place place) {
        if (place == null) {
            return null;
        }
        PlaceResponse response = BeanUtils.copy(place, PlaceResponse.class);
        return response;
    }

    public List<PlaceResponse> toResponse(List<Place> places) {
        if (CollectionUtils.isEmpty(places)) {
            return new ArrayList<>();
        }
        List<PlaceResponse> responses = places.stream().map(place -> {
            PlaceResponse response = BeanUtils.copy(place, PlaceResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }

    public PlaceInternalResponse toInternalResponse(Place place) {
        if (place == null) {
            return null;
        }
        PlaceInternalResponse response = BeanUtils.copy(place, PlaceInternalResponse.class);
        return response;
    }

    public List<PlaceInternalResponse> toInternalResponse(List<Place> places) {
        if (CollectionUtils.isEmpty(places)) {
            return new ArrayList<>();
        }
        List<PlaceInternalResponse> responses = places.stream().map(place -> {
            PlaceInternalResponse response = BeanUtils.copy(place, PlaceInternalResponse.class);
            return response;
        }).collect(Collectors.toList());
        return responses;
    }
}
