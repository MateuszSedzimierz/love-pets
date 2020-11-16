package pl.sedzimierz.lovepets.controller.editor;

import pl.sedzimierz.lovepets.service.UserService;

import java.beans.PropertyEditorSupport;

public final class UserEditor extends PropertyEditorSupport {

    private final UserService userService;

    public UserEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        userService
                .getUserById(Long.parseLong(text))
                .ifPresent(this::setValue);
    }
}
