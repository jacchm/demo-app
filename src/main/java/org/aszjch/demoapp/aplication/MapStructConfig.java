package org.aszjch.demoapp.aplication;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = MapperHelper.class
)
public interface MapStructConfig {

}
