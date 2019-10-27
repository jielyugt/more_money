package uk.ivanc.archi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import uk.ivanc.archi.model.Item;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<Item> inventory;
    private Callback callback;

    public RepositoryAdapter() {
        this.inventory = Collections.emptyList();
    }

    public void setRepositories(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        final RepositoryViewHolder viewHolder = new RepositoryViewHolder(itemView);
        viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(viewHolder.item);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Item item = inventory.get(position);
        Context context = holder.titleTextView.getContext();
        holder.item = item;
        holder.titleTextView.setText(item.getItemName());
        holder.descriptionTextView.setText(item.getDescription());
        holder.price.setText("$ " + item.getPrice());
    }

    @Override
    public int getItemCount() {
        return inventory.size();
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        public View contentLayout;
        public TextView titleTextView;
        public TextView descriptionTextView;
//        public TextView watchersTextView;
//        public TextView starsTextView;
        public TextView price;
        public Item item;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.layout_content);
            titleTextView = (TextView) itemView.findViewById(R.id.item_name);
            descriptionTextView = (TextView) itemView.findViewById(R.id.item_description);
            price = (TextView) itemView.findViewById(R.id.text_forks);
        }
    }

    public interface Callback {
        void onItemClick(Item item);
    }
}
