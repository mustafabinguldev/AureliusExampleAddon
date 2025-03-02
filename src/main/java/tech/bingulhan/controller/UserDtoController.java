package tech.bingulhan.controller;

import tech.bingulhan.dto.UserDto;
import tech.bingulhan.dto.request.UserRequest;
import tech.bingulhan.webserver.app.RestFulResponseStructure;
import tech.bingulhan.webserver.app.addon.AddonManager;

public class UserDtoController implements RestFulResponseStructure.RestFulResponse<UserDto, UserRequest> {

    @Override
    public UserDto response(UserRequest request) {

        UserDto userDto = new UserDto();
        userDto.setUsername(request.getUsername());
        userDto.setEmail(request.getEmail());

        return userDto;
    }

    @Override
    public UserRequest convert(String s) throws Exception {
        return (UserRequest) AddonManager.convertFromBodyJson(s, UserRequest.class);
    }

}
