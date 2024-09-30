package org.aszjch.demoapp.aplication;

public record IdDTO(Long id) {

    static IdDTO of(final Long id) {
        return new IdDTO(id);
    }
}
