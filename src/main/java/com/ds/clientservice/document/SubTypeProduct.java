package com.ds.clientservice.document;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "SUB_TYPE_PRODUCT")
public class SubTypeProduct {
    private String id;
    private String code;
    private String name;
    private Date date;
    private Number limitMount;
    private Number limitDay;
    private Number limitCredit;
    private Boolean commission;
    @NotNull
    private TypeProduct typeProduct;
}
