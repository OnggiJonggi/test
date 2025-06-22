package org.hugme.aiWeather.member.model.service;

import org.hugme.aiWeather.member.model.service.dao.MemberDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private MemberDao dao;
	
	//회원가입
	
}
