package uk.ivanc.archi;

import android.app.Application;
import android.content.Context;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import uk.ivanc.archi.model.GithubService;
import uk.ivanc.archi.model.ItemService;

public class ArchiApplication extends Application {

    private GithubService githubService;
    private Scheduler defaultSubscribeScheduler;

    public static ArchiApplication get(Context context) {
        return (ArchiApplication) context.getApplicationContext();
    }

    public ItemService getItemService() {

        // TODO: get an instance of ItemService
        ItemService itemService = null;
        return itemService;

    }

    public GithubService getGithubService() {
        if (githubService == null) {
            githubService = GithubService.Factory.create();
        }
        return githubService;
    }

    //For setting mocks during testing
    public void setGithubService(GithubService githubService) {
        this.githubService = githubService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    //User to change scheduler from tests
    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }
}
