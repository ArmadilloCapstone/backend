package com.example.dolbomi.repository;

import com.example.dolbomi.controller.PickupRequestForm;

import java.util.List;

public interface PickupRepository {
    PickupRequestForm saveByParent(PickupRequestForm pickupRequestForm);
    List<PickupRequestForm> saveByGuardian(List<PickupRequestForm> pickupRequestFormList);
    List<PickupRequestForm> findAll();
    void clearPickupStore();
}
