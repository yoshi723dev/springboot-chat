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
public class TChatLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CHAT_GROUP_ID")
    private int chat_group_id;
    
    @Id
    @Column(name="CHAT_DATE")
    private Date chat_date;

    @Column(name="USER_ID")
    private int user_id;

    @Column(name="COMMENT")
    private String comment;

    @Column(name="DELETE_FLG")
    private String delete_flg;

    @Column(name="TIME_STAMP")
    private Date time_stamp;
}
