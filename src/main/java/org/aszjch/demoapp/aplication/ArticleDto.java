package org.aszjch.demoapp.aplication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
class ArticleDto {

    @JsonIgnore
    private Long id;
    private String title;
    private String author;
    private String abstractText;

}
