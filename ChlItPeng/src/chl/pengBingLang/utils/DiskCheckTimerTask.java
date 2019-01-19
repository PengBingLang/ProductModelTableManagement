package chl.pengBingLang.utils;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import org.springframework.core.io.ClassPathResource;

/**
 * 检查指定磁盘空间
 * 
 * @author PengBingLang 彭秉浪
 */
public class DiskCheckTimerTask extends TimerTask {

	// 获取属性文件中的值
	private static Properties properties = new Properties();
	static {
		try {
			// 加载属性文件
			InputStreamReader buffer = new InputStreamReader(
					new ClassPathResource("pengBingLang.properties").getInputStream(), "UTF-8");
			properties.load(buffer);
		} catch (Exception e) {
		}
	}
	private String partition = properties.getProperty("diskCheck.partition");
	private String mimSize = properties.getProperty("diskCheck.mimSize");

	private String mailTo = properties.getProperty("diskCheck.mailTo");
	private String mailSubject = properties.getProperty("diskCheck.mailSubject");
	private String mailTestBefore = properties.getProperty("diskCheck.mailTestBefore");
	private String mailTestAfter = properties.getProperty("diskCheck.mailTestAfter");

	// 用于记录日志的上下文
	private ServletContext context = null;

	public DiskCheckTimerTask(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		StringBuilder diskCheckMessageToMail = new StringBuilder("");
		String[] partitionS = partition.split("\\|");
		String[] mimSizeS = mimSize.split("\\|");
		for (int i = 0; i < partitionS.length; i++) {
			File diskPartition = new File(partitionS[i] + ":");
			long usablePatitionSpace = diskPartition.getUsableSpace() / (1024 * 1024 * 1024);// GB
			String diskCheckMessage = "本地磁盘 " + partitionS[i] + " 可用空间不足 " + usablePatitionSpace + " GB";
			context.log(diskCheckMessage);
			if (usablePatitionSpace <= Integer.parseInt(mimSizeS[i])) {
				diskCheckMessageToMail.append(diskCheckMessage);
				diskCheckMessageToMail.append("\n");
			}
		}
		if (diskCheckMessageToMail.capacity() < 1) {
			return;
		}
		diskCheckMessageToMail.insert(0, mailTestBefore);
		diskCheckMessageToMail.append(mailTestAfter);
		// 开始发送邮件
		MailUtil.sendEmail(mailTo, null, mailSubject, diskCheckMessageToMail.toString());
	}
}
