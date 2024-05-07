package be.bstorm.tf_java2024_exomvc.bll.exceptions.product;

import be.bstorm.tf_java2024_exomvc.bll.exceptions.ExoMVCException;

public class ProductNotFoundException extends ExoMVCException {

    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
