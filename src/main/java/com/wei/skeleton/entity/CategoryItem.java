package com.wei.skeleton.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryItem{

  @NonNull
  public String name;

  @NonNull
  public Integer level;

  @NonNull
  public Boolean exempted;

}
