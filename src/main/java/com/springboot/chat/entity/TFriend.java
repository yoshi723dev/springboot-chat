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
public class TFriend implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name="USER_ID")
	private int userId;

    @Column(name="FRIEND_USER_ID")
	private int friendUserId;
    
    @Column(name="REGIST_DATE")
	private Date registDate;

    @Column(name="DELETE_FLG")
	private String deleteFlg;

    @Column(name="TIME_STAMP")
	private Date timeStamp;
}
