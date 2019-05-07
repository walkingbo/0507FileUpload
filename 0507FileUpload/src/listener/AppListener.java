package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
	
	//생성자
    public AppListener() {
        
    }
	//웹 애플리케이션이 종료될 때 호출되는 메소드
    public void contextDestroyed(ServletContextEvent arg0)  { 
         
    }

	//웹 애플리케이션이 시작될 때 호출되는 메소드
    public void contextInitialized(ServletContextEvent arg0)  { 
         
    }
	
}
