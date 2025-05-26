package org.hugme.aiWeather.member.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	
	private Date enrollDate;
	private Date modifyDate;
	private String status;
}
