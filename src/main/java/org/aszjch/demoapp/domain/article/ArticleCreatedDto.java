package org.aszjch.demoapp.domain.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleCreatedDto implements Serializable {

    private Long id;
    private String title;
    private String author;
    private String abstractText;

}
