package com.ds.clientservice.document;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String id;
    @NotNull
    private String idBank;
    @NotEmpty
    private String tpeCrrency;
    private Number numRemainder;
    private Date date;
    private SubTypeProduct subTypeProduct;
    private Client client;

}
