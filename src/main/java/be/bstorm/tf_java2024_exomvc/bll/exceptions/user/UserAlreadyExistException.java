package be.bstorm.tf_java2024_exomvc.bll.exceptions.user;

import be.bstorm.tf_java2024_exomvc.bll.exceptions.ExoMVCException;

public class UserAlreadyExistException extends ExoMVCException {

    public UserAlreadyExistException() {
        super("User already exists");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
