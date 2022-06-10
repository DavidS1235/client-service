package com.ds.clientservice.document;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String id;
    private String idBank;
    private String tpeCrrency;
    private Number numRemainder;
    private Date date;
    private SubTypeProduct subTypeProduct;
    private String idClient;

}
