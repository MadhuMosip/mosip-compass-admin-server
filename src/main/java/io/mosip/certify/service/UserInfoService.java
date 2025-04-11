package io.mosip.certify.service;



import io.mosip.certify.dto.UserInfoDTO;
import io.mosip.certify.dto.UserInfoResponseDTO;

import java.util.UUID;

public interface UserInfoService {
    UserInfoResponseDTO createUserInfo(UserInfoDTO userInfoDTO);
    void deleteUserInfo(UUID id);
    UserInfoDTO getUserInfoByNationalUid(String nationalUid);
}