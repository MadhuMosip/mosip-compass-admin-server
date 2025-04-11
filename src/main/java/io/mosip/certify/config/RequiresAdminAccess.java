package io.mosip.certify.config;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@roleEvaluator.hasAnyRequiredRole()")
public @interface RequiresAdminAccess {
}
