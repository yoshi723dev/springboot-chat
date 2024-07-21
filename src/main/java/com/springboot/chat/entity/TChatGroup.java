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
public class TChatGroup implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name="USER_ID")
	private int user_id;

    @Column(name="FREND_USER_ID")
	private int frend_user_id;
    
    @Column(name="REGIST_DATE")
	private Date regist_date;

    @Column(name="DELETE_FLG")
	private String delete_flg;

    @Column(name="TIME_STAMP")
	private Date time_stamp;
}
