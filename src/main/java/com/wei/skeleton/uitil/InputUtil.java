package com.wei.skeleton.uitil;


import com.wei.skeleton.entity.CategoryItem;
import com.wei.skeleton.entity.CategoryNode;
import com.wei.skeleton.entity.OrderItem;
import com.wei.skeleton.entity.Product;
import com.wei.skeleton.enums.ProductUnit;
import com.wei.skeleton.constant.CategoryTree;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class InputUtil{

  private static boolean isNumeric(String strNum) {
    return strNum.matches("\\d+(\\.\\d+)?");
  }

  private static boolean isInteger(String strNum) {
    return strNum.matches("\\d+");
  }


  public static List<String> readTxt(String filePath) throws IOException{
    Path path = Paths.get(filePath);
    List<String> output = Files.readAllLines(path,StandardCharsets.UTF_8);
    return output;
  }

  private static Optional<CategoryItem> getCategoryByName(String productName){

    Comparable<CategoryItem> searchCriteria = categoryItem -> {
      boolean nodeOk = categoryItem.getName().equalsIgnoreCase(productName);
      return nodeOk ? 0 : 1;
    };
    CategoryNode<CategoryItem> treeRoot = CategoryTree.getCategoryTree();
    CategoryNode<CategoryItem> found = treeRoot.findTreeNode(searchCriteria);
    return Optional.of(found.categoryItem);

  }


  public static OrderItem getOrderItemFromShoppingItem(@NonNull String item) throws Exception{

    String name="";
    Boolean imported = false;
    String number;
    String price;
    String unit="";


    if(item.indexOf("imported") > 0){
      imported = true;
      item = item.replace("imported","");
    }
    String[] split1 = item.split("\\Wat\\W");
    price = split1[1];
    String[] split2 = split1[0].split("\\Wof\\W");
    if(split2.length == 2){
      name = split2[1].trim();
      String[] split3 = split2[0].split("\\W+");
      number = split3[0];
      unit = split3[1];
    }
    else if(split2.length == 1){
      String[] split3 = split2[0].split("\\W*",2);
      number = split3[0];
      name = split3[1].trim();

    }
    else{
      throw new Exception("data in wrong format");
    }

    if(name.isBlank()){
      throw new Exception("product name is empty");
    }
    if(!isInteger(number.trim())){
      throw new Exception("product number is not a Integer: " + number);
    }
    if(!isNumeric(price.trim())){
      throw new Exception("product price name is not numeric: " + price);
    }
    if(!unit.isBlank()){
      if(!ProductUnit.contains(unit.trim())){
        throw new Exception("unit data is not defined in system: "+ unit);
      }
    }
    CategoryItem categoryItem = getCategoryByName(name).orElseThrow(() -> new Exception("Cannot find this product in the category tree"));

    Product product = Product.builder()
                             .productName(name)
                             .price(new BigDecimal(price.trim()))
                             .unit(unit.isEmpty() ? null : ProductUnit.valueOf(unit.trim()))
                             .categoryItem(categoryItem)
                             .imported(imported)
                             .build();

    return OrderItem.builder().number(Integer.parseInt(number)).product(product).build();
  }
}




