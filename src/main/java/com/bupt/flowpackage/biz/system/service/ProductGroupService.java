package com.bupt.flowpackage.biz.system.service;

import java.util.List;

import com.bupt.flowpackage.biz.system.model.ProductAddReq;
import com.bupt.flowpackage.biz.system.model.ProductGroupAddReq;
import com.bupt.flowpackage.biz.system.model.ProductGroupCloneReq;
import com.bupt.flowpackage.biz.system.model.ProductGroupReq;
import com.bupt.flowpackage.biz.system.model.ProductListReq;
import com.bupt.flowpackage.biz.system.model.ProductResp;
import com.bupt.flowpackage.common.domain.Page;
import com.bupt.flowpackage.mybatis.trade.product.model.Product;
import com.bupt.flowpackage.mybatis.trade.productgroup.model.ProductGroup;

/**
 * <p>Description:产品组接口</p>
 * <p>Company:摩尔科技</p>
 * @author daojian
 * @date 2017年6月12日 下午10:28:25
 */
public interface ProductGroupService {
	/**
	* 获取产品组page
	* @param @param req
	* @param @return
	* @return Page<ProductGroup>
	 */
	public Page<ProductGroup> getProductGroupPage(ProductGroupReq bizReq);
	
	/**
	* @Description 查看所有的产品组
	* @param @return
	* @return List<ProductGroup>
	 */
	public List<ProductGroup> getProductGroupList();
	
	/**
	* 产品组添加
	* @param @param bizReq
	* @param @return
	* @return int
	 */
	public int productGroupAdd(ProductGroupAddReq bizReq);
	
	/**
	* 产品组克隆 会克隆产品组及产品组下的所有产品
	* @param @param bizReq
	* @param @return
	* @return int
	 */
	public int productGroupClone(ProductGroupCloneReq bizReq);
	
	/**
	* 产品添加
	* @param @param bizReq
	* @param @return
	* @return int
	 */
	public int productUpdate(ProductAddReq bizReq);
	
	/**
	* 产品组删除 同时删除产品组下的产品，同时通道和通道产品和客户产品
	* @param @param groupId
	* @param @return
	* @return int
	 */
	public int productGroupDelete(Integer pgroupId);
	
	/**
	 * <p>查看产品组下的产品列表</p>   
	 * @param @param pgroupId
	 * @param @return      
	 * @return ProductResp
	 */
	public ProductResp getProductListByGroupId(Integer pgroupId);
	
	/**
	* 根据各种条件查询产品list
	* @param @param bizReq
	* @param @return
	* @return ProductResp
	 */
	public Page<Product> getProductListBySelect(ProductListReq bizReq);
	
}
 