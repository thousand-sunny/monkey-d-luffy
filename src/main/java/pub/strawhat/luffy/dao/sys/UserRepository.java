package pub.strawhat.luffy.dao.sys;

import org.springframework.data.jpa.repository.JpaRepository;

import pub.strawhat.luffy.model.sys.UserVO;

public interface UserRepository extends JpaRepository<UserVO, Integer> {

	UserVO findByUsername(String username);

}
