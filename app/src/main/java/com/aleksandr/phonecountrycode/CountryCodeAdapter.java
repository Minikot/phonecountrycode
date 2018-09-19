package com.aleksandr.phonecountrycode;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandr.phonecountrycode.model.CountryCode;
import com.aleksandr.phonecountrycode.model.CountryCodeBL;

import java.util.ArrayList;

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.ViewHolder> {

    private ArrayList<CountryCode> countryCodes;
    FragmentManager fragmentManager;
    Context context;

    public CountryCodeAdapter(ArrayList<CountryCode> countryCodes, FragmentManager fragmentManager, Context context) {
        this.countryCodes = countryCodes;
        this.fragmentManager = fragmentManager;
        this.context = context;
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
        TextView tvCountry;
        TextView tvCode;
        View container;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFlag = itemView.findViewById(R.id.iv_country_flag_adapter);
            tvCountry = itemView.findViewById(R.id.tv_country);
            tvCode = itemView.findViewById(R.id.tv_code_phone);
            container = itemView;
        }

        private void bindTo(final CountryCode countryCode) {
            final int resId = countryCode.resId = context.getResources().getIdentifier("ic_" + countryCode.getIso(), "drawable", context.getPackageName());
            ivFlag.setImageResource(resId);

            tvCountry.setText(countryCode.getName());

            tvCode.setText(countryCode.getCode());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CountryCodeBL.getInstance().setCode(tvCode.getText().toString());
                    CountryCodeBL.getInstance().setResId(resId);

                    CountryCodeBL.getInstance().backTo(fragmentManager);
                    CountryCodeBL.getInstance().getCodesArrayFiltered().
                            removeAll(CountryCodeBL.getInstance().getCodesArrayFiltered());
                }
            });
        }

    }
}
