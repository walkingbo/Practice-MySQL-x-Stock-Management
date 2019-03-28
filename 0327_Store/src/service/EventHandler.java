package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import dao.StoreDAO;
import dao.StoreDAOImpl;
import domain.Store;
import view.MainUI;

public class EventHandler implements ActionListener, java.awt.event.KeyListener {
	private MainUI ui;
	//DAO 메소드 호출을 위한 참조형 변수
	private StoreDAO dao;
	
	public EventHandler(MainUI ui) {
		//ui는 넘겨받았고(Dependency Injection - 의존성 주입)
		//dao는 직접생성
		this.ui=ui;
		dao = StoreDAOImpl.getInstance();
		
		//전체 데이터를 가져오기
		ui.list=dao.getAllStore();
		//인덱스는 0으로 초기화
		ui.idx =0;
		//데이터를 출력
		ui.display();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand().trim()) {
		case "이전":
			if(ui.idx == 0) {
				JOptionPane.showMessageDialog(null, "첫번째 데이터 입니다");
				return;
			}
			ui.idx = ui.idx-1;
			ui.display();
			break;
		
		case "다음":
			if(ui.idx == ui.list.size()-1) {
				JOptionPane.showMessageDialog(null, "마지막 데이터 입니다");
			}
			ui.idx = ui.idx+1;
			ui.display();
			break;
			
		case "처음":
			ui.idx = 0;
			ui.display();
			break;
			
		case "마지막":
			ui.idx = ui.list.size()-1;
			ui.display();
			break;
			
case "삽입":
	
	if(ui.btnClear.getText().equals("지움")) {
		 JOptionPane.showMessageDialog(null, "지우고 입력하세요");
		 return;
	}
	// 입력한 내용을 가져오기
	String productname = ui.txtProductname.getText().trim();
	String price = ui.txtPrice.getText().trim();
	String amount = ui.txtAmount.getText().trim();
	String barcode = ui.txtBarcode.getText().trim();
 
			
	if(productname.length()==0) {
		JOptionPane.showMessageDialog(null, "제품명은 필수 입력입니다.");
		ui.txtProductname.setFocusable(true);
		return;
	}
	if(barcode.length()==0) {
		JOptionPane.showMessageDialog(null, "바코드는 필수 입력입니다.");
		ui.txtBarcode.setFocusable(true);
		return;
	}
	if(price.length()==0) {
		JOptionPane.showMessageDialog(null, "가격은 필수 입력입니다.");
		ui.txtPrice.setFocusable(true);
		return;
	}
	
	Store store = new Store();
	store.setProductname(productname);
	store.setPrice(Integer.parseInt(price));
	store.setAmount(Integer.parseInt(amount));
	store.setBarcode(barcode);
	
	int r = dao.insertStore(store);
	if(r > 0) {
		JOptionPane.showMessageDialog(null, "삽입성공");
		//전체 데이터를 가져오고 다시 출력
		ui.list = dao.getAllStore();
		ui.idx = 0;
		ui.display();
		
		//버튼 상태 초기 상태로 되돌리
		ui.btnClear.setText("지움");
		ui.btnUpdate.setEnabled(true);
		ui.btnDelete.setEnabled(true);
		ui.btnSearch.setEnabled(true);
		
		
	}
	
	
	break;
	
		case "수정":
			// 입력한 내용을 가져오기-공백을 제거하고 가져오
			String productname1 = ui.txtProductname.getText().trim();
			String price1 = ui.txtPrice.getText().trim();
			String amount1 = ui.txtAmount.getText().trim();
			String barcode1 = ui.txtBarcode.getText().trim();
						//필수 입력 검사
						if(productname1.length()<1 || price1.length() <1 || barcode1.length()<1) {
							JOptionPane.showMessageDialog(null, "이름이나 이메일 또는 전화번호는 필수 입력입니다.");
							return;
						}
						//바코드 10 자리
						if(barcode1.length() > 10 || barcode1.length() < 1) {
							JOptionPane.showMessageDialog(null, "바코드는 숫자 10자리 입니다.\n ex-1000000001");
							return;
						}
						//바코드는 모두 숫자로 구성
						for(int i =0;i<barcode1.length();i=i+1) {
							char ch = barcode1.charAt(i);
							if(ch<'0' || ch>'9') {
								JOptionPane.showMessageDialog(null, "바코드는 숫자로만 입력해야합니다.");
								return;
							}
						}
						
						//가격은 모두 숫자로 구성
						for(int i =0;i<price1.length();i=i+1) {
							char ch = price1.charAt(i);
							if(ch<'0' || ch>'9') {
								JOptionPane.showMessageDialog(null, "가격 숫자로만 입력해야합니다.");
								return;
							}
						}
						
						//버튼 선택 여부를 묻는 대화상자를 생성
						int re = JOptionPane.showConfirmDialog(null, "데이터수정","정말로수정하시겠습니까",JOptionPane.YES_NO_OPTION);
						if(re == JOptionPane.YES_OPTION) {
							
							//현재 출력중인 데이터의 사원번호 찾아오기
						String updateBarcode = ui.list.get(ui.idx).getBarcode();
						
						Store store1 = new Store();
						store1.setBarcode(updateBarcode);
						store1.setPrice(Integer.parseInt(price1));
						store1.setAmount(Integer.parseInt(amount1));
						store1.setProductname(productname1);
						
						int update = dao.updateStore(store1);
						if(update>=0) {
							JOptionPane.showMessageDialog(null, "수정성공");
							ui.list = dao.getAllStore();
							ui.display();
						}else {
							JOptionPane.showMessageDialog(null, "수정실패");
							ui.display();
						}		
						
						}
						break;
			
		
		case "삭제":
			
			//정말로 지울 것인지 확인
			int deleteInt = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
			if(deleteInt == JOptionPane.YES_OPTION) {
				//현재 출력 중인 데이터의 사원번호를 찾아 옵니다.
				String Barcode = ui.list.get(ui.idx).getBarcode();
				//데이터 베이스 에서 삭
				int deleteResult = dao.deleteStore(Barcode);
				if(deleteResult>0) {
					JOptionPane.showMessageDialog(null, "삭제 성공");
					//이전에는 데이터베이스에서 데이터를 다시 불러옴
					//ui.list=dao.getAllSawon
					
					//ui.list에서 삭제
					ui.list.remove(ui.idx);
					
					if(ui.idx > ui.list.size()-1) {
						ui.idx =ui.idx-1;
					}
					ui.display();
				}
				
			}
	
			break;
		case "조회":
			
			String word =JOptionPane.showInputDialog("검색할 이름이나 전화번호를 입력하세요");
			if(word != null) {
				//단어를 이용해서 검색
				ui.list = dao.getNameStore(word);
				ui.idx=0;
				ui.display();
			}
	
			break;
		
		case "지움":
			//모든 텍스트 필드의 내용을 빈칸으로 만들
			ui.txtProductname.setText("");
			ui.txtBarcode.setText("");
			ui.txtPrice.setText("");
			ui.txtAmount.setText("");
			
			//삽입 상태가 되도록 삽입 이외의 버튼을 전부 비활성화
			ui.btnUpdate.setEnabled(false);
			ui.btnDelete.setEnabled(false);
			ui.btnSearch.setEnabled(false);
			
			//지움 버튼의 타이틀을 이전상태로 변경
			ImageIcon icon = new ImageIcon("/Users/a503_18/Downloads/I1tO18H6a3gYtH_26SBghwdJ0Oxs.jpg");
			ui.btnClear.setIcon(icon);
			ui.btnClear.setText("이전상태");
			
			break;	
			
			
		
			
		case "이전상태":
			ui.btnUpdate.setEnabled(true);
			ui.btnDelete.setEnabled(true);
			ui.btnSearch.setEnabled(true);
			ui.btnClear.setText("지움");
			ui.display();
			
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
if(e.getKeyCode() == KeyEvent.VK_F5) {
			
			ui.list =dao.getAllStore();
			ui.idx =0;
			ui.display();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
