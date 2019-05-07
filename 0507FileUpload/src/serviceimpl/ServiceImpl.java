package serviceimpl;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import service.DAO;
import service.Service;
import util.SharedInstance;

//DAO를 주입 받아야하고 트랜잭션 처리를 여기서 수행
public class ServiceImpl implements Service {
	private DAO dao;
	private Connection con; //스프링 프로젝트 같은 곳에서는 어노테이션으로 처리
	private static Service service;
	
	private ServiceImpl() {
		dao = DAOImpl.getInstance();
		con = SharedInstance.getInstance().con;
		
	}
	
	public static Service getInstance() {
		if(service == null) {
			service = new ServiceImpl();
		}
		return service;
	}

	@Override
	public Map<String, Object> fileUpload(HttpServletRequest request) {
		Map<String,Object> map = null;
		try {
			//파라미터를 찾아올 때는 요청 파일을 확인
			//writer 파라미터 값 가져오기
			String writer = request.getParameter("writer");
			//업로드 하는 파일 찾아오기
			Part part = request.getPart("file");
			//파일 이름을 찾아오기 위한 작업
			//header에서 content-disposition 값 가져오기
			String contentDisposition = part.getHeader("content-disposition");
			//위의 값을 출력해보면 파일 이름과 경로 및 기타 정보가 포함되어 있습니다.
			//System.out.println(contentDisposition);
			//form-data; name="file"; filename="조보아.jpg"
			
			
			//;으로 분할하고 세번째 부분에서 ""사이의 문자열 가져오기
			String [] sp = contentDisposition.split(";");
			//첫번째와 두번째 "의 위치 찾기
			int sIdx = sp[2].indexOf("\"");
			int lIdx = sp[2].lastIndexOf("\"");
			//파일 이름 가져오기
			//범위를 설정할 때 프로그래머가 작성하는 언어는 뒤 부분은 -1
			//일반적인 유저가 작성하는 언어는 뒤 부분까지(Visual, Basic, R, Python 데이터분석)
			String filename = sp[2].substring(sIdx+1, lIdx);
			//실제 업로드 될 파일 이름 만들기 - 파일 이름 중복을 방지하기 위해서 
			//확장자 만들기
			String ext = filename.split("\\.")[1];
			
			//업로드할 파일 이름 만들기
			String uploadfilename="";
			while(true) {
				uploadfilename = UUID.randomUUID().toString() + "." + ext;
				//중복검사
				File f = new File("/Users/a503_18/Documents/" + uploadfilename);
				//파일이 중복되지 않으면 파일을 업로드
				if(f.exists() == false) {
					part.write(uploadfilename);
					break;
				}
			}
			map = new HashMap<String,Object>();
			map.put("writer", writer);
			map.put("filename", uploadfilename);
			
			
			
		}catch(Exception e) {
			System.out.println("파일업로드 서비스:" + e.getMessage());
			e.printStackTrace();
		}
		
		return map;
	}
}
