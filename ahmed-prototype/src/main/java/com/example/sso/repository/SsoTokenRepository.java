package com.example.sso.repository;

import com.example.sso.domain.SsoToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SsoTokenRepository extends JpaRepository<SsoToken, String> {
}
