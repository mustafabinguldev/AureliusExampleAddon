package tech.bingulhan;

import tech.bingulhan.controller.UserDtoController;
import tech.bingulhan.webserver.app.RestFulResponseStructure;
import tech.bingulhan.webserver.app.addon.Addon;
import tech.bingulhan.webserver.app.addon.AddonManager;
import tech.bingulhan.webserver.response.impl.restful.RestFulRequestType;

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
                            public String response(Object o) {
                                return "Hello world!";
                            }

                            @Override
                            public Object convert(String s) throws Exception {
                                return null;
                            }
                        }).
                        setRequestType(RestFulRequestType.GET).build());

    }

    @Override
    public void onDisable() {

    }
}