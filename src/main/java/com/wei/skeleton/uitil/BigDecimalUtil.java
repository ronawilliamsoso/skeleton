package com.wei.skeleton.uitil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil{

  public static BigDecimal roundToNearst5Cent(BigDecimal value) {

      BigDecimal increment = new BigDecimal(0.05);
      BigDecimal divided = value.divide(increment,0,RoundingMode.UP);
      BigDecimal result = divided.multiply(increment);
      return result.setScale(2,RoundingMode.FLOOR);

  }
}


