package org.hugme.aiWeather.email.model.dao;

import org.hugme.aiWeather.email.vo.MemberToken;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDao {
	public int insertMemberToken(SqlSessionTemplate sqlSession, MemberToken memberToken) {
		return sqlSession.insert("emailMapper.insertMemberToken", memberToken);
	}
}
