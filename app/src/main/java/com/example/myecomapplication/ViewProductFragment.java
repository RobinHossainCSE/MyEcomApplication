package com.example.myecomapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapter.ProductAdapter;
import com.callback.OnProductItemClickListener;
import com.example.myecomapplication.databinding.FragmentViewProductBinding;
import com.viewmodels.ProductViewModel;


public class ViewProductFragment extends Fragment implements OnProductItemClickListener {

    private FragmentViewProductBinding binding;
    private ProductViewModel productViewModel;

    public ViewProductFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViewProductBinding.inflate(inflater);
        productViewModel = new ViewModelProvider(requireActivity())
                .get(ProductViewModel.class);
        final ProductAdapter adapter = new ProductAdapter(this);
        binding.productRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.productRV.setAdapter(adapter);
        productViewModel.productListLiveData.observe(getViewLifecycleOwner(),
                productList -> {
                    adapter.submitList(productList);
                });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onProductItemClicked(String productId) {
        final Bundle bundle = new Bundle();
        bundle.putString("pid", productId);
        Navigation.findNavController(getActivity(), R.id.fragmentContainerView).navigate(R.id.action_viewProductFragment_to_productDetailsFragment, bundle);

    }
}