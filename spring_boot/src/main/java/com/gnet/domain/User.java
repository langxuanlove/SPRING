package com.gnet.domain;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户注册表
 * 
 *	与数据库的表名字和字段名称一定要一样，并且区分大小写;
 *  而且最好建立表的时候使用类去创建而不是使用指定数据库列的方式创建.不然会报错. 
 */

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -5614453697619250524L;
	// 序号
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String name;
	private String isDeleted;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", isDeleted=");
		builder.append(isDeleted);
		builder.append("]");
		return builder.toString();
	}

}
