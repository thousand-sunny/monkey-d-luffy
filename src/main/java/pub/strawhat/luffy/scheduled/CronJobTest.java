package pub.strawhat.luffy.scheduled;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJobTest {

	int i = 0;

//	@Scheduled(cron = "0/10 * * * * *")
	public void everySecond() {
		System.out.println("第" + (++i) + "次调用，每秒任务，当前时间：" + new Date());
	}

}
