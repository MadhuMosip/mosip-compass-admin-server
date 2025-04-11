package io.mosip.certify.controller;

import io.mosip.certify.dto.UserInfoDTO;
import io.mosip.certify.dto.UserInfoResponseDTO;
import io.mosip.certify.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user-info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Operation(
            summary = "Create a new user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("hasRole('admin')")
    @PostMapping
    public ResponseEntity<UserInfoResponseDTO> createUserInfo(@Valid @RequestBody UserInfoDTO userInfoDTO) {
        return new ResponseEntity<>(userInfoService.createUserInfo(userInfoDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get user by national UID",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{nationalUid}")
    public ResponseEntity<UserInfoDTO> getUserInfoByNationalUid(@PathVariable String nationalUid) {
        return ResponseEntity.ok(userInfoService.getUserInfoByNationalUid(nationalUid));
    }

    @Operation(
            summary = "Delete user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable UUID id) {
        userInfoService.deleteUserInfo(id);
        return ResponseEntity.noContent().build();
    }
}