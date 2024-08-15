package org.aszjch.demoapp.aplication;

import jakarta.validation.constraints.Null;

class ArticleDto {

  @Null(message = "id must not be provided.")
  private Long id;


}
