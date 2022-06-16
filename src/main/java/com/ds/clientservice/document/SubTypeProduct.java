package com.ds.clientservice.document;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
