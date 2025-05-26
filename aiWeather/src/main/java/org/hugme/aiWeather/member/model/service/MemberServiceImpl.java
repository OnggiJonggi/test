package org.hugme.aiWeather.member.model.service;

import org.hugme.aiWeather.member.model.dao.MemberDao;
import org.hugme.aiWeather.member.vo.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDao dao;

	@Override
	public int insertMember(Member m) {
		return dao.insertMember(sqlSession,m);
	}

	@Override
	public int checkUserId(String id) {
		return dao.checkUserId(sqlSession,id);
	}

	@Override
	public String selectUserId(int userNo) {
		return dao.selectUserId(sqlSession,userNo);
	}
	
	
}
