package com.wei.skeleton.entity;


import static com.wei.skeleton.constant.Constants.BASIC_TAX;
import static com.wei.skeleton.constant.Constants.IMPORTED_TAX;

import com.wei.skeleton.enums.ProductUnit;
import com.wei.skeleton.uitil.BigDecimalUtil;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product{

  @NonNull
  private String productName;

  @NonNull
  private BigDecimal price;

  private ProductUnit unit;

  private Boolean imported;

  @NonNull
  private CategoryItem categoryItem;

  BigDecimal getTax(){

    BigDecimal tax = BigDecimal.ZERO;
    if(!categoryItem.getExempted()){
      tax = tax.add(price.multiply(BASIC_TAX));
    }
    if(imported){
      tax = tax.add(price.multiply(IMPORTED_TAX));
    }
    return BigDecimalUtil.roundToNearst5Cent(tax);
  }
}
