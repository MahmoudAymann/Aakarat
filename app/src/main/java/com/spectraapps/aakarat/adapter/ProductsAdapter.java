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
import com.spectraapps.aakarat.model.ProductsModel;
import com.spectraapps.aakarat.util.ListSharedPreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MahmoudAyman on 04/06/2018.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    private Context context;
    private ListAllListeners listAllListeners;
    private ArrayList<ProductsModel> mDataList;

    private ListSharedPreference.Set setSharedPreference;
    private ListSharedPreference.Get getSharedPreference;

    public ProductsAdapter(Context context, ArrayList<ProductsModel> mDataList,
                           ListAllListeners listAllListeners) {
        this.mDataList = mDataList;
        this.listAllListeners = listAllListeners;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_item_products_view, parent, false);
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

        holder.typeTV.setText(mDataList.get(holder.getAdapterPosition()).getType());

        if (holder.typeTV.getText().equals("For Rent") || holder.typeTV.getText().equals("للإيجار")) {
            if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_LTR)
                holder.typeTV.setBackgroundResource(R.drawable.bg_type_rent_tv);
            else
                holder.typeTV.setBackgroundResource(R.drawable.bg_type_rent_tv_rtl);
        } else { // sale
            if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_LTR)
                holder.typeTV.setBackgroundResource(R.drawable.bg_type_sale_tv);
            else
                holder.typeTV.setBackgroundResource(R.drawable.bg_type_sale_tv_rtl);
        }


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
        void onItemViewClick(ProductsModel productsModel, int adapterPosition);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        SelectableRoundedImageView imageView;
        @BindView(R.id.priceTV)
        TextView priceTV;
        @BindView(R.id.countryTV)
        TextView countryTV;
        @BindView(R.id.cityTV)
        TextView cityTV;
        @BindView(R.id.currencyTV)
        TextView currencyTV;
        @BindView(R.id.unitTV)
        TextView unitTV;
        @BindView(R.id.measureTV)
        TextView measureTV;
        @BindView(R.id.typeTV)
        TextView typeTV;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }//end class MyViewHolder

}//end class