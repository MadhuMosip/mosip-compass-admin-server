package io.mosip.certify.service.impl;

import io.mosip.certify.dto.UserInfoDTO;
import io.mosip.certify.dto.UserInfoResponseDTO;
import io.mosip.certify.entity.UserInfo;
import io.mosip.certify.exception.AdminServerException;
import io.mosip.certify.mapper.UserInfoMapper;
import io.mosip.certify.repository.UserInfoRepository;
import io.mosip.certify.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional
    public UserInfoResponseDTO createUserInfo(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = userInfoMapper.toEntity(userInfoDTO);
        UserInfo savedUserInfo = userInfoRepository.save(userInfo);
        return userInfoMapper.toResponseDto(savedUserInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoDTO getUserInfoByNationalUid(String nationalUid) {
        UserInfo userInfo = userInfoRepository.findByNationalUid(nationalUid)
                .orElseThrow(() -> new AdminServerException("UserInfo not found with National UID: " + nationalUid));
        return userInfoMapper.toDto(userInfo);
    }

    @Override
    @Transactional
    public void deleteUserInfo(UUID id) {
        UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> new AdminServerException("UserInfo not found with National UID: " + id));
        userInfoRepository.delete(userInfo);
    }
}