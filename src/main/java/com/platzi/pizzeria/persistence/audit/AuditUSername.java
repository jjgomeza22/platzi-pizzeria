package com.platzi.pizzeria.persistence.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditUSername implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !!authentication.isAuthenticated()) {
            return null;
        }
        String user = authentication.getPrincipal().toString();
        return Optional.of(user);
    }
}
