package com.wei.skeleton.enums;


public enum ProductUnit {
  bottle, packet, box;
  public static boolean contains(String type){
    for(ProductUnit typeEnum : ProductUnit.values()){
      if(typeEnum.name().equals(type)){
        return true;
      }
    }
    return false;
  }
  }


