package com.ds.clientservice.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "PRODUCT")
public class Product {

    private String id;
    @NotNull
    private String idBank;
    @NotEmpty
    private String tpeCrrency;
    private Number numRemainder;
    private Date date;
    //@NotNull
    private SubTypeProduct subTypeProduct;
    //@NotNull
    private Client client;

}
