package com.aleksandr.phonecountrycode;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandr.phonecountrycode.model.CountryCode;

import java.util.ArrayList;

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.ViewHolder> {

    private ArrayList<CountryCode> countryCodes;

    public CountryCodeAdapter(ArrayList<CountryCode> countryCodes) {
        this.countryCodes = countryCodes;
        notifyDataSetChanged();
    }

    public void dataUpdate(ArrayList<CountryCode> countryCodes) {
        if (countryCodes != null) {
            this.countryCodes = countryCodes;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_country_code, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.bindTo(countryCodes.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return countryCodes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFlag;
        TextView tvCode;

        public ViewHolder(View itemView) {
            super(itemView);

            ivFlag = itemView.findViewById(R.id.iv_country_flag_adapter);
            tvCode = itemView.findViewById(R.id.tv_code_phone);
        }

        private void bindTo(final CountryCode countryCode){
        tvCode.setText(countryCode.getName() + " " +
                        countryCode.getCode() + " " +
                        countryCode.getDigits() + " " +
                        countryCode.getIso());
        }
    }
}
