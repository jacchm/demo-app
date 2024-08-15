package org.aszjch.demoapp.aplication;

public record IdDTO (Long id) {
  static IdDTO of(Long id) {
    return new IdDTO(id);
  }
}
