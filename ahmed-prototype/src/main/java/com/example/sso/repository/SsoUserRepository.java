package com.example.sso.repository;

import com.example.sso.domain.SsoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SsoUserRepository extends JpaRepository<SsoUser, String> {
}
