package be.bstorm.tf_java2024_exomvc.bll.exceptions.product;

import be.bstorm.tf_java2024_exomvc.bll.exceptions.ExoMVCException;

public class ProductAlreadyExistException extends ExoMVCException {

    public ProductAlreadyExistException() {
        super("Product Already Exists");
    }

    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
