package com.springboot.chat.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Component
public class TChatGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="CHAT_GROUP_ID")
	private int chat_group_id;
	
    @Column(name="USER_ID")
	private int user_id;

    @Column(name="DELETE_FLG")
	private String delete_flg;

    @Column(name="TIME_STAMP")
	private Date time_stamp;
}
