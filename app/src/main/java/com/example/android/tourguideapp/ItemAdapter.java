package com.example.android.tourguideapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private static final String TAG = "ItemAdapter";
    private int mColorResourceId;
    public static ArrayList<Item> items = new ArrayList<>();
    private Context context;
    ArrayList<String> itemURLs=new ArrayList<String>();
    ArrayList<String> itemNames=new ArrayList<String>();
    ImageView imageView;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    public static ProgressDialog progressDialog;

    public ItemAdapter(Context context, ArrayList<Item> items, int colorResourceId) {
        this.context= context;
        this.items= items;
        this.mColorResourceId = colorResourceId;
    }
    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context= context;
        this.items= items;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ItemHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {

        if (items == null || items.size() ==0){
            holder.image.setImageResource(R.drawable.placeholder);
            holder.tvName.setText("there is No topic avalible is this list of toure guaide");
        }else{
            final Item photoItem = items.get(position);
            Log.d(TAG, "onBindViewHolder: " + photoItem.getName() + " --> " + position);

            holder.tvName.setText(photoItem.getName());
            int color = ContextCompat.getColor(context, mColorResourceId);
            holder.tvName.setBackgroundColor(color);

            Picasso.get().load(photoItem.getImageResourceId())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.image, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.image.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e(TAG, "error " + e.getMessage());
                        }

                    });

            
            holder.itemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "hallo toast", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return ((items != null) && (items.size() != 0)) ? items.size() : 1;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvName;
        View itemCard;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName =  itemView.findViewById(R.id.name_text_view);
            image =  itemView.findViewById(R.id.image);
            itemCard= itemView;
        }
    }
}