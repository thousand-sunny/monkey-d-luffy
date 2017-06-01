package pub.strawhat.luffy.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pub.strawhat.luffy.model.sys.UserVO;

@Mapper
public interface UserMapper {

	UserVO findAuthUserByName(@Param("username") String username);
	
}