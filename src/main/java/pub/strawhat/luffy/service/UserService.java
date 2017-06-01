package pub.strawhat.luffy.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pub.strawhat.luffy.dao.sys.PermissionRepository;
import pub.strawhat.luffy.dao.sys.UserMapper;
import pub.strawhat.luffy.dao.sys.UserRepository;
import pub.strawhat.luffy.model.sys.PermissionVO;
import pub.strawhat.luffy.model.sys.UserVO;
import pub.strawhat.luffy.shiro.PackageScanner;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private UserMapper userMapper;

	public UserVO findAuthUserByName(String username) {
		return userMapper.findAuthUserByName(username);
	}

	public UserVO findUserByName(String username) {
		return userRepository.findByUsername(username);
	}

	public UserVO findUserById(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public UserVO saveUser(UserVO user) {
		return userRepository.save(user);
	}

	public void delUserById(int id) {
		userRepository.delete(id);
	}

	public Page<UserVO> findUserList(Example<UserVO> example, Pageable pageable) {

		System.out.println(userRepository.count(example));
		return userRepository.findAll(example, pageable);
	}

	public void refreshPermissions() {

		PackageScanner d = new PackageScanner(new String[] { "pub.strawhat.luffy" }, Controller.class);
		Set<Class<?>> cs;
		try {
			cs = d.getClassSet();
			for (Class<?> c : cs) {
				Method[] ms = c.getDeclaredMethods();
				System.out.println(ms.length);
				for (Method m : ms) {
					RequiresPermissions p = m.getDeclaredAnnotation(RequiresPermissions.class);
					if (null != p) {
						for (String s : p.value()) {

							PermissionVO po = permissionRepository.findByModuleAndOperation(s.split(":")[0],
									s.split(":")[1]);
							if (null == po) {
								po = new PermissionVO();
								po.setModule(s.split(":")[0]);
								po.setOperation(s.split(":")[1]);
							}
							permissionRepository.save(po);
							System.out.println(s);
						}
					}
				}

			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
