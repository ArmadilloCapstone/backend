package com.example.dolbomi.repository;

import com.example.dolbomi.controller.PickupRequestForm;

import java.util.List;

public interface PickupRepository {
    PickupRequestForm saveByParent(PickupRequestForm pickupRequestForm, Long class_id);
    PickupRequestForm saveByGuardian(PickupRequestForm pickupRequestForm, Long class_id);
    List<PickupRequestForm> findAll(Long class_id);
    void clearPickupStore(Long class_id);
}
