package pl.sedzimierz.lovepets.controller.editor;

import pl.sedzimierz.lovepets.service.PetTypeService;
import pl.sedzimierz.lovepets.service.dto.PetTypeDTO;

import java.beans.PropertyEditorSupport;

public final class PetTypeEditor extends PropertyEditorSupport {

    private final PetTypeService petTypeService;

    public PetTypeEditor(PetTypeService petTypeService){
        this.petTypeService = petTypeService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        petTypeService
                .getPetTypeById(Long.parseLong(text))
                .ifPresent(this::setValue);
    }

    @Override
    public String getAsText() {
        PetTypeDTO petTypeDTO = (PetTypeDTO) getValue();
        return petTypeDTO == null ? "" : petTypeDTO.getId().toString();
    }
}
