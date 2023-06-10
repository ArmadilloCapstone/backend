package com.example.dolbomi.repository;

import com.example.dolbomi.domain.PickupMessage;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public interface PickupMessageRepository {
    PickupMessage save(PickupMessage pickupMessage);
    Optional<PickupMessage> findById(Long id);
    List<PickupMessage> findAll();
    void exportPickupLog(Long teacher_id, HttpServletResponse response);

}
