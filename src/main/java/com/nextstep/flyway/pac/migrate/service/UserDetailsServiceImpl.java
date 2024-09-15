package com.nextstep.flyway.pac.migrate.service;

import com.nextstep.flyway.pac.migrate.model.UserInfoModel;
import com.nextstep.flyway.pac.migrate.repository.UserRepoSitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepoSitory userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfoModel user = userRepo.findByUsername(username);
        if(user == null){

            throw new UsernameNotFoundException("could not found user..!!");
        }

        return new UserDetailInfo(user);
    }


}