package org.hugme.aiWeather.member.model.dao;

import org.hugme.aiWeather.member.vo.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}

	public int checkUserId(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.selectOne("memberMapper.checkUserId", id);
	}

	public String selectUserId(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectOne("memberMapper.selectUserId", userNo);
	}
	
}
