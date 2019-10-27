package uk.ivanc.archi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import uk.ivanc.archi.model.GithubService;
import uk.ivanc.archi.model.Item;
import uk.ivanc.archi.model.Repository;
import uk.ivanc.archi.model.User;

public class RepositoryActivity extends AppCompatActivity {

    private static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";
    private static final String TAG = "RepositoryActivity";

    private Toolbar toolbar;
    private TextView descriptionText;
    private TextView priceText;
    private TextView detailText;
    private TextView nameText;
//    private TextView forkText;
//    private TextView ownerNameText;
//    private TextView ownerEmailText;
//    private TextView ownerLocationText;
//    private ImageView ownerImage;
//    private View ownerLayout;
//    private TextView itemName;
//    private Item item;
//    public Item small_latte = new Item(1, "small latte", "coffee", 3.25, "nice local coffee bean, 8oz");
//    public Item medium_latte = new Item(2, "medium latte", "coffee", 3.75, "medium latte, 12oz");
//    public Item large_latte = new Item(1, "large latte", "coffee", 3.25, "served in special Halloween cup!");
//

//    private Subscription subscription;

    public static Intent newIntent(Context context, Item item) {
        Intent i = new Intent(context, RepositoryActivity.class);
        i.putExtra("name", item.getItemName());
        i.putExtra("description", item.getDescription());
        i.putExtra("price", Double.toString(item.getPrice()));
        //i.putExtra("item", item)
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        //dummy data

        descriptionText = (TextView) findViewById(R.id.text_item_description);
        priceText = (TextView) findViewById(R.id.price);
        detailText = (TextView) findViewById(R.id.detail_description);
        nameText = (TextView) findViewById(R.id.itemName);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String description = i.getStringExtra("description");
        String price = i.getStringExtra("price");
        //Item item = i.getExtra("name");
        //Item item =  i.getExtra("name_of_extra");

        bindItemData(name, description, price);
        //loadFullItem(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (subscription != null) subscription.unsubscribe();
    }

    private void bindItemData(final String name, final String description, final String price) {
        setTitle(name);
        descriptionText.setText(description);
        priceText.setText(price);
        detailText.setText(description);
        nameText.setText(name);
        // languageText.setVisibility(repository.hasLanguage() ? View.VISIBLE : View.GONE);
        //Preload image for user because we already have it before loading the full user
    }


    //private void bindOwnerData(final User owner) {
        //ownerNameText.setText(owner.name);
        //ownerEmailText.setText(owner.email);
        //ownerEmailText.setVisibility(owner.hasEmail() ? View.VISIBLE : View.GONE);
        //ownerLocationText.setText(owner.location);
        //ownerLocationText.setVisibility(owner.hasLocation() ? View.VISIBLE : View.GONE);
    //}
//


//    private void loadFullItem(String url) {
//        ArchiApplication application = ArchiApplication.get(this);
//        GithubService githubService = application.getGithubService();
//        subscription = githubService.userFromUrl(url)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(application.defaultSubscribeScheduler())
//                .subscribe(new Action1<User>() {
//                    @Override
//                    public void call(User user) {
//                        Log.i(TAG, "Full user data loaded " + user);
//                        bindItemData(user);
//                        ownerLayout.setVisibility(View.VISIBLE);
//                    }
//                });
//    }

//    private void loadFullItem(String url) {
//        ArchiApplication application = ArchiApplication.get(this);
//        GithubService githubService = application.getGithubService();
//
//                    @Override
//                    public void call(Item item) {
//                        Log.i(TAG, "Full user data loaded " + item);
//                        bindItemData(item);
//                        ownerLayout.setVisibility(View.VISIBLE);
//                    }
//                });
//    }
}
