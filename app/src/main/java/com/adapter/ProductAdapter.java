package com.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.callback.OnProductItemClickListener;
import com.example.myecomapplication.databinding.ProductRowBinding;
import com.models.ProductModel;

public class ProductAdapter extends ListAdapter<ProductModel, ProductAdapter.ProductViewHolder> {
    private OnProductItemClickListener listener;

    public ProductAdapter(Fragment fragment) {

        super(new ProductDiff());
        listener = (OnProductItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ProductRowBinding binding = ProductRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ProductViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.itemView.setOnClickListener(view -> {
            listener.onProductItemClicked(getItem(position).getProductId());

        });

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private ProductRowBinding binding;
        public ProductViewHolder(ProductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(ProductModel productModel) {
            binding.setProduct(productModel);
        }
    }

    static class ProductDiff extends DiffUtil.ItemCallback<ProductModel>{
        @Override
        public boolean areItemsTheSame(@NonNull ProductModel oldItem, @NonNull ProductModel newItem) {
            return oldItem.getProductId().equals(newItem.getProductId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductModel oldItem, @NonNull ProductModel newItem) {
            return oldItem.getProductId().equals(newItem.getProductId());
        }
    }

}
