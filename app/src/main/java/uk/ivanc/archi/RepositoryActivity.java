package uk.ivanc.archi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import uk.ivanc.archi.model.GithubService;
import uk.ivanc.archi.model.Item;
import uk.ivanc.archi.model.Repository;
import uk.ivanc.archi.model.User;
import uk.ivanc.archi.MainActivity;


public class RepositoryActivity extends AppCompatActivity {

    private static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";
    private static final String TAG = "RepositoryActivity";

    private Toolbar toolbar;
    private TextView descriptionText;
    private TextView priceText;
    private TextView detailText;
    private TextView nameText;
    private RecyclerView reposRecycleView;
    private EditText editTextUsername;
    private TextView infoTextView;
    public static double originalPrice;
    private ImageButton searchButton;

    public static Intent newIntent(Context context, Item item) {
        Intent i = new Intent(context, RepositoryActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra("name", item.getItemName());
        i.putExtra("description", item.getDescription());
        i.putExtra("price", Double.toString(item.getPrice()));
        i.putExtra("category", item.getCategoryName());
        //i.putExtra("item", item)
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        infoTextView = (TextView) findViewById(R.id.text_item_description);
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
        String category = i.getStringExtra("category");
        originalPrice = Double.valueOf(price);
        //Item item = i.getExtra("name");
        //Item item =  i.getExtra("name_of_extra");

        bindItemData(name, description, price);
        reposRecycleView = (RecyclerView) findViewById(R.id.recommend_rec);
        setupRecyclerView(reposRecycleView);
        loadCheaperItems(name, Double.valueOf(price), category);
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

    private void loadCheaperItems(String name, double price, String category) {
//        progressBar.setVisibility(View.VISIBLE);
        reposRecycleView.setVisibility(View.GONE);
        infoTextView.setVisibility(View.GONE);

        List<Item> inventory = MainActivity.unique_dummy_inventory;


        if (inventory != null) {

            List<Item> applicable_inventory = find_cheaper_items(inventory, name, price, category);
            RepositoryAdapter adapter = (RepositoryAdapter) reposRecycleView.getAdapter();
            adapter.setRepositories(applicable_inventory);
            adapter.notifyDataSetChanged();
            System.out.println("Fuck me please");

            if (reposRecycleView.getAdapter().getItemCount() > 0) {
                reposRecycleView.requestFocus();
                reposRecycleView.setVisibility(View.VISIBLE);
                System.out.println("Fuck me please 1");

            } else {
                infoTextView.setText("No available recommendations");
                infoTextView.setVisibility(View.VISIBLE);
                System.out.println("Fuck me please 2");

            }
        }
    }

    private List<Item> find_cheaper_items(List<Item> inventory, String item_name, double price, String category) {
        System.out.println("Fuck: in find_cheaper_items()");
        System.out.println("Fuck: trying to find cheaper version for:" + item_name);

        List<Item> applicable_inventory = new ArrayList();
        for(int i = 0; i < inventory.size(); i++) {
            if ((inventory.get(i).getCategoryName().contains(category))) {
                if (find_same_keyword(inventory.get(i).getItemName(), item_name)) {
                    if ((inventory.get(i).getPrice() < price)) {
                        applicable_inventory.add(inventory.get(i));
                    }
                }
            }
        }

        System.out.println("Fuck:" + applicable_inventory);
        return applicable_inventory;
    }

    private boolean find_same_keyword(String string1, String string2) {

        //split the second string into words
        List<String> wordsOfSecond = Arrays.asList(string1.split(" "));

        //split and compare each word of the first string
        for (String word : string2.split(" ")) {
            if(wordsOfSecond.contains(word))
                return true;
        }
        return false;
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        RepositoryAdapter adapter = new RepositoryAdapter();
        adapter.setCallback(new RepositoryAdapter.Callback() {
            @Override
            public void onItemClick(Item item) {
                //startActivity(RepositoryActivity.newIntent(RepositoryActivity.this, item));
                MainActivity.total_investment += (originalPrice - item.getPrice());

                setContentView(R.layout.activity_main);
                Intent intent = new Intent(RepositoryActivity.this, MainActivity.class);
//                EditText editText = (EditText) findViewById(R.id.editText);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);


//                TextView money;
////                money = (TextView) findViewById(R.id.money);
////                money.setText(String.format("$%.2f", MainActivity.total_investment));
////                //Set up ToolBar
////                toolbar = (Toolbar) findViewById(R.id.toolbar);
////                setSupportActionBar(toolbar);
////                //Set up RecyclerView
////                reposRecycleView = (RecyclerView) findViewById(R.id.repos_recycler_view);
////                setupRecyclerView(reposRecycleView);
////                // Set up search button
////                searchButton = (ImageButton) findViewById(R.id.button_search);
////                setContentView(R.layout.activity_main);


                //directly finish
//                finish();

//                searchButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
////                loadGithubRepos(editTextUsername.getText().toString());
//                        MainActivity.loadItems(editTextUsername.getText().toString());
//
//                    }
//                });

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }





}
