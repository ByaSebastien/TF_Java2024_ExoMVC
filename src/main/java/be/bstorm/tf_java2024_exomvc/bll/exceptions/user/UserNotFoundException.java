package be.bstorm.tf_java2024_exomvc.bll.exceptions.user;

import be.bstorm.tf_java2024_exomvc.bll.exceptions.ExoMVCException;

public class UserNotFoundException extends ExoMVCException {

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
