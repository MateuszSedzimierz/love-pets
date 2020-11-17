package pl.sedzimierz.lovepets.service.exception;

public class PetDeleteNotAllowedException extends RuntimeException {

    public PetDeleteNotAllowedException() {
        super("You do not have permission to delete this pet!");
    }
}
