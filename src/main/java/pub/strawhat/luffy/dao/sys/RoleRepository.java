package pub.strawhat.luffy.dao.sys;

import org.springframework.data.jpa.repository.JpaRepository;

import pub.strawhat.luffy.model.sys.RoleVO;

public interface RoleRepository extends JpaRepository<RoleVO, Integer> {

}
