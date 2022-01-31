package com.example.myecomapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myecomapplication.databinding.FragmentProductDetailsBinding;
import com.viewmodels.ProductViewModel;


public class ProductDetailsFragment extends Fragment {
    private FragmentProductDetailsBinding binding;
    private ProductViewModel productViewModel;
    private String productId;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        productId = getArguments().getString("pid");
        binding = FragmentProductDetailsBinding.inflate(inflater);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getProductById(productId);
        productViewModel.productLiveData.observe(getViewLifecycleOwner(),productModel -> {
            binding.setProduct(productModel);
        });
        return binding.getRoot();
    }
}