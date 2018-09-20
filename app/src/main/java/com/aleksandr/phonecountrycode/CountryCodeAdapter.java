package com.aleksandr.phonecountrycode;

import android.content.Context;
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
    private final CountryItemListener countryItemListener;
    Context context;

    public CountryCodeAdapter(ArrayList<CountryCode> countryCodes, CountryItemListener countryItemListener, Context context) {
        this.countryCodes = countryCodes;
        this.countryItemListener = countryItemListener;
        this.context = context;
    }

    public CountryCodeAdapter(CountryItemListener countryItemListener) {
        this.countryItemListener = countryItemListener;
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
                .inflate(R.layout.dialog_adapter_country_code, parent, false);
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

    public CountryCode getCountry(int position) {
        return countryCodes.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFlagD;
        TextView tvCountryD;
        TextView tvCodeD;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFlagD = itemView.findViewById(R.id.iv_dialog_country_flag_adapter);
            tvCountryD = itemView.findViewById(R.id.tv_dialog_country);
            tvCodeD = itemView.findViewById(R.id.tv_dialog_code_phone);

            itemView.setOnClickListener(v -> {
                if (countryItemListener != null)
                    countryItemListener.countrySelect(getCountry(getAdapterPosition()));
            });
        }

        private void bindTo(final CountryCode countryCode) {
            ivFlagD.setImageResource(countryCode.resId = context.getResources().
                    getIdentifier("ic_" + countryCode.getIso(), "drawable", context.getPackageName()));
            tvCountryD.setText(countryCode.getName());
            tvCodeD.setText("+ " + countryCode.getCode());
        }
    }

    public interface CountryItemListener {
        void countrySelect(CountryCode countryCode);
    }
}
