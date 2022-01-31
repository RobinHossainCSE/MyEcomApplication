package com.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.callback.OnCategoryQueryCompleteListener;
import com.callback.OnProductQueryCompleteListener;
import com.callback.OnSingleProductQueryCompleteListener;
import com.models.ProductModel;
import com.models.PurchaseModel;
import com.repos.AdminRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private final AdminRepository repository = new AdminRepository();
    public MutableLiveData<List<String>> categoryListLiveData = new MutableLiveData<>();
    public MutableLiveData<List<ProductModel>> productListLiveData = new MutableLiveData<>();
    public MutableLiveData<ProductModel> productLiveData = new MutableLiveData<>();

    public ProductViewModel() {
        getCategories();
        getProducts();
    }
    public void addProduct(ProductModel productModel, PurchaseModel purchaseModel){
        repository.addNewProduct(productModel, purchaseModel);
    }

    private void getCategories(){
        repository.getAllCategories(items -> categoryListLiveData.postValue(items));

    }

    private void getProducts(){
        repository.getAllProducts(items -> productListLiveData.postValue(items));
    }

    public void getProductById(String productId){
        repository.getProductByProductId(productId, productModel -> {
            productLiveData.postValue(productModel);

        });
    }


}
