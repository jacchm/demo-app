package org.aszjch.demoapp.domain.articlefile;

import lombok.Data;

import java.io.File;

@Data
public class ArticleFile {

    private Long articleId;
    private String filename;
    private File file;
}
