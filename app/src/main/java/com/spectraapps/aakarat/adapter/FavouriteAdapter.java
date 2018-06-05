package com.spectraapps.aakarat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.model.FavModel;
import com.spectraapps.aakarat.model.ProductsModel;
import com.spectraapps.aakarat.util.ListSharedPreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MahmoudAyman on 05/06/2018.
 */
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {
    private Context context;
    private ListAllListeners listAllListeners;
    private ArrayList<FavModel> mDataList;

    private ListSharedPreference.Set setSharedPreference;
    private ListSharedPreference.Get getSharedPreference;

    public FavouriteAdapter(Context context, ArrayList<FavModel> mDataList,
                           ListAllListeners listAllListeners) {
        this.mDataList = mDataList;
        this.listAllListeners = listAllListeners;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_item_favourites_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mDataList != null)
            return mDataList.size();
        else
            return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        setSharedPreference = new ListSharedPreference.Set(context);
        getSharedPreference = new ListSharedPreference.Get(context);

        holder.nameTV.setText(mDataList.get(holder.getAdapterPosition()).getName());

        Picasso.get().load(mDataList.get(holder.getAdapterPosition()).getImage())
                .error(R.drawable.place_holder)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAllListeners.onItemViewClick(mDataList.get(holder.getAdapterPosition()),
                        holder.getAdapterPosition());
            }
        });
    }//end onBindViewHolder()

    public interface ListAllListeners {
        void onItemViewClick(FavModel favModel, int adapterPosition);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        SelectableRoundedImageView imageView;
        @BindView(R.id.nameTV)
        TextView nameTV;


        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }//end class MyViewHolder

}//end class
