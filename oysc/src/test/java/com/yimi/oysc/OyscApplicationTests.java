package com.yimi.oysc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yimi.oysc.entity.CarInquiryEntity;
import com.yimi.oysc.entity.OperationLogEntity;
import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.enumerate.PermissionTypeEnum;
import com.yimi.oysc.enumerate.StatusEnum;
import com.yimi.oysc.mapper.OperationLogMapper;
import com.yimi.oysc.mapper.RoleMapper;
import com.yimi.oysc.service.ICarInquiryService;
import com.yimi.oysc.service.IRoleService;
import com.yimi.oysc.vo.select.SelectRoleVO;
import com.yimi.oysc.vo.result.SelectRoleResultVO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class OyscApplicationTests {


	@Autowired
	private ICarInquiryService carInquiryService;

	@Autowired
	private IRoleService roleService;


	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private OperationLogMapper operationLogMapper;

	@Autowired
	private WebApplicationContext applicationContext;

	@Test
	public void testGetAllURL() {
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		// 拿到Handler适配器中的全部方法
		Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
//		for (RequestMappingInfo info : methodMap.keySet()){
//
//			Set<String> urlSet = info.getPatternsCondition().getPatterns();
//			// 获取全部请求方式
//			Set<RequestMethod> Methods = info.getMethodsCondition().getMethods();
//			System.out.println(Methods.toString());
//			for (String url : urlSet){
//				// 加上自己的域名和端口号，就可以直接调用
////				urlList.add("http://localhost:XXXX"+url);
//				System.out.println(url);
//			}
//		}

		methodMap.values().forEach(handlerMethod -> {
			Method method = handlerMethod.getMethod();
			Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
			ApiOperation apiOperation = method.getDeclaredAnnotation(ApiOperation.class);
			if (apiOperation != null) {



				System.out.println(apiOperation.value());
			}

		});

	}

	private PermissionTypeEnum getPermissionType(Method method) {
		GetMapping get = method.getDeclaredAnnotation(GetMapping.class);
		if (get != null) {
		}


		return null;
	}

	@Test
	public void testOperationLog() {
//		OperationLogEntity entity = new OperationLogEntity();
//		entity.setName("新增角色");
//		entity.setType(OperationLogTypeEnum.ADD);
//		entity.setRequestJson("{\"name\":\"request\"}");
//		entity.setResponseJson("{\"name\":\"response\"}");
//		entity.setRequestIp("127.0.0.1");
//		entity.setCreateTime(LocalDateTime.now());
//		entity.setCreateBy("sa");
//		operationLogMapper.insert(entity);

		List<OperationLogEntity> list = operationLogMapper.selectList(new QueryWrapper<>());
		list.forEach(System.out::println);
	}

	@Test
	public void testSelect() {
		SelectRoleVO condVO = new SelectRoleVO();
		condVO.setCode("ROLE_ADMIN");
		condVO.setStatus(StatusEnum.FALSE);

		Page<?> page = new Page<>();
		page.setSize(10);
		page.setCurrent(1);

		Page<SelectRoleResultVO> pageList = roleMapper.selectRoles(page, condVO);
		System.out.println(pageList.getTotal());
		pageList.getRecords().forEach(System.out::println);

	}

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


//	@Test
//	public void test2() {
//		RoleEntity role = new RoleEntity();
//		role.setNAME("管理员");
//		role.setREMARK("拥有高权限");
//		role.setSTATUS(StatusEnum.FALSE);
//
//		role.setCREATE_BY("admin");
//		role.setCREATE_TIME(LocalDateTime.now());
//
//		roleService.save(role);
//
//		List<RoleEntity> list = roleService.list();
//		list.forEach(System.out::println);
//	}
//
	@Test
	public void test3() {
		QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
		RoleEntity role = new RoleEntity();
		role.setStatus(StatusEnum.TRUE);
		wrapper.setEntity(role);
		List<RoleEntity> list = roleService.list(wrapper);
		list.forEach(System.out::println);
	}



}
