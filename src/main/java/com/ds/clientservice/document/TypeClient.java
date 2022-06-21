package com.ds.clientservice.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "TYPE_CLIENT")
public class TypeClient {

  @Id
  private String id;
  private String code;
  private String name;

}
