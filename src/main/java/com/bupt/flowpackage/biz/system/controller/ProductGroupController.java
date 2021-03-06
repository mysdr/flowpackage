package com.bupt.flowpackage.biz.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bupt.flowpackage.biz.system.model.ProductAddReq;
import com.bupt.flowpackage.biz.system.model.ProductGroupAddReq;
import com.bupt.flowpackage.biz.system.model.ProductGroupCloneReq;
import com.bupt.flowpackage.biz.system.model.ProductGroupReq;
import com.bupt.flowpackage.biz.system.model.ProductListReq;
import com.bupt.flowpackage.biz.system.model.ProductResp;
import com.bupt.flowpackage.biz.system.service.ProductGroupService;
import com.bupt.flowpackage.common.domain.BaseResponse;
import com.bupt.flowpackage.common.domain.Page;
import com.bupt.flowpackage.common.exception.ExceptionHelper;
import com.bupt.flowpackage.mybatis.trade.product.model.Product;
import com.bupt.flowpackage.mybatis.trade.productgroup.model.ProductGroup;

@Controller
@RequestMapping("/system/productgroup")
public class ProductGroupController {
	public static Logger logger = LoggerFactory.getLogger(ProductGroupController.class);
	
	public static final String PATH = "/system/productgroup/";

	@Resource
	private ProductGroupService productGroupService;
	/**
	* @Description 基础产品组
	* @param @return
	* @return String
	 */
	@RequestMapping("/pgroup-list")
	public String productGroup() {
		return PATH + "pgroup-list";
	}
	
	/**
	* @Description 产品组添加
	* @param @return
	* @return String
	 */
	@RequestMapping("/pgroup-add")
	public String productGroupAdd() {
		return PATH + "pgroup-form";
	}
	
	/**
	* @Description 产品查询
	* @param @return
	* @return String
	 */
	@RequestMapping("/product-query")
	public String productQuery() {
		return PATH + "product-query";
	}
	
	/**
	* @Description 产品组克隆
	* @param @return
	* @return String
	 */
	@RequestMapping("/pgroup-clone")
	public String productGroupClone(ModelMap modelMap) {
		try{
			List<ProductGroup>  pgroupList = productGroupService.getProductGroupList();
			modelMap.addAttribute("pgroupList", pgroupList);
		}catch(Exception e) {
			logger.error("产品组克隆页面访问失败!", e);
			throw e;
		}
		return PATH + "pgroup-clone";
	}
	
	@ResponseBody
	@RequestMapping("/api/pgroup-clone")
	public BaseResponse<String> pgroupClone(ProductGroupCloneReq req) {
		BaseResponse<String> baseResp = new BaseResponse<String>();
		try{
			productGroupService.productGroupClone(req);
			baseResp.setMsg("产品组克隆成功!");
		}catch(Exception e) {
			baseResp = ExceptionHelper.createResponse(e);
		}
		return baseResp;
	}
	
	/**
	* @Description 获取基础产品组列表
	* @param @return
	* @return BaseResponse<ProvinceResp>
	 */
	@ResponseBody
	@RequestMapping("/api/getProductGroup")
	public BaseResponse<ProductGroup> getProductGroup(ProductGroupReq req) {
		BaseResponse<ProductGroup> baseResp = new BaseResponse<ProductGroup>();
		try{
			Page<ProductGroup> pages = productGroupService.getProductGroupPage(req);
			baseResp.setPages(pages);
		}catch(Exception e) {
			baseResp = ExceptionHelper.createResponse(e);
		}
		return baseResp;
	}
	
	/**
	* 产品查询
	* @param @param req
	* @param @return
	* @return BaseResponse<ProductGroup>
	 */
	@ResponseBody
	@RequestMapping("/api/productquery")
	public BaseResponse<Product> productQuery(ProductListReq req) {
		BaseResponse<Product> baseResp = new BaseResponse<Product>();
		try{
			Page<Product> page = productGroupService.getProductListBySelect(req);
			baseResp.setPages(page);
		}catch(Exception e) {
			baseResp = ExceptionHelper.createResponse(e);
		}
		return baseResp;
	}
	
	@ResponseBody
	@RequestMapping("/api/product-update")
	public BaseResponse<ProductGroup> productUpdate(ProductAddReq req) {
		BaseResponse<ProductGroup> baseResp = new BaseResponse<ProductGroup>();
		try{
			productGroupService.productUpdate(req);
			baseResp.setMsg("产品更新成功!");
		}catch(Exception e) {
			baseResp = ExceptionHelper.createResponse(e);
		}
		return baseResp;
	}
	
	@ResponseBody
	@RequestMapping("/api/pgroup-update")
	public BaseResponse<String> pgroupUpdate(ProductGroupAddReq req) {
		BaseResponse<String> baseResp = new BaseResponse<String>();
		try{
			productGroupService.productGroupAdd(req);
			baseResp.setMsg("产品组添加成功!");
		}catch(Exception e) {
			baseResp = ExceptionHelper.createResponse(e);
		}
		return baseResp;
	}
	
	@ResponseBody
	@RequestMapping("/pgroup-delete")
	public BaseResponse<String> pgroupDelete(@RequestParam(required=true)Integer id) {
		BaseResponse<String> baseResp = new BaseResponse<String>();
		try{
			productGroupService.productGroupDelete(id);
			baseResp.setMsg("产品组删除成功!");
		}catch(Exception e) {
			baseResp = ExceptionHelper.createResponse(e);
		}
		return baseResp;
	}
	/**
	 * <p>获取产品组下的产品</p>   
	 * @param @param pgroupId
	 * @param @param modelMap
	 * @param @return      
	 * @return String
	 */
	@RequestMapping("/pgroup-productedit")
	public String productEdit(@RequestParam(required=true)Integer pgroupId, ModelMap modelMap) {
		try{
			ProductResp resp = productGroupService.getProductListByGroupId(pgroupId);
			modelMap.addAttribute("resp", resp);
		}catch(Exception e) {
			logger.error("产品组添加产品页面访问失败!", e);
			throw e;
		}
		return PATH + "pgroup-productedit";
	} 
	/**
	* @Description 产品组查看产品页面
	* @param @param pgroupId
	* @param @param modelMap
	* @param @return
	* @return String
	 */
	@RequestMapping("/pgroup-productlist")
	public String productList(@RequestParam(required=true)Integer pgroupId, ModelMap modelMap) {
		try{
			ProductResp resp = productGroupService.getProductListByGroupId(pgroupId);
			modelMap.addAttribute("resp", resp);
		}catch(Exception e) {
			logger.error("产品组查看产品页面访问失败!", e);
			throw e;
		}
		return PATH + "pgroup-productlist";
	} 
}
