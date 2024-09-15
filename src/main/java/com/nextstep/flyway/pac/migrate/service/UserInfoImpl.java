package com.nextstep.flyway.pac.migrate.service;

import com.nextstep.flyway.pac.migrate.model.UserInfoModel;
import com.nextstep.flyway.pac.migrate.repository.UserRepoSitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoImpl implements UserInfo {

    @Autowired
    UserRepoSitory userRepoSitory;


//    it works with security config
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void saveUser(UserInfoModel userInfoModel) {
        userInfoModel.setPassword(passwordEncoder.encode(userInfoModel.getPassword()));
        userRepoSitory.save(userInfoModel);
    }

    @Override
    public List<UserInfoModel> listofUser() {

        List<UserInfoModel>list=userRepoSitory.findAll();
        return list.isEmpty()? new ArrayList<>():list;
    }

    @Override
    public UserInfoModel getOneUser(int id) {

        Optional<UserInfoModel> userInfoModel=userRepoSitory.findById(id);
        return userInfoModel.orElse(null);
    }
}
