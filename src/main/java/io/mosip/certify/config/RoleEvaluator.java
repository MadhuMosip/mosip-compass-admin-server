package io.mosip.certify.config;// File: RoleEvaluator.java

import io.mosip.certify.config.RoleProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("roleEvaluator")
@RequiredArgsConstructor
public class RoleEvaluator {

    private final RoleProperties roleProperties;

    public boolean hasAnyRequiredRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return false;

        return authentication.getAuthorities().stream()
                .anyMatch(auth -> roleProperties.getRequiredRoles().stream()
                        .anyMatch(role -> auth.getAuthority().equalsIgnoreCase("ROLE_" + role.trim())));
    }
}
