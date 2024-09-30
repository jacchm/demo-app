package org.aszjch.demoapp.aplication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ArticleDto {

    @JsonIgnore
    private Long id;
    private String title;
    private String author;
    private String abstractText;

}
