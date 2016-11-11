package cn.ifxcode.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.ifxcode.model.User;
import cn.ifxcode.utils.RoleJudge;

public class SystemInterceptor implements HandlerInterceptor{

	private Logger logger = Logger.getLogger(SystemInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String contextPath=request.getContextPath();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
        	logger.info("session为空，还没登录，不可以执行此操作");
        	request.setAttribute("error", "你还没有登录");
        	response.sendRedirect(contextPath+"/account/login?backurl="+contextPath+"/index");
        	return false;
        } else if(!RoleJudge.roleJudge(user.getRole().getRid())){
        	logger.info("用户没有权限，不可以执行此操作");
        	request.setAttribute("error", "你没有权限");
        	response.sendRedirect(contextPath+"/index");
        	return false;
        } else{
        	logger.info("权限验证成功，执行跳转");
        	return true;
        }
	}

}
