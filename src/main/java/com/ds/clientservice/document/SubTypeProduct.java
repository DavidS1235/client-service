package com.ds.clientservice.document;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubTypeProduct {
    private String id;
    private String code;
    private String name;

    private Date date;

    private TypeProduct typeProduct;
}
