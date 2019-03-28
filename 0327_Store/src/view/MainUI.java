package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import domain.Store;
import service.EventHandler;

public class MainUI extends JFrame {
	
	//테이블4개
		public JLabel lblBarcode, lblProductname, lblPrice, lblAmount;
		public JLabel lblIdx;
		
		//입력받을 텍스트 필드 4개
		public JTextField txtBarcode, txtProductname, txtPrice, txtAmount;
		
		//버튼 9개
		public JButton btnNext, btnPrev, btnLast, btnFirst;
		public JButton btnInsert, btnUpdate, btnDelete, btnSearch, btnClear;
		
		//패널 4개
		public JPanel pCenter, pSouth, pTop, pMid;
		
		public List<Store>	list;
		//현재 출력 중인 데이터의 인덱스를 저장할 변수
		public int idx;
		//현재 스트의 데이터를 화면에 출력해주는 메소드
		public void display() {
			//List로 데이터를 가져오면 데이터가 없으면 size 가 0
			if(list.size()==0) {
				JOptionPane.showMessageDialog(null, "데이터가 없습니다.");
				txtProductname.setText("");
				txtBarcode.setText("");
				txtPrice.setText("");
				txtAmount.setText("");
				lblIdx.setText("0");
			}else {
				//데이터가 있으면 idx 번째 데이터를 가져와서 출력
				Store store =list.get(idx);
				txtProductname.setText(store.getProductname());
				txtBarcode.setText(store.getBarcode());
				txtPrice.setText(store.getPrice()+"");
				txtAmount.setText(store.getAmount()+"");
				lblIdx.setText(idx + 1 + "");
			}
		}
		public MainUI() {
			
			
			pCenter = new JPanel();
			pCenter.setLayout(new BorderLayout());
			
			pTop = new JPanel();
			//5행 2열의 레이아웃 설
			pTop.setLayout(new GridLayout(4,2,4,4));
			pTop.setBorder(new TitledBorder("현재데이터"));
			
			lblProductname = new JLabel("제품이름",JLabel.RIGHT);
			pTop.add(lblProductname);
			txtProductname = new JTextField();
			pTop.add(txtProductname);
			
			lblBarcode = new JLabel("바코드번호",JLabel.RIGHT);
			pTop.add(lblBarcode);
			txtBarcode = new JTextField();
			pTop.add(txtBarcode);
			
			lblPrice = new JLabel("가격",JLabel.RIGHT);
			pTop.add(lblPrice);
			txtPrice = new JTextField();
			pTop.add(txtPrice);
			
			lblAmount = new JLabel("재고수량",JLabel.RIGHT);
			pTop.add(lblAmount);
			txtAmount = new JTextField();
			pTop.add(txtAmount);
			
			
			pCenter.add("Center",pTop);
			
			pMid =new JPanel();
			//1행 5열
			pMid.setLayout(new GridLayout(1,5,2,2));
			pMid.setBorder(new TitledBorder("조회"));
			
			btnFirst = new JButton("처음");
			pMid.add(btnFirst);
			
			btnPrev = new JButton("이전");
			pMid.add(btnPrev);
			
			lblIdx = new JLabel("없음",JLabel.CENTER);
			pMid.add(lblIdx);
			
			btnNext = new JButton("다음");
			pMid.add(btnNext);
			
			btnLast = new JButton("마지막");
			pMid.add(btnLast);
			
			
			pCenter.add("South",pMid);
			
			this.add("Center",pCenter);
			
			
			//하단 부
			
			pSouth = new JPanel();
			pSouth.setLayout(new GridLayout(1,6,2,2));
			
			btnInsert = new JButton("삽입");
			pSouth.add(btnInsert);
			
			btnUpdate = new JButton("수정");
			pSouth.add(btnUpdate);
			
			btnDelete = new JButton("삭제");
			pSouth.add(btnDelete);
			
			btnSearch = new JButton("조회");
			pSouth.add(btnSearch);
			
			btnClear = new JButton("지움");
			pSouth.add(btnClear);
			
			
			
			pSouth.setBorder(new TitledBorder("데이터작업"));
			this.add("South",pSouth);
			
			//종료 버튼을 눌렀을 때 자바 프로그램을 종료할 수 있도록 해주는 설
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("재고관리");
			setSize(600,600);
			setLocation(200,200);
			setResizable(false);
			setVisible(true);
			
			EventHandler handler = new EventHandler(this); 
			
			btnNext.addActionListener(handler);
			btnLast.addActionListener(handler);
			btnPrev.addActionListener(handler);
			btnFirst.addActionListener(handler);
			
			btnInsert.addActionListener(handler);
			btnUpdate.addActionListener(handler);
			btnDelete.addActionListener(handler);
			btnSearch.addActionListener(handler);
			btnClear.addActionListener(handler);
			
			txtProductname.addKeyListener(handler);
			
			
		}

}
