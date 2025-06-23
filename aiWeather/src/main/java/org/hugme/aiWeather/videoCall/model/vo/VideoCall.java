package org.hugme.aiWeather.videoCall.model.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VideoCall {
	private String vcNo;
	private String userNo;
	private byte[] vcId;
	private String vcSession;
	private String vcName;
	private Timestamp createTimestamp;
	private int maxParticipants;
	private String status;
	
	//방장 이름과 시드
	private String userName; //member
	private byte[] nameSeed; //member
	
	//현재 참여자
	private int currParticipants; //count vc_member
	
}
