package com.example.dolbomi.repository;

import com.example.dolbomi.controller.PickupRequestForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPickupRepository implements PickupRepository{
    private static Map<Long, PickupRequestForm> store= new HashMap<>();
    private static long sequence = 0L;
    @Override
    public PickupRequestForm saveByParent(PickupRequestForm pickupRequestForm) {
        store.put(sequence, pickupRequestForm);
        sequence++;
        return pickupRequestForm;
    }

    @Override
    public List<PickupRequestForm> saveByGuardian(List<PickupRequestForm> pickupRequestFormList) {
        int count = pickupRequestFormList.size();
        for(int i = 0; i < count; i++){
            store.put(sequence,pickupRequestFormList.get(i));
            sequence++;
        }
        return pickupRequestFormList;
    }

    @Override
    public List<PickupRequestForm> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearPickupStore(){
        store.clear();
        sequence = 0L;
    }
}
