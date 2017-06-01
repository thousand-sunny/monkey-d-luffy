package pub.strawhat.luffy.shiro;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class RefreshListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		System.out.println("ContextRefreshedEvent 测试！");
		String key = event.getApplicationContext().getEnvironment().getProperty("mybatis.typeAliasesPackage");
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
							System.out.println(s);
						}
					}
				}

			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Map<String, Object> map =
		// event.getApplicationContext().getBeansWithAnnotation(Controller.class);
		// for (String key : map.keySet()) {
		// System.out.println("1=" + map.get(key).getClass());
		// try {
		// Class<?> c = Class.forName(map.get(key).getClass().getName());
		//
		// Method[] m = c.getDeclaredMethods();
		// for (Method mm : m) {
		// Annotation[] annotations = mm.getAnnotations();
		// for (Annotation annotation : annotations) {
		// // 获取注解的具体类型
		// Class<? extends Annotation> annotationType =
		// annotation.annotationType();
		// if (RequiresPermissions.class == annotationType) {
		// System.out.println("RequiresPermissions=" + ((RequiresPermissions)
		// annotation).value());
		// }
		// }
		// if (mm.getName().equals("defaultHome")) {
		//
		// RequiresPermissions[] pp =
		// mm.getDeclaredAnnotationsByType(RequiresPermissions.class);
		// RequiresPermissions[] pp1 =
		// mm.getAnnotationsByType(RequiresPermissions.class);
		// if (null != pp && pp.length > 0) {
		// for (RequiresPermissions p : pp) {
		//
		// System.out.println("RequiresPermissions=" + p.value());
		// }
		// }
		// }
		//
		// }
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}

}
