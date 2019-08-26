package com.yz.git.sc.product.service;

import com.yz.git.sc.product.api.param.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScProductServiceApplicationTests {
	@Autowired
	ProductManagerService productManagerService;




	@Test
	public void saveProductTest() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setCreateDate(new Date());
		productInfo.setIsDelete("1");
		productInfo.setIsHotSale("1");
		productInfo.setProductCode("00001");
		productInfo.setProductCreatDate(new Date());
		productInfo.setProductFactoryCode("H0");
		productInfo.setProductLabel("食用");
		productInfo.setProductFactoryStatus("营业");
		productInfo.setProductGrade("食用");
		System.out.println(productManagerService.saveProductInfo(productInfo)
		);
	}
	@Test
	public void updateProductTest() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setCreateDate(new Date());
		productInfo.setIsDelete("1");
		productInfo.setIsHotSale("1");
		productInfo.setProductCode("00001");
		productInfo.setProductCreatDate(new Date());
		productInfo.setProductFactoryCode("H0");
		productInfo.setProductLabel("辣条系列");
		productInfo.setProductFactoryStatus("营业");
		productInfo.setProductGrade("食用");
		System.out.println(productManagerService.updateHotSaleSort(productInfo)
		);
	}
	@Test
	public void contextLoads() {
	}

}
