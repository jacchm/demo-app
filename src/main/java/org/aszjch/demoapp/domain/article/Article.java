package org.aszjch.demoapp.domain.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.OffsetDateTime;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private Long id;
    private String title;
    private String author;
    private OffsetDateTime creationDate;
    private String abstractText;
    private String filename;

}
