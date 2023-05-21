package com.example.dolbomi.repository;

import com.example.dolbomi.controller.PickupRequestForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPickupRepository implements PickupRepository{
    private static Map<Long, Map<Long, PickupRequestForm>> store= new HashMap<>();
    private static int MAXCLASS = 100;
    private static long[] sequence = new long[MAXCLASS];

    @Override
    public PickupRequestForm saveByParent(PickupRequestForm pickupRequestForm, Long class_id) {
        if (store.get(class_id).isEmpty() == true){
            Map<Long, PickupRequestForm> subStore = new HashMap<>();
            store.put(class_id, subStore);
            sequence[class_id.intValue()] = 0;
        }
        store.get(class_id).put(sequence[class_id.intValue()], pickupRequestForm);
        sequence[class_id.intValue()]++;
        return pickupRequestForm;
    }

    @Override
    public PickupRequestForm saveByGuardian(PickupRequestForm pickupRequestForm, Long class_id) {
        if (store.get(class_id).isEmpty() == true){
            Map<Long, PickupRequestForm> subStore = new HashMap<>();
            store.put(class_id, subStore);
            sequence[class_id.intValue()] = 0;
        }
        store.get(class_id).put(sequence[class_id.intValue()], pickupRequestForm);
        sequence[class_id.intValue()]++;
        return pickupRequestForm;
    }

    @Override
    public List<PickupRequestForm> findAll(Long class_id) {
        return new ArrayList<>(store.get(class_id).values());
    }

    public void clearPickupStore(Long class_id){
        store.get(class_id).clear();
        sequence[class_id.intValue()] = 0;
    }
}

