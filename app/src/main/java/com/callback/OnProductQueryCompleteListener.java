package com.callback;

import com.models.ProductModel;

import java.util.List;

public interface OnProductQueryCompleteListener {
    void onProductQueryComplete(List<ProductModel> items);
}
