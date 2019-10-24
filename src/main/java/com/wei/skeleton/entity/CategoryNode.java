package com.wei.skeleton.entity;


import java.util.LinkedList;
import java.util.List;


public class CategoryNode<CategoryItem>  {

  public CategoryItem categoryItem;
  public CategoryNode<CategoryItem> parent;
  public List<CategoryNode<CategoryItem>> children;

  public boolean isRoot() {
    return parent == null;
  }

  public boolean isLeaf() {
    return children.size() == 0;
  }

  public List<CategoryNode<CategoryItem>> elementsIndex;

  public CategoryNode(CategoryItem data) {
    this.categoryItem = data;
    this.children = new LinkedList<CategoryNode<CategoryItem>>();
    this.elementsIndex = new LinkedList<>();
    this.elementsIndex.add(this);
  }

  public CategoryNode<CategoryItem> addChild(CategoryItem child) {
    CategoryNode<CategoryItem> childNode = new CategoryNode<CategoryItem>(child);
    childNode.parent = this;
    this.children.add(childNode);
    this.registerChildForSearch(childNode);
    return childNode;
  }

  public int getLevel() {
    if (this.isRoot())
      return 0;
    else
      return parent.getLevel() + 1;
  }

  private void registerChildForSearch(CategoryNode<CategoryItem> node) {
    elementsIndex.add(node);
    if (parent != null)
      parent.registerChildForSearch(node);
  }

  public CategoryNode<CategoryItem> findTreeNode(Comparable<CategoryItem> cmp) {
    for (CategoryNode<CategoryItem> element : this.elementsIndex) {
      CategoryItem elData = element.categoryItem;
      if (cmp.compareTo(elData) == 0)
        return element;
    }

    return null;
  }

  @Override
  public String toString() {
    return categoryItem != null ? categoryItem.toString() : "[data null]";
  }

}
