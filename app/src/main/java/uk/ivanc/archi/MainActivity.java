package uk.ivanc.archi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

import rx.Subscription;
import uk.ivanc.archi.model.Item;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Subscription subscription;
    private RecyclerView reposRecycleView;
    private Toolbar toolbar;
    private EditText editTextUsername;
    private ProgressBar progressBar;
    private TextView infoTextView;
    private ImageButton searchButton;

    private TextView money;
    private TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        infoTextView = (TextView) findViewById(R.id.text_info);

        //Set up money view !!!!!!
        money = (TextView) findViewById(R.id.money);
        slogan = (TextView) findViewById(R.id.slogan);

        //Set up ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Set up RecyclerView
        reposRecycleView = (RecyclerView) findViewById(R.id.repos_recycler_view);
        setupRecyclerView(reposRecycleView);
        // Set up search button
        searchButton = (ImageButton) findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                loadGithubRepos(editTextUsername.getText().toString());
                loadItems(editTextUsername.getText().toString());

            }
        });
        //Set up username EditText
        editTextUsername = (EditText) findViewById(R.id.edit_text_username);
        editTextUsername.addTextChangedListener(mHideShowButtonTextWatcher);
        editTextUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String username = editTextUsername.getText().toString();
                    if (username.length() > 0) loadItems(username);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) subscription.unsubscribe();
    }



    public void loadItems(String item_name) {

        System.out.println("Jie: Debug in loadItems()");


        // Hide money view !!!!!!
        money.setVisibility(View.GONE);
        slogan.setVisibility(View.GONE);

        progressBar.setVisibility(View.VISIBLE);
        reposRecycleView.setVisibility(View.GONE);
        infoTextView.setVisibility(View.GONE);


        // making dummy data for test

        System.out.println("Jie: trying to create a dummy inventory for test");

        Item small_latte = new Item(1, "small latte", "coffee", 3.25, "nice local coffee bean, 8oz");
        Item medium_latte = new Item(2, "medium latte", "coffee", 3.75, "medium latte, 12oz");
        Item large_latte = new Item(3, "large latte", "coffee", 4.25, "served in our special Halloween cup!");

        Item apple = new Item(11, "apple", "fruit", 1.70, "just mediocre apple");
        Item expensive_apple = new Item(12, "fuji apple", "fruit", 11.70, "best apple in the world!");
        Item watermelon = new Item(13, "watermelon", "fruit", 2.10, "summer time!");

        List<Item> inventory = new ArrayList();
        inventory.add(small_latte);
        inventory.add(medium_latte);
        inventory.add(large_latte);
        inventory.add(apple);
        inventory.add(expensive_apple);
        inventory.add(watermelon);

        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i).getItemName() + " loaded to the inventory");
        }

        // read API call
        //  List<Item> inventory = ItemService.getInventory();

        if (inventory != null) {

            List<Item> applicable_inventory = find_applicable_items(inventory, item_name);
            RepositoryAdapter adapter = (RepositoryAdapter) reposRecycleView.getAdapter();
            adapter.setRepositories(applicable_inventory);
            adapter.notifyDataSetChanged();

            if (reposRecycleView.getAdapter().getItemCount() > 0) {
                reposRecycleView.requestFocus();
                hideSoftKeyboard();
                reposRecycleView.setVisibility(View.VISIBLE);

            } else {
                infoTextView.setText(R.string.text_empty_inventory);
                infoTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    private List<Item> find_applicable_items(List<Item> inventory, String item_name) {
        List<Item> applicable_inventory = new ArrayList();
        for(int i = 0; i < inventory.size(); i++) {
            if ((inventory.get(i).getItemName().contains(item_name)) || (inventory.get(i).getCategoryName().contains(item_name))) {
                applicable_inventory.add(inventory.get(i));
            }
        }
        return applicable_inventory;
    }

//
//    public void loadGithubRepos(String username) {
//
//        // Hide money view !!!!!!
//        money.setVisibility(View.GONE);
//        slogan.setVisibility(View.GONE);
//
//        progressBar.setVisibility(View.VISIBLE);
//        reposRecycleView.setVisibility(View.GONE);
//        infoTextView.setVisibility(View.GONE);
//
//
//        // doing stuff here
//        ArchiApplication application = ArchiApplication.get(this);
//        ItemService itemService = application.getItemService();
//
//
//        subscription = githubService.publicRepositories(username)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(application.defaultSubscribeScheduler())
//                .subscribe(new Subscriber<List<Item>>() {
//                    @Override
//                    public void onCompleted() {
//                        progressBar.setVisibility(View.GONE);
//                        if (reposRecycleView.getAdapter().getItemCount() > 0) {
//                            reposRecycleView.requestFocus();
//                            hideSoftKeyboard();
//                            reposRecycleView.setVisibility(View.VISIBLE);
//                        } else {
//                            infoTextView.setText(R.string.text_empty_repos);
//                            infoTextView.setVisibility(View.VISIBLE);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable error) {
//                        Log.e(TAG, "Error loading GitHub repos ", error);
//                        progressBar.setVisibility(View.GONE);
//                        if (error instanceof HttpException
//                                && ((HttpException) error).code() == 404) {
//                            infoTextView.setText(R.string.error_username_not_found);
//                        } else {
//                            infoTextView.setText(R.string.error_loading_repos);
//                        }
//                        infoTextView.setVisibility(View.VISIBLE);
//                    }
//
//
//                    @Override
//                    public void onNext(List<Item> items) {
//
//                        // loading the items to recycle view!!!
//                        // TODO: It's the important part!!!
//
//                        RepositoryAdapter adapter =
//                                (RepositoryAdapter) reposRecycleView.getAdapter();
//                        adapter.setRepositories(items);
//                        adapter.notifyDataSetChanged();
//                    }
//                });


    private void setupRecyclerView(RecyclerView recyclerView) {
        RepositoryAdapter adapter = new RepositoryAdapter();
        adapter.setCallback(new RepositoryAdapter.Callback() {
            @Override
            public void onItemClick(Item item) {
                startActivity(RepositoryActivity.newIntent(MainActivity.this, item));
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
    }

    private TextWatcher mHideShowButtonTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            searchButton.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}
