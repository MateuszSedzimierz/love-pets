package pl.sedzimierz.lovepets.service.exception;

public class LoginAlreadyUsedException extends RuntimeException {

    public LoginAlreadyUsedException() {
        super("This login is already being used!");
    }
}
