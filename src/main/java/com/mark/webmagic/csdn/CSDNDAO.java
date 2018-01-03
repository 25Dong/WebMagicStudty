package com.mark.webmagic.csdn;

import java.util.List;

public class CSDNDAO {

	public static void insert(List<CSDNModel> models) {
		int count = 0;
		for(CSDNModel model:models){
			count++;
			System.out.println("阅读次数："+model.getView()+"  发布时间："+model.getTime()+"   标题："+model.getTitle());
			System.out.println("----------------------------------------------------------");
		}
		System.out.println(count);
	}

}
