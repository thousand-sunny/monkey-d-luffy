package pub.strawhat.luffy.shiro;

import java.util.ArrayList;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(true)
public class AnnotationScanner implements BeanDefinitionRegistryPostProcessor {
	ArrayList<String> cache = new ArrayList<>();

	@Override

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

	}

	@Override

	public void postProcessBeanFactory(ConfigurableListableBeanFactory postProcessBeanFactory) throws BeansException {

		Map<String, Object> map = postProcessBeanFactory.getBeansWithAnnotation(RequiresPermissions.class);

		for (String key : map.keySet()) {

			cache.add(key);

			System.out.println("beanName= " + key);

		}

	}
}
