package com.neuedu.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.exception.DbException;
import com.neuedu.pojo.ResponseBean;
import com.neuedu.pojo.User;
import com.neuedu.service.IUserService;
import com.neuedu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class GetUserByIdServlet
 */
@WebServlet("/getUserById")
public class GetUserByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		IUserService service=new UserServiceImpl();
		User db_user=null;
		try {
			db_user=service.getUserById(Integer.parseInt(id));
			
		} catch (NumberFormatException | DbException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseBean resp=new ResponseBean();
		resp.setCode("0");
		resp.setMsg("success");
		List<User>  userList=new ArrayList();
		userList.add(db_user);
		resp.setList(userList);
		ObjectMapper om=new ObjectMapper();
		String json=om.writeValueAsString(resp);
		response.getWriter().print(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
