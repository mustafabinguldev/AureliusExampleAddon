package tech.bingulhan;

import tech.bingulhan.webserver.app.PageStructure;
import tech.bingulhan.webserver.app.addon.Addon;
import tech.bingulhan.webserver.app.addon.AddonManager;

public class Main extends Addon {
    @Override
    public String getAddonName() {
        return "Example Addon!";
    }

    @Override
    public void onEnable() {
        System.out.println("Example Addon > Addon activated!");

        PageStructure.Builder builder = new PageStructure.Builder("/example-addon");
        builder.setPageHtmlData("<html><body><h1>Example Addon Page!</h1></body></html>");

        if (AddonManager.addPageData(builder.build())) {
            System.out.println("Addon page added!");
        }

    }

    @Override
    public void onDisable() {
        System.out.println("Example Addon > Addon is disabled.");
    }
}