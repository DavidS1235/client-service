package com.ds.clientservice.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "TYPE_PRODUCT")
public class TypeProduct {

    private String id;
    private String code;
    private String name;

}
