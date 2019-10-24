package com.wei.skeleton;


import java.util.List;
import com.wei.skeleton.entity.OrderItem;
import com.wei.skeleton.entity.ShoppingCart;
import com.wei.skeleton.uitil.InputUtil;
import java.util.ArrayList;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkeletonApplication{


  public static void main(String[] args) throws Exception{

    List<String> inputs = List.of("./res/input1.txt","./res/input2.txt","./res/input3.txt");
    for (int j = 0; j < inputs.size(); j++){
      List<String> input = InputUtil.readTxt(inputs.get(j));
      List<OrderItem> orderItemList = new ArrayList<>();//put items from the file into a List
      for (String anInput : input){
        if(!anInput.isBlank()){// skip the empty lines
          OrderItem orderItem = InputUtil.getOrderItemFromShoppingItem(anInput);
          orderItemList.add(orderItem);
        }
      }
      ShoppingCart shoppingCart = ShoppingCart.builder().orderItemList(orderItemList).shoppingCartId(j + 1).build();//put the list into a ShoppingCart
      shoppingCart.printReceipt();
    }
  }
}
