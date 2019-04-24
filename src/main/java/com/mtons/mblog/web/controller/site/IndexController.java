/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.web.controller.site;

import javax.servlet.http.HttpServletRequest;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.utils.IPKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mtons.mblog.web.controller.BaseController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author langhsu
 *
 */
@Controller
public class IndexController extends BaseController{

//	public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

	@Autowired
	private JavaMailSender mailSender;

	@Value("${music.path1}")
	private String musicPath;

	@Value("${site.location}")
	private String location;

	@RequestMapping(value= {"/", "/index"})
	public String root(ModelMap model, HttpServletRequest request) {
		String order = ServletRequestUtils.getStringParameter(request, "order", Consts.order.NEWEST);
		int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
		model.put("order", order);
		model.put("pageNo", pageNo);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1021558365@qq.com");
		message.setTo("1021558365@qq.com");

		message.setSubject("通知邮件");
		String device = request.getHeader("User-Agent");
		String ip = IPKit.getIpAddrByRequest(request);
		message.setText("ip为:" + ip + "  ,使用了"+device+"设备，刚刚访问了你的网站:https://www.yilukkk.com  时间为：" + new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));

//		fixedThreadPool.execute(new Runnable() {
//			@Override
//			public void run() {
//				mailSender.send(message);
//				System.out.println("邮件已发送...");
//			}
//		});


		new Thread(new Runnable() {
			@Override
			public void run() {
				message.setTo("2290353065@qq.com");
				mailSender.send(message);
		System.out.println("邮件已发送...");
			}
		}).start();

		String view = view(Views.INDEX);
		return view;
	}

	@RequestMapping(value= {"/lesley"})
	public String lesley(ModelMap model, HttpServletRequest request) {

		String view = view("/lesley");
		model.put("musicPath",musicPath);
		return view;
	}

}
