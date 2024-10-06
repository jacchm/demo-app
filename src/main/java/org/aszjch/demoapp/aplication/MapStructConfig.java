package org.aszjch.demoapp.aplication;

import org.aszjch.demoapp.infrastructure.storage.MultipartFileToFileConverter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {MultipartFileToFileConverter.class, CurrentTimeProvider.class}
)
public interface MapStructConfig {

}
