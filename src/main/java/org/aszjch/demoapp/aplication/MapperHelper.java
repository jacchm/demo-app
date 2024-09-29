package org.aszjch.demoapp.aplication;

import lombok.RequiredArgsConstructor;
import org.aszjch.demoapp.infrastructure.storage.TempFilesService;
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
    private final TempFilesService tempFilesService;

    @Named("getCurrentTime")
    OffsetDateTime getCurrentTime(Object ignore) {
        return OffsetDateTime.now(clock);
    }

    @Named("multipartFileToFile")
    File multipartFileToFile(MultipartFile multipartFile) throws IOException {
        return tempFilesService.convertMultipartFile(multipartFile);
    }

}
