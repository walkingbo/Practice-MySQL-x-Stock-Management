import java.util.List;
import dao.StoreDAO;
import dao.StoreDAOImpl;
import domain.Store;
import view.MainUI;

public class Main {

	public static void main(String[] args) {
		
		new MainUI();
		
		StoreDAO storeDAO = StoreDAOImpl.getInstance();
		
		/*
		int r =storeDAO.deleteStore("1000000002");
		
		if(r>=0) {
			System.out.printf("데이터	삭제성공\n");
		}else {
			System.out.printf("데이터 삭제실패\n");
		}
		*/
		
		/*
		Store store = new Store();
		store.setBarcode("1000000002");
		store.setProductname("가나마일드초코");
		store.setPrice(1500);
		store.setAmount(10);
		
		int r =storeDAO.updateStore(store);
		
		if(r>=0) {
			System.out.printf("데이터수정성공\n");
		}else {
			System.out.printf("데이터수정실패\n");
		}
		*/
		
		
		
		
		/*
		//전체 데이터 가져오기 테스트
		List<Store> list = storeDAO.getNameStore("런");
		//텍스트 할 때 바로 출력
		//System.out.printf("%s\n", list); -쭉 나온다.
		
		//세부 내용을 확인할 때는 빠른 열거 이용- 한줄씩 나온다.
		for(Store store : list) {
			System.out.printf("%s\n", store);
		}
		*/
		
		
		/*
				//전체 데이터 가져오기 테스트
				List<Store> list = storeDAO.getAllStore();
				//텍스트 할 때 바로 출력
				//System.out.printf("%s\n", list); -쭉 나온다.
				
				//세부 내용을 확인할 때는 빠른 열거 이용- 한줄씩 나온다.
				for(Store store : list) {
					System.out.printf("%s\n", store);
				}
		*/
		
		
		/*
		Store store = new Store();
		store.setBarcode("1000000002");
		store.setProductname("가나");
		store.setPrice(1500);
		store.setAmount(10);
		
int r =storeDAO.insertStore(store);
		
		if(r>0) {
			System.out.printf("데이터삽입성공\n");
		}else {
			System.out.printf("데이터삽입실패\n");
		}
		*/
	}

}
