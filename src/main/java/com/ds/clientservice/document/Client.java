package com.ds.clientservice.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "CLIENT")
public class Client {

  @Id
  private String id;
  private String code;
  private String idPerson;
  private String name;
  private Person person;
  private TypeClient typeClient;
  //Personal
  private Boolean ctaAhorro;
  private Boolean plzFijo;
  private Boolean credPersonal;
  //Personal - Empresarial
  private Integer ctaCorriente; // P: 1 E: n
  private Integer credEmpresarial; // E: n
  private Boolean tcPersonal;
  private Boolean tcEmpresarial;

}