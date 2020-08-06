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
@Table ( name ="base_admin_function_menu_button" )
@Data
public class BaseAdminFunctionMenuButton implements Serializable {


	private static final long serialVersionUID =  2171885241290054960L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 功能id
	 */
   	@Column(name = "function_id" )
	private Long functionId;

	/**
	 * 菜单id
	 */
   	@Column(name = "menu_id" )
	private Long menuId;

	/**
	 * 按钮id
	 */
   	@Column(name = "button_id" )
	private Long buttonId;

	/**
	 * 操作人员
	 */
   	@Column(name = "operator" )
	private String operator;

   	@Column(name = "create_time" )
	private Date createTime;

   	@Column(name = "update_time" )
	private Date updateTime;

	/**
	 * 备注信息
	 */
   	@Column(name = "remark" )
	private String remark;

	/**
	 * 1-车维后台，2-车维app， 9-骑行
	 */
   	@Column(name = "type" )
	private Integer type;

}
