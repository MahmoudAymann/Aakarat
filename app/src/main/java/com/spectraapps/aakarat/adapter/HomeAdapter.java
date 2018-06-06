package com.spectraapps.aakarat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.model.HomeModel;
import com.spectraapps.aakarat.util.ListSharedPreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by MahmoudAyman on 04/06/2018.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context context;
    private ListAllListeners listAllListeners;
    private ArrayList<HomeModel> mDataList;

    private ListSharedPreference.Set setSharedPreference;
    private ListSharedPreference.Get getSharedPreference;

    public HomeAdapter(Context context, ArrayList<HomeModel> mDataList,
                       ListAllListeners listAllListeners) {
        this.mDataList = mDataList;
        this.listAllListeners = listAllListeners;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_item_home_view, parent, false);
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

//        Picasso.get().load(mDataList.get(holder.getAdapterPosition()).getImage())
//                .error(R.drawable.place_holder)
//                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAllListeners.onItemViewClick(mDataList.get(holder.getAdapterPosition()),
                        holder.getAdapterPosition());
            }
        });
    }//end onBindViewHolder()

    public interface ListAllListeners {
        void onItemViewClick(HomeModel homeModel, int adapterPosition);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SelectableRoundedImageView imageView;
        TextView nameTV;

        MyViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTxt);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }//end class MyViewHolder

}//end class