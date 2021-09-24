package com.yimi.oysc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yimi.oysc.entity.CarInquiryEntity;
import com.yimi.oysc.entity.CarInquirySubItemEntity;
import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.enumerate.StatusEnum;
import com.yimi.oysc.mapper.CarInquiryMapper;
import com.yimi.oysc.service.ICarInquiryService;
import com.yimi.oysc.service.ICarInquirySubItemService;
import com.yimi.oysc.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class OyscApplicationTests {


	@Autowired
	private ICarInquiryService carInquiryService;

	@Autowired
	private ICarInquirySubItemService carInquirySubItemService;

	@Autowired
	private IRoleService roleService;


	@Test
	public void test() {
		System.out.println("==============");
		log.info("++++++++++++++++++");

		IPage<CarInquiryEntity> page = new Page<>();
		page.setCurrent(1L);
		page.setSize(5L);


		IPage<CarInquiryEntity> pageList = carInquiryService.page(page);

		System.out.println(pageList);




//		List<CarInquiryEntity> list = carInquiryService.list();
//		list.forEach(System.out::println);


//		CarInquirySubItemEntity entity = new CarInquirySubItemEntity();
//		entity.setInquiryId("ORD1111111");
//		entity.setItemCode("TEST001");
//		entity.setSubId(111);
//		entity.setAmount(158D);
//		entity.setPremium(666D);
//		entity.setName("三者意外险");
//		entity.setContent("哈哈哈啊啊");
//		entity.setItemSelectId(333);
//		carInquirySubItemService.save(entity);
	}


	@Test
	public void test2() {
		RoleEntity role = new RoleEntity();
		role.setNAME("管理员");
		role.setREMARK("拥有高权限");
		role.setSTATUS(StatusEnum.FALSE);

		role.setCREATE_BY("admin");
		role.setCREATE_TIME(LocalDateTime.now());

		roleService.save(role);

		List<RoleEntity> list = roleService.list();
		list.forEach(System.out::println);
	}

	@Test
	public void test3() {
		List<RoleEntity> list = roleService.list();
		list.forEach(System.out::println);
	}



}
