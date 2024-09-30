package org.aszjch.demoapp.domain.article;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ArticleCreatedDto implements Serializable {

    private Long id;
    private String title;
    private String author;
    private String abstractText;

    @Override
    public String toString() {
        return "Article created{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", abstractText='" + abstractText + '\'' +
                '}';
    }
}
