package org.aszjch.demoapp.aplication;

import lombok.RequiredArgsConstructor;
import org.aszjch.demoapp.infrastructure.storage.MultipartFileToFileConverter;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Named("MapperHelper")
@Component
class MapperHelper {

    private final Clock clock;
    private final MultipartFileToFileConverter multipartFileToFileConverter;

    @Named("getCurrentTime")
    OffsetDateTime getCurrentTime(final Object ignore) {
        return OffsetDateTime.now(clock);
    }

    @Named("multipartFileToFile")
    File multipartFileToFile(final MultipartFile multipartFile) throws IOException {
        return multipartFileToFileConverter.convertMultipartFile(multipartFile);
    }

}
