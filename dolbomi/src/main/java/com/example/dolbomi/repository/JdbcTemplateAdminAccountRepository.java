package com.example.dolbomi.repository;

import com.example.dolbomi.domain.AdminAccount;

import java.util.List;
import java.util.Optional;

public class JdbcTemplateAdminAccountRepository implements AdminAccountRepository{
    @Override
    public AdminAccount save(AdminAccount adminAccount) {
        return null;
    }

    @Override
    public Optional<AdminAccount> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<AdminAccount> findAll() {
        return null;
    }
}
