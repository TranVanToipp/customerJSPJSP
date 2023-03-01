<label for="username">Tên đăng nhập:</label>
<input type="text" id="username" name="username" value="${param.username}">


req.getParameter("lblCustomerID");

package fjs.cs.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fjs.cs.common.Constants;
import fjs.cs.dao.T002Dao;
import fjs.cs.dto.T002Dto;

/**
 * Servlet implementation class T002
 */
@WebServlet("/T002")
public class T002 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public T002() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String indexPage = request.getParameter("index");
		if (indexPage == null) {
			indexPage = "1";
		}
		
		int index = Integer.parseInt(indexPage);
		request.setAttribute("tag", index);
		T002Dao dao = new T002Dao();
		
		
		//get data search
		List<T002Dto> list = dao.getData();
		
		request.setAttribute("listData", list);
		//page 
		int count = dao.getDataPage();
		//trang cuoi cùng
		int endpage = count/3;
		if (count % 3 != 0) {
			endpage++;
		}
		
		List<T002Dto> listPage = dao.pagingData(index);
		request.setAttribute("ListA", listPage);
		request.setAttribute("endP", endpage);
		request.getRequestDispatcher(Constants.T002_SEARCH);
		
		
				//request.getRequestDispatcher("/WEB-INF/jsp/T002.jsp").forward(request, response);
		RequestDispatcher myRD = null;
		// Hien thi man hinh Login
		myRD = request.getRequestDispatcher(Constants.T002_SEARCH);
		myRD.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			String name = request.getParameter("txtCustomerName");
			String sex = request.getParameter("browser");
			String birthdayFrom = request.getParameter("txtBirthdayFromName");
			String birthdayTo = request.getParameter("txtBirthdayToName");
			T002Dao daoSearch = new T002Dao();
			
			String[] ids = request.getParameterValues("selectedValues");
			if (ids != null && ids.length > 0) {
			    String idList = String.join(",", ids);
			   
			    // Loại bỏ các dấu ngoặc kép trong chuỗi
			    idList = idList.replace("[\"", "").replace("\"]", "");
			    String result = idList.replaceAll("[^\\d,]", "").replace("\"", "");
			    String[] result2 = result.split(",");
			    for (int i = 0;i< result2.length; i++) {	
			    	if (result2[i] == "") {
			    	}else {			    		
			    		daoSearch.deleteData(result2);
			    	}
			    }
			   
			}
			List<T002Dto> resultSearch = daoSearch.getDataSearch(name, sex, birthdayFrom, birthdayTo);
			request.setAttribute("listDataSearch", resultSearch);
			
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/T002.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * INSERT INTO mstcustomer (Customer_Id, Customer_Name, Sex, Birthday, Email, Address, Delete_YMD, Insert_Ymd, Insert_Psn_Cd, Update_YMD, Update_Psn_Cd) 
	   VALUES (SEQ_CUSTOMER_ID.NEXTVAL, 'Customer_Name_Value', 'Sex_Value', 'Birthday_Value', 'Email_Value', 'Address_Value', NULL, CURRENT_TIMESTAMP, 'Insert_Psn_Cd_Value', CURRENT_TIMESTAMP, 'Update_Psn_Cd_Value');


public static int save(T003Dto save) throws SQLException {
		int status = 0;
		Connection conn = null;
		try {
			String query = "";
			conn = new DBConnection().getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
	
	
	
	public static int save(T003Dto save) throws SQLException {
int status = 0;
Connection conn = null;
try {
String query = "INSERT INTO mstcustomer (Customer_Id, Customer_Name, Sex, Birthday, Email, Address, Delete_YMD, Insert_Ymd, Insert_Psn_Cd, Update_YMD, Update_Psn_Cd) VALUES (SEQ_CUSTOMER_ID.NEXTVAL, ?, ?, ?, ?, ?, NULL, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP, ?)";
conn = new DBConnection().getConnection();
PreparedStatement ps = conn.prepareStatement(query);
ps.setString(1, save.getCUSTOMER_NAME());
ps.setString(2, save.getSEX());
ps.setString(3, save.getBIRTHDAY());
ps.setString(4, save.getEMAIL());
ps.setString(5, save.getADDRESS());
ps.setString(6, save.getINSERT_PSN_CD());
ps.setString(7, save.getUPDATE_PSN_CD());
status = ps.executeUpdate();
} catch (Exception e) {
e.printStackTrace();
} finally {
if (conn != null) {
conn.close();
}
}
return status;
}
	 */
	
	/**
		 * UPDATE mstcustomer
	SET Customer_Name = [giá trị được input từ màn hình],
	Sex = [giá trị được input từ màn hình],
	Birthday = [giá trị được input từ màn hình],
	Email = [giá trị được input từ màn hình],
	Address = [giá trị được input từ màn hình],
	Delete_YMD = null,
	Update_YMD = [Current_TimeStamp],
	Update_Psn_Cd = [Psn_Cd của user đã được đăng nhập]
	WHERE Customer_Id = [giá trị của Customer_Id đang hiển thị tại <Customer_Id>]
	 */


}
