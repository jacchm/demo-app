package org.aszjch.demoapp.aplication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
class ArticleFileDto {

    private Long articleId;
    private String filename;
    private MultipartFile file;
}
