package io.mosip.certify.controller;

import io.mosip.certify.dto.UserInfoDTO;
import io.mosip.certify.dto.UserInfoResponseDTO;
import io.mosip.certify.service.UserInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user-info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<UserInfoResponseDTO> createUserInfo(@Valid @RequestBody UserInfoDTO userInfoDTO) {
        return new ResponseEntity<>(userInfoService.createUserInfo(userInfoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{nationalUid}")
    public ResponseEntity<UserInfoDTO> getUserInfoByNationalUid(@PathVariable String nationalUid) {
        return ResponseEntity.ok(userInfoService.getUserInfoByNationalUid(nationalUid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable UUID id) {
        userInfoService.deleteUserInfo(id);
        return ResponseEntity.noContent().build();
    }
}