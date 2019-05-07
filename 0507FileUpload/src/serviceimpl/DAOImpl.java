package serviceimpl;

import java.sql.Connection;

import service.DAO;
import util.SharedInstance;

public class DAOImpl implements DAO {
	//싱글톤을 전용해야 하고 데이터베이스 사용 객체가 주입되어야 합니다.
	//싱글톤을 만들 때 변수는 인터페이스가 있는 경우는 인터페이스로 변수를 만드는게 
	//일반적입니다. 
	
	private Connection con;
	private DAOImpl() {
		con = SharedInstance.getInstance().con;
	}
	private static DAO dao;
	public static DAO getInstance() {
		if(dao == null) {
			dao = new DAOImpl();
		}
		return dao;
	}
}
