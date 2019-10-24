package com.wei.skeleton.constant;


import com.wei.skeleton.entity.CategoryItem;
import com.wei.skeleton.entity.CategoryNode;

public class CategoryTree{

  public static CategoryNode<CategoryItem> getCategoryTree() {
    CategoryNode<CategoryItem> root = new CategoryNode<CategoryItem>(new CategoryItem("root",0,false));
    {
      CategoryNode<CategoryItem> book = root.addChild(new CategoryItem("book", 1, true));
      CategoryNode<CategoryItem> food = root.addChild(new CategoryItem("food", 1, true));
      {
        CategoryNode<CategoryItem> food0 = food.addChild(new CategoryItem("chocolate bar", 2, true));
        CategoryNode<CategoryItem> food1 = food.addChild(new CategoryItem("chocolates", 2, true));
      }
      CategoryNode<CategoryItem> medical = root.addChild(new CategoryItem("medical", 1, true));
      {

        CategoryNode<CategoryItem> medical0 = medical.addChild(new CategoryItem("headache pills", 2, true));

      }
      CategoryNode<CategoryItem> media = root.addChild(new CategoryItem("media", 1, false));
      {
        CategoryNode<CategoryItem> media0 = media.addChild(new CategoryItem("music CD", 2, false));
      }
      CategoryNode<CategoryItem> cosmetic = root.addChild(new CategoryItem("cosmetic", 1, false));
      {
        CategoryNode<CategoryItem> cosmetic0 = cosmetic.addChild(new CategoryItem("perfume", 2, false));
      }
    }

    return root;
  }
}
