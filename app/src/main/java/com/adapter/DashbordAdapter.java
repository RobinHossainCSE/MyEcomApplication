package com.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.callback.OnDashboardItemSelectedListener;
import com.example.myecomapplication.databinding.DashboardItemRowBinding;
import com.models.DashboardItem;

import java.util.List;

public class DashbordAdapter extends RecyclerView.Adapter<DashbordAdapter.DashboardViewHolder> {
    private List<DashboardItem> items;
    private OnDashboardItemSelectedListener listener;

    public DashbordAdapter(Fragment fragment, List<DashboardItem> items){
        this.items = items;
        this.listener = (OnDashboardItemSelectedListener) fragment;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final DashboardItemRowBinding binding = DashboardItemRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new DashboardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DashboardViewHolder extends RecyclerView.ViewHolder {
        private DashboardItemRowBinding binding;
        public DashboardViewHolder(DashboardItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.dashItem.setOnClickListener(v -> {
                final int position = getAdapterPosition();
                listener.onDashboardItemSelect(items.get(position).getItemName());
            });
        }

        public void bind(DashboardItem item) {
            binding.setItem(item);
        }
    }
}