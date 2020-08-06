package com.dys.springcloud.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @author dingyingsi
 * @Date 2020-07-28 
 */

@DynamicInsert
@DynamicUpdate
@Entity
@Table ( name ="base_admin_function" )
@Data
public class BaseAdminFunction implements Serializable {


	private static final long serialVersionUID =  1882362366455244332L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * function_id
	 */
   	@Column(name = "function_id" )
	private Long functionId;

	/**
	 * 老车维功能id
	 */
   	@Column(name = "function_cw_id" )
	private Long functionCwId;

	/**
	 * 老车维骑行id
	 */
   	@Column(name = "function_qx_id" )
	private Long functionQxId;

	/**
	 * 编码
	 */
   	@Column(name = "code" )
	private String code;

	/**
	 * 导航名称
	 */
   	@Column(name = "nav_name" )
	private String navName;

	/**
	 * 动作类型
	 */
   	@Column(name = "action_type" )
	private String actionType;

	/**
	 * 功能名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 描述信息
	 */
   	@Column(name = "description" )
	private String description;

	/**
	 * 创建时间
	 */
   	@Column(name = "created_at" )
	private Date createdAt;

	/**
	 * 修改时间
	 */
   	@Column(name = "updated_at" )
	private Date updatedAt;

	/**
	 * 状态
	 */
   	@Column(name = "status" )
	private Integer status;

	/**
	 * 1-车维后台，2-车维app， 9-骑行
	 */
   	@Column(name = "type" )
	private Integer type;

	/**
	 * 地区编号
	 */
   	@Column(name = "area_id" )
	private Long areaId;

}
