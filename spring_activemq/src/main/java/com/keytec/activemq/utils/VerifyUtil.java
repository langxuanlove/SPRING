package com.keytec.activemq.utils;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import com.rop.AbstractRopRequest;
import com.rop.response.BusinessServiceErrorResponse;

/**
 * <pre>
 * 功能说明：参数验证工具类
 * </pre>
 * 
 * @author hao
 * @version 1.0
 */
public class VerifyUtil {

	/**
	 * 参数验证方法
	 */
	public static Object verifyThis(AbstractRopRequest obj,String... verifyNameList) {
		Validator v = new Validator();
		v.disableAllProfiles();
		List<ConstraintViolation> list = v.validate(obj, verifyNameList);
		if (!list.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			ConstraintViolation[] cvList=new ConstraintViolation[list.size()];
			 list.toArray(cvList);
			validate(cvList,sb) ;
			return new BusinessServiceErrorResponse("ncw.custom","USER_COUSTOM_ERROR", null, sb);
			
			/*
			 * 下列代码只输出验证错误里第一个错误
			 */
			//如果有错误则输出第一个错误
			/*if(list.get(0).getCauses()!=null) //如果是递归验证则输出递归对象里的错误
			        return  new BusinessServiceErrorResponse( "hao.custom", "USER_COUSTOM_ERROR", null,list.get(0).getCauses());
			else
				    return  new BusinessServiceErrorResponse( "hao.custom", "USER_COUSTOM_ERROR", null,list.get(0).getMessage());*/
		}
		return null;
	}
	
	/**
	 * 实现递归验证参数验证，传入值cvList需保证不为空
	 */
	private static void validate(ConstraintViolation[] cvList, StringBuilder sb) {
		for (ConstraintViolation cv : cvList) {
			 //如果是递归验证则输出递归对象里的错误
			ConstraintViolation[] chlidCvList = cv.getCauses();
			if (chlidCvList != null && chlidCvList.length > 0) {
				validate(chlidCvList, sb);
			} else {
				sb.append("[").append(cv.getMessage()).append("]");
			}
		}
	}

}
