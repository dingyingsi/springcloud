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
 */

@DynamicInsert
@DynamicUpdate
@Entity
@Table ( name ="word" )
@Data
public class Word implements Serializable {
	private static final long serialVersionUID =  593274901001305026L;

	@Id
	//@GenericGenerator(name="assigned_gen",strategy="assigned")
	//@GeneratedValue(generator="assigned_gen")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   	@Column(name = "word_name" )
	private String wordName;

   	@Column(name = "word_phonetic_sign" )
	private String wordPhoneticSign;

   	@Column(name = "word_meaning" )
	private String wordMeaning;

   	@Column(name = "word_example")
   	private String wordExample;

   	@Column(name = "create_time")
   	private Date createTime;

   	@Column(name = "update_time")
   	private Date updateTime;

}
