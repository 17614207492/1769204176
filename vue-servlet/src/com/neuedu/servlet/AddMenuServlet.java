package com.neuedu.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.exception.DbException;
import com.neuedu.pojo.ResponseBean;
import com.neuedu.pojo.SysMenu;
import com.neuedu.service.IMenuService;
import com.neuedu.service.impl.MenuServiceImpl;

/**
 * Servlet implementation class AddMenuServlet
 */
@WebServlet("/addMenu")
public class AddMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String parentId=request.getParameter("parentId");
		    String menuName=request.getParameter("menuName");
		    String orderNum=request.getParameter("orderNum");
		    String path=request.getParameter("path");
		    String icon=request.getParameter("icon");
		    String visible=request.getParameter("visible");
		    String parentName=request.getParameter("parentName");
		    SysMenu sm=new SysMenu();
		    sm.setIcon(icon);
		    sm.setParentName(parentName);
		    sm.setMenuName(menuName);
		    sm.setOrderNum(orderNum);
		    sm.setParentId(Integer.parseInt(parentId));
		    sm.setPath(path);
		    sm.setVisible(visible);
		    IMenuService ms=new MenuServiceImpl();
		    int result=0;

		        try {
					result = ms.save(sm);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    ResponseBean rb=new ResponseBean();
		    if(result>0){
		        rb.setCode("0");
		        rb.setMsg("Ìí¼Ó³É¹¦");
		    }
		    ObjectMapper om=new ObjectMapper();
		    String json=om.writeValueAsString(rb);
		    response.getWriter().write(json);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
