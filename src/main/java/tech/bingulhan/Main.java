package tech.bingulhan;

import tech.bingulhan.controller.UserDtoController;
import tech.bingulhan.webserver.app.addon.Addon;
import tech.bingulhan.webserver.app.addon.AddonManager;
import tech.bingulhan.webserver.app.restful.RestFulResponseHelper;
import tech.bingulhan.webserver.app.restful.RestFulResponseStructure;
import tech.bingulhan.webserver.app.restful.cookie.CookieStructure;
import tech.bingulhan.webserver.app.restful.cookie.impl.CFHttpOnly;
import tech.bingulhan.webserver.app.restful.cookie.impl.CFMaxAge;
import tech.bingulhan.webserver.response.impl.restful.RestFulRequestType;

import java.util.Arrays;
import java.util.Random;

public class Main extends Addon {
    @Override
    public String getAddonName() {
        return "Example Addon!";
    }

    @Override
    public void onEnable() {

        //domoin:8080/api/test POST
        AddonManager.
                registerRestFulService(new RestFulResponseStructure.Builder("test").
                        setRestFulResponse(new UserDtoController()).
                setRequestType(RestFulRequestType.POST).build());

        //domoin:8080/api/hello GET

        AddonManager.
                registerRestFulService(new RestFulResponseStructure.Builder("hello").
                        setRestFulResponse(new RestFulResponseStructure.RestFulResponse<String, Object>() {
                            @Override
                            public String response(Object o, RestFulResponseHelper helper) {
                                CookieStructure cookieStructure = new CookieStructure();
                                cookieStructure.setCookieName("randomid");
                                cookieStructure.setCookieValue(""+new Random().nextInt(10000));
                                cookieStructure.setFeatures(Arrays.asList(new CFMaxAge(3600), new CFHttpOnly()));
                                helper.sendCookie(cookieStructure);

                                return "Hello world ! Path data:"+ Arrays.toString(helper.getPathData());
                            }

                            @Override
                            public Object convert(String s) throws Exception {
                                return null;
                            }

                        }).
                        setRequestType(RestFulRequestType.GET).
                        build());

        //domoin:8080/api/hello/etc/hello2 GET


        AddonManager.
                registerRestFulService(new RestFulResponseStructure.Builder("hello/hello2").
                        setRestFulResponse(new RestFulResponseStructure.RestFulResponse<String, Object>() {
                            @Override
                            public String response(Object o, RestFulResponseHelper helper) {
                                CookieStructure cookieStructure = new CookieStructure();
                                cookieStructure.setCookieName("randomid3");
                                cookieStructure.setCookieValue(""+new Random().nextInt(10000));
                                cookieStructure.setFeatures(Arrays.asList(new CFMaxAge(3600), new CFHttpOnly()));
                                helper.sendCookie(cookieStructure);

                                return "Hello world 2 ! Path data:"+ Arrays.toString(helper.getPathData());
                            }

                            @Override
                            public Object convert(String s) throws Exception {
                                return null;
                            }

                        }).
                        setRequestType(RestFulRequestType.GET).
                        build());

    }

    @Override
    public void onDisable() {

    }
}