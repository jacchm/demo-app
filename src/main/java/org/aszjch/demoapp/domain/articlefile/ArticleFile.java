package org.aszjch.demoapp.domain.articlefile;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleFile extends ArticleFileMetadata {

    private File file;
}
