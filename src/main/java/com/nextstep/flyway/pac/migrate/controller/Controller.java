package com.nextstep.flyway.pac.migrate.controller;

import com.nextstep.flyway.pac.migrate.model.UserInfoModel;
import com.nextstep.flyway.pac.migrate.repository.UserRepoSitory;
import com.nextstep.flyway.pac.migrate.response.ApiResposne;
import com.nextstep.flyway.pac.migrate.service.JwtService;
import com.nextstep.flyway.pac.migrate.service.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated

@RequestMapping("/dep/v1/get")
public class Controller {

    @Autowired
    UserInfo userInfo;

    @Autowired
    UserRepoSitory userRepoSitory;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/saveUser")
    public ResponseEntity<ApiResposne>saveUser(@Valid @RequestBody UserInfoModel userInfoModel){
        userInfo.saveUser(userInfoModel);

        ApiResposne apiResposne=ApiResposne.builder().status_message("success").status_code(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResposne);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<ApiResposne>logInUser(@RequestBody UserInfoModel userInfo) {
        UserInfoModel userInfoModel = userRepoSitory.findByEmail(userInfo.getEmail());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfoModel.getUsername(), userInfo.getPassword()));
        if (authentication.isAuthenticated()) {

            String token = jwtService.GenerateToken(userInfoModel.getUsername());
            ApiResposne apiResposne = ApiResposne.builder().status_message("token generated successFully").status_code(HttpStatus.OK.value()).token(token).build();

          return   ResponseEntity.status(HttpStatus.OK).body(apiResposne);
        } else {
            ApiResposne apiResposne = ApiResposne.builder().status_message("token not generated").status_code(HttpStatus.UNAUTHORIZED.value()).build();
         return    ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResposne);
        }
    }
    @PreAuthorize("hasAuthority('Admin')|| hasAuthority('User')")
    @GetMapping("/getUser")

    public ResponseEntity<ApiResposne>getUser(){
        List<UserInfoModel>list=userInfo.listofUser();

        ApiResposne apiResposne=ApiResposne.<UserInfoModel>builder().status_message("success").status_code(HttpStatus.OK.value()).list(list).build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResposne);
}


    @PreAuthorize("hasAuthority('Admin')|| hasAuthority('User')")
    @GetMapping("/getUser/{user_id}")

    public ResponseEntity<ApiResposne>getOne(@PathVariable ("user_id") int user_id){
UserInfoModel userInfoModel=userInfo.getOneUser(user_id);
ApiResposne apiResposne=ApiResposne.<UserInfoModel>builder().status_message("success").ab(userInfoModel).status_code(HttpStatus.OK.value()).build();

return  ResponseEntity.status(HttpStatus.OK).body(apiResposne);
    }


    }


