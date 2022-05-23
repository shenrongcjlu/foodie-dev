package com.imooc.dto.response;

import lombok.Data;

@Data
public class SubCategoryDTO {

    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;
}
