package io.mosip.certify.service;



import io.mosip.certify.dto.UserInfoDTO;
import io.mosip.certify.dto.UserInfoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserInfoService {

    UserInfoResponseDTO createUserInfo(UserInfoDTO userInfoDTO);

    String deleteUserInfo(UUID id);

    UserInfoDTO getUserInfoByNationalUid(String nationalUid);

    List<UserInfoDTO> getAllUsers();

    String deleteMultipleUsers(List<UUID> userInfoIds);
}