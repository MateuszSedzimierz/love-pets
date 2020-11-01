package pl.sedzimierz.lovepets.service.exception;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException() {
        super("This email address is already being used!");
    }
}
