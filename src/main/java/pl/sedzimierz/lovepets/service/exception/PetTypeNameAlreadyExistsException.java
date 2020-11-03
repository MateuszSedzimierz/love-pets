package pl.sedzimierz.lovepets.service.exception;

public class PetTypeNameAlreadyExistsException extends RuntimeException {

    public PetTypeNameAlreadyExistsException() {
        super("This pet type name already exists!");
    }
}
