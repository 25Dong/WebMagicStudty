package com.mark.webmagic.csdn;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现PageProcessor
 * 1.爬虫的配置
 * 2.页面元素的抽取
 * 3.链接的发现
 * @author YIMA
 *
 */
public class CSDNProcesser implements PageProcessor {

	private Site site= new Site();
	public void process(Page page) {
		Html html = page.getHtml();
		//确定要加入待爬取队列的URL
		List<String> links =  html.css("div.pagination-wrapper").links().all();
		parserCSDNModel(html.getDocument(),page);
		page.addTargetRequests(links);
	}

	//解析页面信息
	private void parserCSDNModel(Document document, Page page) {
		CSDNModel model = null;
		List<CSDNModel> models = new ArrayList<CSDNModel>();
		Element divList = document.getElementById("main");
		Elements divs = divList.select("li[class=blog-unit]");
		for(Element div:divs){
			model = new CSDNModel();
			String title = div.select("h3.blog-title").text().trim();
			String view = div.select("div.left-dis-24").get(1).text().trim();
			String time = div.select("div.left-dis-24").get(0).text().trim();
			model.setTitle(title);
			model.setView(view);
			model.setTime(time);
			models.add(model);
		}
		page.putField("model",models);
	}

	public Site getSite() {
		site.me().setRetryTimes(3).setSleepTime(100);
		return site;
	}

}
