package tech.bingulhan.controller;

import tech.bingulhan.dto.UserDto;
import tech.bingulhan.dto.request.UserRequest;
import tech.bingulhan.webserver.app.addon.AddonManager;
import tech.bingulhan.webserver.app.restful.RestFulResponseHelper;
import tech.bingulhan.webserver.app.restful.RestFulResponseStructure;
import tech.bingulhan.webserver.app.restful.cookie.CookieStructure;
import tech.bingulhan.webserver.app.restful.cookie.impl.CFHttpOnly;
import tech.bingulhan.webserver.app.restful.cookie.impl.CFMaxAge;

import java.util.Arrays;
import java.util.Random;

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

    @Override
    public void initializeSettings(RestFulResponseHelper restFulResponseHelper) {
        CookieStructure cookieStructure = new CookieStructure();
        cookieStructure.setCookieName("randomid2");
        cookieStructure.setCookieValue(""+new Random().nextInt(10000));
        cookieStructure.setFeatures(Arrays.asList(new CFMaxAge(3600), new CFHttpOnly()));
        restFulResponseHelper.sendCookie(cookieStructure);
    }

}
