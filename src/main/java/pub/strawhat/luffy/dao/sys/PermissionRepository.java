package pub.strawhat.luffy.dao.sys;

import org.springframework.data.jpa.repository.JpaRepository;

import pub.strawhat.luffy.model.sys.PermissionVO;

public interface PermissionRepository extends JpaRepository<PermissionVO, Integer> {
	
	public PermissionVO findByModuleAndOperation(String module, String operation);

}