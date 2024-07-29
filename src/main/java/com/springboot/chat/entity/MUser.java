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
public class MUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="USER_ID")
    private int user_id;

    @Column(name="USER_NM")
    private String user_nm;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="REGIST_DATE")
    private Date regist_date;

    @Column(name="TIME_STAMP")
    private Date time_stamp;
}
