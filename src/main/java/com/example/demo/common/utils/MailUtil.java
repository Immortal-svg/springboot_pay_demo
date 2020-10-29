package com.example.demo.common.utils;

import org.apache.commons.mail.*;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class MailUtil {

	private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

	private static final String sendEmail = "monitor@shfzm.cn";

	private static final String sendEmailPass = "Fzm!@##.2212";

	private static final String sendEmailName = "业务监控";

	private static final String sendEmailHost = "smtp.exmail.qq.com";

	private static final Integer sendEmailPort = 465;

	public static boolean sendSimpleEmail(String[] userEmail, String emailSubject, String emailContent) {

		try {
			// 特殊情况开启
			// email.setStartTLSEnabled(true);

			SimpleEmail email = new SimpleEmail();
			// 设置发送主机的服务器地址
			email.setHostName(sendEmailHost);
			// 设置端口号
			email.setSmtpPort(sendEmailPort);// 默认也是sendEmailPort
			
			email.setSSLOnConnect(true);
   		    email.setStartTLSEnabled(true);
			// 如果要求身份验证，设置用户名、密码，分别为发件人在邮件服务器上注册的用户名和密码
			email.setAuthenticator(new DefaultAuthenticator(sendEmail, sendEmailPass));
			// 设置收件人邮箱以及名称
			email.addTo(userEmail);
			// email.addTo("zhufuniliu12345@qq.com");
			// 发件人邮箱以及名称，邮件编码格式
			email.setFrom(sendEmail, sendEmailName, "UTF-8");
			// 设置邮件的主题
			email.setSubject(emailSubject);
			// 邮件正文消息
			email.setMsg(emailContent);

			email.setCharset("UTF-8");
			// 发送
			email.send();
			return true;
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return false;
		}

	}

	public static boolean sendSimpleSSEmail(String[] userEmail, String emailContent, String emailSubject) {

		try {

			SimpleEmail email = new SimpleEmail();
			// 设置发送主机的服务器地址
			email.setHostName(sendEmailHost);
			email.setSmtpPort(sendEmailPort);
			// 端口号
			// email.setSslSmtpPort("465");
			// 登陆邮件服务器的用户名和密码
			email.setAuthenticator(new DefaultAuthenticator(sendEmail, sendEmailPass));// 注意qq邮箱需要授权，在设置那里生成一个随机码，然后填写到密码框。
			// 接收人
			email.setSSLOnConnect(true);
   		    email.setStartTLSEnabled(true);

			// 设置收件人邮箱以及名称
			email.addTo(userEmail);
			// 发件人邮箱以及名称，邮件编码格式
			email.setFrom(sendEmail, sendEmailName, "UTF-8");
			// 设置邮件的主题
			email.setSubject(emailSubject);
			// 邮件正文消息
			email.setMsg(emailContent);

			email.setCharset("UTF-8");
			// 发送
			email.send();
			return true;
		} catch (Exception e) {
			logger.error("发送失败", e);
			return false;
		}

	}

	public static boolean sendEmaillAttachment(String[] userEmail, String emailContent, String emailSubject,
			Map<String, String> attaAddress) {

		try {

			// Create the email message
			MultiPartEmail email = new MultiPartEmail();

			Set<String> keySet = attaAddress.keySet();

			keySet.forEach(name -> {
				// 创建一个Email附件
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(attaAddress.get(name));// 本地资源需要存在
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setDescription("");
				attachment.setName(name);// 自定义文件名，并且格式要一致，不然附近收到的话，有可能读不出来。
				// add the attachment
				try {
					email.attach(attachment);
				} catch (EmailException e) {
					e.printStackTrace();
				}
			});

			// smtp host
			email.setHostName(sendEmailHost);
			// 设置端口号
			email.setSmtpPort(sendEmailPort);// 默认也是sendEmailPort
			// 登陆邮件服务器的用户名和密码
			email.setAuthenticator(new DefaultAuthenticator(sendEmail, sendEmailPass));
			email.setSSLOnConnect(true);
   		    email.setStartTLSEnabled(true);
			// 设置收件人邮箱以及名称
			email.addTo(userEmail);
			// 发件人邮箱以及名称，邮件编码格式
			email.setFrom(sendEmail, sendEmailName, "UTF-8");
			// 设置邮件的主题
			email.setSubject(emailSubject);
			// 邮件正文消息
			email.setMsg(emailContent);

			email.setCharset("UTF-8");

			// send the email
			email.send();
			return true;
		} catch (Exception e) {
			logger.error("发送失败", e);
			return false;
		}

	}

	public static boolean sendEmaillAttachmentWithUrl(String[] userEmail, String emailContent, String emailSubject,
			Map<String, String> attaAddress) {

		try {

			// Create the email message
			MultiPartEmail email = new MultiPartEmail();

			Set<String> keySet = attaAddress.keySet();

			keySet.forEach(name -> {
				// 创建一个Email附件
				EmailAttachment attachment = new EmailAttachment();
				try {
					attachment.setURL(new URL(attaAddress.get(name)));
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setDescription("");
				attachment.setName(name);// 自定义文件名，并且格式要一致，不然附近收到的话，有可能读不出来。
				// add the attachment
				try {
					email.attach(attachment);
				} catch (EmailException e) {
					e.printStackTrace();
				}
			});

			// smtp host
			email.setHostName(sendEmailHost);
			// 设置端口号
			email.setSmtpPort(sendEmailPort);// 默认也是sendEmailPort
			// 登陆邮件服务器的用户名和密码
			email.setAuthenticator(new DefaultAuthenticator(sendEmail, sendEmailPass));
			email.setSSLOnConnect(true);
   		    email.setStartTLSEnabled(true);
			// 设置收件人邮箱以及名称
			email.addTo(userEmail);

			// 发件人邮箱以及名称，邮件编码格式
			email.setFrom(sendEmail, sendEmailName, "UTF-8");
			// 设置邮件的主题
			email.setSubject(emailSubject);
			// 邮件正文消息
			email.setMsg(emailContent);

			email.setCharset("UTF-8");

			email.send();
			return true;
		} catch (Exception e) {
			logger.error("发送失败", e);
			return false;
		}

	}

	public static boolean sendEmaillHTML(String[] userEmail, String emailContent, String emailSubject) {

		try {
			// Create the email message
			HtmlEmail email = new HtmlEmail();
			// 设置发送主机的服务器地址
			email.setHostName(sendEmailHost);
			// 设置端口号
			email.setSmtpPort(sendEmailPort);// 默认也是sendEmailPort
			// 如果要求身份验证，设置用户名、密码，分别为发件人在邮件服务器上注册的用户名和密码
			email.setAuthenticator(new DefaultAuthenticator(sendEmail, sendEmailPass));

			// URL url = new
			// URL("http://www.apache.org/images/asf_logo_wide.gif");
			// String cid = email.embed(url, "Apache logo");
			email.setSSLOnConnect(true);
   		    email.setStartTLSEnabled(true);
			// 设置收件人邮箱以及名称
			email.addTo(userEmail);
			// 发件人邮箱以及名称
			// email.setFrom ("daliu_it@163.com", "发件人名称");
			// 发件人邮箱以及名称，邮件编码格式
			email.setFrom(sendEmail, sendEmailName, "UTF-8");
			// 设置邮件的主题
			email.setSubject(emailSubject);

			// set the html message
			email.setHtmlMsg(emailContent);
			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");
			email.setCharset("UTF-8");
			// send the email
			email.send();
			return true;
		} catch (Exception e) {
			logger.error("发送失败", e);
			return false;
		}

	}

	public static boolean sendEmaillHTMLWithImage(String[] userEmail, String emailContent, String emailSubject) {

		try {
			// create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			// 设置发送主机的服务器地址
			email.setHostName(sendEmailHost);
			// 设置端口号
			email.setSmtpPort(sendEmailPort);// 默认也是sendEmailPort
			// 如果要求身份验证，设置用户名、密码，分别为发件人在邮件服务器上注册的用户名和密码
			email.setAuthenticator(new DefaultAuthenticator(sendEmail, sendEmailPass));
			// load your HTML email template
			String htmlEmailTemplate = "嵌入图片的HTML文本:<img src=\"http://www.apache.org/images/feather.gif\"> ....";

			URL url = new URL("http://www.apache.org");
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			// 设置收件人邮箱以及名称
			email.addTo(userEmail);
			// 发件人邮箱以及名称
			// email.setFrom ("daliu_it@163.com", "发件人名称");
			// 发件人邮箱以及名称，邮件编码格式
			email.setFrom(sendEmail, sendEmailName, "UTF-8");
			// 设置邮件的主题
			email.setSubject(emailSubject);
			email.setSSLOnConnect(true);
   		    email.setStartTLSEnabled(true);
			// set the html message
			email.setHtmlMsg(htmlEmailTemplate);

			email.setCharset("UTF-8");

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");

			// send the email
			email.send();
			return true;
		} catch (Exception e) {
			logger.error("发送失败", e);
			return false;
		}

	}


}