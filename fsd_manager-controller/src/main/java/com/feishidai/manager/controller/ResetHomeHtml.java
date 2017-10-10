package com.feishidai.manager.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feishidai.common.utils.FileUtils;
import com.feishidai.common.utils.PropertiesUtils;

@Controller
public class ResetHomeHtml {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/resetHomeHtml")
	public void resetHomeHtml(HttpServletRequest req, HttpServletResponse res, String uid) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (StringUtils.equals(uid, sdf.format(new Date()))) {
			String path = req.getSession().getServletContext().getRealPath("") + "/previewhome/";
			File previewhome = new File(path);
			FileUtils.deleteDirectory(previewhome);
			logger.info("deleteDirectory::" + previewhome);
			
			String homecontentpath = PropertiesUtils.get("manager", "homepagepath");
			File homepage = new File(homecontentpath);
			if (homepage.exists()) {
				for (File file : homepage.listFiles()) {
					if (file.isDirectory()) {
						FileUtils.copyDirectoryToDirectory(file, previewhome);
					} else {
						FileUtils.copyFileToDirectory(file, previewhome);
					}
				}
			}
			logger.info("copyDirectory::" + homecontentpath);
		}

	}
}
