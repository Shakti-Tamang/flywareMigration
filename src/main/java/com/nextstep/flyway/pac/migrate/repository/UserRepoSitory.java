package com.nextstep.flyway.pac.migrate.repository;

import com.nextstep.flyway.pac.migrate.model.UserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoSitory extends JpaRepository<UserInfoModel,Integer> {
    public UserInfoModel findByUsername(String name);
    public  UserInfoModel findByEmail(String email);
}
