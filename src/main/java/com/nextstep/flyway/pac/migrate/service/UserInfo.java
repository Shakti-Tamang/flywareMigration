package com.nextstep.flyway.pac.migrate.service;

import com.nextstep.flyway.pac.migrate.model.UserInfoModel;

import java.util.List;

public interface UserInfo {

    public  void saveUser(UserInfoModel userInfoModel);
    public List<UserInfoModel>listofUser();

    public UserInfoModel getOneUser(int id);
}
