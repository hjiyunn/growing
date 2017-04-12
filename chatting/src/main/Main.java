package main;

import dao.H2DBtest;
import dao.LoginDao;
import view.LoginFrame;
import view.ManageView;

public class Main {
	private ManageView frameManage;
	private H2DBtest h2dbtest;
	private LoginDao logindao;
	private static LoginFrame loginframe;
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.loginframe = new LoginFrame();
		Main.loginframe.setMain(main);
		

		// 메모리DB초기화(테이블 만듦)
		H2DBtest hdbtest = new H2DBtest();
		hdbtest.initDatabase(args);

		// login을 위한 데이터 액세스 오브젝트 만들고, 사용자 입력
		LoginDao logindao = new LoginDao();
		logindao.insertUser();
		
		//프레임에 dao 주입
		loginframe.setDaoLogin(logindao);
		
		
		
	}
	
	public void showManageview(LoginFrame loginframe) throws Exception{
		loginframe.dispose();
		new ManageView();
		System.out.println("ManageView Open~");
		
	}

}
