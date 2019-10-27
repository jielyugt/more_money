package uk.ivanc.archi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import uk.ivanc.archi.model.Item;

public class RepositoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView descriptionText;
    private TextView priceText;
    private TextView detailText;
    private TextView nameText;

    public static Intent newIntent(Context context, Item item) {
        Intent i = new Intent(context, RepositoryActivity.class);
        i.putExtra("name", item.getItemName());
        i.putExtra("description", item.getDescription());
        i.putExtra("price", Double.toString(item.getPrice()));
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


        descriptionText = (TextView) findViewById(R.id.text_item_description);
        priceText = (TextView) findViewById(R.id.price);
        detailText = (TextView) findViewById(R.id.detail_description);
        nameText = (TextView) findViewById(R.id.itemName);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String description = i.getStringExtra("description");
        String price = i.getStringExtra("price");
        bindItemData(name, description, price);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void bindItemData(final String name, final String description, final String price) {
        setTitle(name);
        descriptionText.setText(description);
        priceText.setText(price);
        detailText.setText(description);
        nameText.setText(name);
    }
}
