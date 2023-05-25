package medication.takemedichine.medicationalert.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.ProductDetails;

import java.util.List;


import medication.takemedichine.medicationalert.Interface.ItemClickListener;
import medication.takemedichine.medicationalert.R;

public class RemoveAdsAdapter extends RecyclerView.Adapter<RemoveAdsAdapter.RemoveADS> {
    ItemClickListener itemClickListener;

    Context context;
    List<ProductDetails> productDetailsList;
    Activity activity;

    public RemoveAdsAdapter(Context context, List<ProductDetails> productDetailsList, Activity activity, ItemClickListener itemClickListener) {
        this.context = context;
        this.productDetailsList = productDetailsList;
        this.activity = activity;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public RemoveADS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.billing_view_items, null, false);
        return new RemoveADS(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RemoveADS holder, @SuppressLint("RecyclerView") int position) {

        if (position == 0) {
            holder.price.setText("$0.99");
            holder.title.setText("1 Month");
        }
        if (position == 1) {
            holder.price.setText("$2.49");
            holder.title.setText("3 Month");
        }
        if (position == 2) {
            holder.price.setText("$0.45");
            holder.title.setText("15 Days");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.clickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDetailsList.size();
    }


    public class RemoveADS extends RecyclerView.ViewHolder {
        TextView price, title;


        public RemoveADS(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.tvPrice);
            title = itemView.findViewById(R.id.tvMonth);
        }
    }
}