package com.example.dolbomi.repository;

import com.example.dolbomi.domain.AdminAccount;

import java.util.List;
import java.util.Optional;

public interface AdminAccountRepository {
    AdminAccount save (AdminAccount adminAccount);
    Optional<AdminAccount> findById(Long id);
    List<AdminAccount> findAll();
}
