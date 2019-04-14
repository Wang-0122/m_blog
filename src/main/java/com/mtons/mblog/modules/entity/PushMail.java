/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 模块/内容分组
 * @author langhsu
 *
 */
@Entity
@Table(name = "mto_push_mail")
public class PushMail implements Serializable {
	private static final long serialVersionUID = 2436696690653745209L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 当前用户昵称
	 */
	@Column(length = 32)
	private String name;

	/**
	 *0为无效，1为有效
	 */
	@Column(name = "status",  length = 6)
	private int status;

	@Column(name = "email",  length = 32)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
