package com.ds.clientservice.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "PERSON")
public class Person {

    @Id
    private String id;
    private String idTypeDocument;
    private String document;
    private String name;
    private String paternal;
    private String maternal;
    private Integer idTpePerson; // 1 = personal 2= empresarial

}