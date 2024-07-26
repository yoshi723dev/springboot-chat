package com.springboot.chat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class MUser implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name="USER_ID")
	private int userId;

    @Column(name="USER_NM")
	private String userNm;

    @Column(name="PASSWORD")
	private String password;

    @Column(name="REGIST_DATE")
	private Date registDate;

    @Column(name="TIME_STAMP")
	private Date timeStamp;
}
