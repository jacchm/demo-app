package org.aszjch.demoapp.aplication;

import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ArticleDto {

    @Null(message = "id must not be provided.")
    private Long id;
    private String title;
    private String author;
    private String abstractText;
    private String filename;

}
