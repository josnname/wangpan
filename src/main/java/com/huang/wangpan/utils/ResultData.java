package com.huang.wangpan.utils;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ResultData {
	private Object data;
	public void setData(Object data) {
		this.data = data;
	}
	

	public void writeToResponse(HttpServletResponse rp) {
		String jsonstr = JSONObject.toJSONString(data);
		byte[] bs;
		try {
			bs = jsonstr.trim().getBytes("utf-8");
			rp.setContentType("application/json;charset=UTF-8");
			rp.getOutputStream().write(bs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
/*
 * {
 *   code:200,
 *   msg:"",
 *   data:{}
 * }
 * 
 * */
	
	/*
	 * {
	 *   code:200,
	 *   msg:"",
	 *   data:[]
	 * }
	 * 
	 * */
}
