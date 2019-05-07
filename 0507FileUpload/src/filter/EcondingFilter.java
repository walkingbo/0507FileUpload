package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/*")
public class EcondingFilter implements Filter {

    //생성자
    public EcondingFilter() {
            }
    //소멸될 때 사용하는 메소드
	public void destroy() {
		
	}
	//요청을 처리하기 전이나 후에 호출되는 메소드
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//파라미터 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//다음 요청을 처리하기 위해서 호출되는 메소드
		chain.doFilter(request, response);
	}
	//초기화 메소드
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
