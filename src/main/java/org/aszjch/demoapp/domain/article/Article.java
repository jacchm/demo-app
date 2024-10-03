package org.aszjch.demoapp.domain.article;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Article {

    private Long id;
    private String title;
    private String author;
    private OffsetDateTime creationDate;
    private String abstractText;
    private String filename;

}
