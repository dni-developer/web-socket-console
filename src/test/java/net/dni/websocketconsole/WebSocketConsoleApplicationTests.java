package net.dni.websocketconsole;

import net.dni.websocketconsole.model.LogMessage;
import net.dni.websocketconsole.repository.LogMessageRepository;
import net.dni.websocketconsole.service.PullLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSocketConsoleApplicationTests {

	@Autowired
	private PullLogService pullLogService;

	@Autowired
	private LogMessageRepository logMessageRepository;


	@Test
	public void contextLoads() {
		/*test context*/
	}

	@Test
	public void testPullLogService() throws IOException {
		List<LogMessage> logMessages = pullLogService.pullErrorLogs();
		logMessages.forEach(log -> logMessageRepository.save(log));
	}

}
