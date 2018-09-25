package com.aleksandr.phonecountrycode;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandr.phonecountrycode.model.CountryCode;

import java.util.ArrayList;

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.ViewHolder> {

    private ArrayList<CountryCode> countryCodes;
    private final CountryItemListener countryItemListener;

    public CountryCodeAdapter(ArrayList<CountryCode> countryCodes, CountryItemListener countryItemListener) {
        this.countryCodes = countryCodes;
        this.countryItemListener = countryItemListener;
    }

    public CountryCodeAdapter(CountryItemListener countryItemListener) {
        this.countryItemListener = countryItemListener;
    }

    public void dataUpdate(ArrayList<CountryCode> countryCodes) {
        this.countryCodes.clear();
        if (countryCodes != null) {
            this.countryCodes.addAll(countryCodes);
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
        public CountryCodeView countryCodeView;

        public ViewHolder(View itemView) {
            super(itemView);
        countryCodeView = itemView.findViewById(R.id.country_code);

            itemView.setOnClickListener(v -> {
                if (countryItemListener != null)
                    countryItemListener.countrySelect(getCountry(getAdapterPosition()));
            });
        }

        private void bindTo(final CountryCode countryCode) {
            countryCodeView.setView(countryCode);
        }
    }

    public interface CountryItemListener {
        void countrySelect(CountryCode countryCode);
    }
}
