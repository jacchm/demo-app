package org.aszjch.demoapp.domain.article;

import lombok.Data;

import java.time.OffsetDateTime;


// TODO: important - DOMAIN CLASS STATE CAN BE CHANGED ONLY FROM WITHIN THE CLASS, NOT FROM OUTSIDE
@Data
public class Article {

    private Long id;
    private String title;
    private String author;
    private OffsetDateTime creationDate;
    private String abstractText;
    private String filename;

}
