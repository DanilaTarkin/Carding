package ru.bezzdars.cardingrest.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bezzdars.cardingrest.enums.Levels;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestMessage {

  private Levels level;
  private String service;
  private String content;
  private LocalDateTime time;
}
