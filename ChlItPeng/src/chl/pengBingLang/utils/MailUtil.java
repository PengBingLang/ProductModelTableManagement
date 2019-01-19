package chl.pengBingLang.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * javaMail的邮件工具类
 * 
 * @author PengBingLang 彭秉浪
 */
public class MailUtil {
	/**
	 * 使用加密的方式，利用465端口进行传输邮件，开启ssl
	 * 
	 * @param mailTo_收件人，用逗号分隔
	 * @param mailCC_抄送，用逗号分隔
	 * @param mailSubject_主题
	 * @param mailText_正文
	 * @author PengBingLang 彭秉浪
	 */
	public static void sendEmail(String mailTo, String mailCC, String mailSubject, String mailText) {
		try {
			final String sendMailAddress = "plm@chl.com.cn"; // 发件人邮箱用户名
			final String receiveMailPassword = "wcadmin@P1"; // 发件人邮箱密码

			// 设置邮件会话参数
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");// 使用的协议（JavaMail规范要求）
			props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");// 邮箱的发送服务器地址
			props.setProperty("mail.smtp.port", "465");// 邮箱发送服务器端口
			props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
			props.setProperty("mail.smtp.auth", "true");// 设置邮件发送需要认证

			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtp.socketFactory.fallback", "false");

			// 获取到邮箱会话，利用匿名内部类的方式，将发送者邮箱用户名和密码授权给jvm
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(sendMailAddress, receiveMailPassword);
				}
			});
			// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
			session.setDebug(true);

			// 通过会话，创建一个邮件消息
			MimeMessage message = new MimeMessage(session);
			// 设置发件人
			message.setFrom(new InternetAddress(sendMailAddress)); // 发件人邮箱
			// 设置收件人，to为收件人，cc为抄送，bcc为密送，true表示校验邮件格式
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo, true));
			if (null != mailCC && mailCC.length() > 1) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailCC, true));
			}
			// 设置邮件主题
			message.setSubject(mailSubject);
			// 设置邮件正文
			message.setText(mailText);
			// message.setContent(mailText, "text/html;charset=utf8");
			// 设置发送的日期
			message.setSentDate(new Date());
			// 调用Transport的send方法去发送邮件
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
