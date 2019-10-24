package com.wei.skeleton.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart{

  @NonNull
  private Integer shoppingCartId;

  @NonNull
  private List<OrderItem> orderItemList;

  private BigDecimal getTotalPrice(){
    List<BigDecimal> bigDecimalList = orderItemList.stream()
                                                   .map(item-> item.getProduct().getPrice().multiply(new BigDecimal(item.getNumber())))
                                                   .collect(Collectors.toList());
    return bigDecimalList.stream()
                         .reduce(BigDecimal.ZERO,BigDecimal::add );
  }

  private BigDecimal getTotalTax(){

    List<BigDecimal> bigDecimalList = orderItemList.stream()
                                                   .map(orderItem -> orderItem.getProduct()
                                                                              .getTax()
                                                                              .multiply(new BigDecimal(orderItem.getNumber())))
                                                   .collect(Collectors.toList());
    return bigDecimalList.stream()
                         .reduce(BigDecimal.ZERO,BigDecimal::add );
  }


  public void printReceipt(){
    System.out.println("Output "+shoppingCartId+":");
    System.out.println();
    orderItemList.forEach(item->{
      StringBuilder sb = new StringBuilder();
      sb.append(item.getNumber())
        .append(" ")
        .append(item.getProduct().getImported() ?"imported ":"")
        .append(item.getProduct().getUnit()!=null?item.getProduct().getUnit()+" of ":"")
        .append(item.getProduct().getProductName())
        .append(": ")
        .append( (item.getProduct().getPrice().add(item.getProduct().getTax())).multiply(new BigDecimal(item.getNumber())));
      System.out.println(sb.toString());
      System.out.println();
    });
    System.out.println("Sales Taxes: "+this.getTotalTax());
    System.out.println();
    System.out.println("Total: "+ this.getTotalPrice().add(this.getTotalTax()));
    System.out.println();
  }
}
