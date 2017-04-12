package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.H2DBtest;
import dao.LoginDao;
import main.Main;

public class LoginFrame extends JFrame implements ActionListener {
	BufferedImage img = null;
	JTextField loginTextField;
	JPasswordField passwordField;
	JButton bt;
	Main main;
	LoginDao logindao;

	// 메인
	public static void main(String[] args) throws Exception {
		LoginFrame loginframe = new LoginFrame();

		// 메모리DB초기화
		H2DBtest hdbtest = new H2DBtest();
		hdbtest.initDatabase(args);

		// 사용자 넣기
		LoginDao logindao = new LoginDao();
		logindao.insertUser();

		loginframe.setDaoLogin(logindao);

	}

	public void setDaoLogin(LoginDao logindao) {
		this.logindao = logindao;

	}

	// 생성자
	public LoginFrame() {
		setTitle("로그인 테스트");
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);

		// 이미지 받아오기
		try {
			img = ImageIO.read(new File("img/login.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		// 레이아웃 설정
		JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지처럼 처리해주는 클래스
		layeredPane.setBounds(0, 0, 1600, 900);
		layeredPane.setLayout(null);

		// 패널1
		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1600, 900);
		// 로그인 필드
		loginTextField = new JTextField(15);
		loginTextField.setBounds(731, 399, 280, 30);
		loginTextField.setOpaque(false); // 투명처리
		loginTextField.setForeground(Color.green); // 전경색 초록
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // 테두리
																					// 투명처리
		layeredPane.add(loginTextField);

		// 패스워드
		passwordField = new JPasswordField(15);
		passwordField.setBounds(731, 529, 280, 30);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.green);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		layeredPane.add(passwordField);

		// 로그인버튼 추가
		bt = new JButton(new ImageIcon("img/btLogin_hud.png"));
		bt.setBounds(746, 620, 109, 129);
		// bt.addActionListener(this); //액션걸림

		// 버튼 투명처리
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false);
		bt.addActionListener(this);
		layeredPane.add(bt);

		// 마지막 추가들
		layeredPane.add(panel);

		add(layeredPane);
		setVisible(true);
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String id = loginTextField.getText();
		char[] pass = passwordField.getPassword();
		String password = new String(pass);

		if (id.equals("") || password.equals("")) {
			// 메시지를 날린다.
			JOptionPane.showMessageDialog(null, "빈칸이 있네요");
		} else {

			// 로그인 참 거짓 여부를 판단
			boolean existLogin = LoginDao.loginCheck(id, password);
			if (existLogin) {
				// 로그인 성공일 경우
				JOptionPane.showMessageDialog(null, "로그인 성공");
				System.out.println("로그인 성공!");//TODO main에게 이거는 닫아주고, 새로운 배니지 뷰 화면 열어줘 요청
				
				try {
					main.showManageview(this);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				// 로그인 실패일 경우
				JOptionPane.showMessageDialog(null, "로그인 실패");

			}

		}
		password = null;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
