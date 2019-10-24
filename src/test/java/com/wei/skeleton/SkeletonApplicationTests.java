package com.wei.skeleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.wei.skeleton.entity.OrderItem;
import com.wei.skeleton.uitil.BigDecimalUtil;
import com.wei.skeleton.uitil.InputUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class SkeletonApplicationTests {

	@Test
	@DisplayName("read file tests")
	void readFileTests() throws IOException{

		List<String> inputs = List.of("./res/input1.txt","./res/input2.txt","./res/input3.txt");
		for (String input : inputs){
			List<String> inputs1 = InputUtil.readTxt(input);
			assertNotNull(inputs1);
		}

		String notExistFile = "./res/input99.txt";
		assertThrows(Exception.class, ()->InputUtil.readTxt(notExistFile));
	}

	@Test
	@DisplayName("data format tests")
	void dataFormatTests() throws Exception{

		List<String> normalItems = List.of("1 chocolate bar at 0.85","1 imported bottle of perfume at 47.50","1 box of imported chocolates at 11.25","1 bottle of perfume at 18.99");
		for (String item : normalItems){
			OrderItem orderItem = InputUtil.getOrderItemFromShoppingItem(item);
			assertNotNull(orderItem);
		}

		List<String> notSoNormalItems = List.of( "3 chocolate bar at 0.85" ,"1 imported bottle of perfume at 47999.50" ,"1       box of imported chocolates at 11.25" ,"1 bottle of      perfume at 18.99"
				                                   , "1 bottle of perfume at     18.99"  , "20    bottle  of    perfume  at     108.99" );
		for (String item : notSoNormalItems){
			OrderItem orderItem = InputUtil.getOrderItemFromShoppingItem(item);
			assertNotNull(orderItem);
		}

		List<String> wrongDataItems = List.of( "three chocolate bar at 0.85" ,"1 imported bottle of perfume at 47999,50" ,"1  bag of imported chocolates at 11.25" ,"1 bottle of women perfume at 18.99"
				, "1 bottle of perfume at $18.99"  , "20 bottle of perfume  with 108.99", "20 bottle of 香水  at 18.99");

		for (String item : wrongDataItems){
			assertThrows(Exception.class, ()->InputUtil.getOrderItemFromShoppingItem(item));
		}
	}


	@Test
	@DisplayName("read file tests")
	void roundUpBigDecimal() throws IOException{

		List<BigDecimal> bigDecimalList =   List.of(new BigDecimal("1.01"),new BigDecimal("0.41"),new BigDecimal("1.87"),new BigDecimal("1.80"),new BigDecimal("10000.81"));
		List<BigDecimal> expectedBigDecimalList =   List.of(new BigDecimal("1.05"),new BigDecimal("0.45"),new BigDecimal("1.90"),new BigDecimal("1.80"),new BigDecimal("10000.85"));

		for (int i =0;i<bigDecimalList.size();i++){
			assertEquals(expectedBigDecimalList.get(i), BigDecimalUtil.roundToNearst5Cent(bigDecimalList.get(i)));
		}
	}

}
