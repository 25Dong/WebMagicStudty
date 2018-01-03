package com.mark.webmagic.csdn;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

public class CSDNPipeLine implements Pipeline {

	public void process(ResultItems resultItems, Task task) {
		List<CSDNModel> models =  resultItems.get("model");
		CSDNDAO.insert(models);
	}

}
