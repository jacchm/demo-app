package org.aszjch.demoapp.aplication;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Named("CurrentTimeProvider")
@Component
class CurrentTimeProvider {

    private final Clock clock;

    @Named("getCurrentTime")
    OffsetDateTime getCurrentTime(final Object ignore) {
        return OffsetDateTime.now(clock);
    }

}
