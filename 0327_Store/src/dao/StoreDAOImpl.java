package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import domain.Store;

public class StoreDAOImpl implements StoreDAO {
	private StoreDAOImpl( ) {}
	private static StoreDAO dao;
	public static StoreDAO getInstance() {
		if(dao==null) {
			dao = new StoreDAOImpl();
		}
		return dao;
	}
	
	@Override
	public int insertStore(Store store) {
		int result = -1;
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement("insert into store(barcode, productname, price, amount) values(?,?,?,?)");){
			
			pstmt.setString(1, store.getBarcode());
			pstmt.setString(2, store.getProductname());
			pstmt.setInt(3, store.getPrice());
			pstmt.setInt(4, store.getAmount());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.printf("삽입 예외메세지: %s\n", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Store> getAllStore() {
		List<Store> list = new ArrayList<Store>();
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement("select * from store");){
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Store store = new Store();
				store.setBarcode(rs.getString("barcode"));
				store.setProductname(rs.getString("productname"));
				store.setPrice(rs.getInt("price"));
				store.setAmount(rs.getInt("Amount"));
				
				list.add(store);
			}
			
		}catch(Exception e) {
			//예외 메세지를 출력
			System.out.printf("삽입 예외메세지: %s\n", e.getMessage());
			//예외가 발생한 곳 까지의 코드를 역 추적
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Store> getNameStore(String productname) {
		List<Store> list = new ArrayList<Store>();
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement("select * from store where productname like ?");){
			
			pstmt.setString(1, "%"+productname+"%");
			
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Store store = new Store();
				store.setBarcode(rs.getString("barcode"));
				store.setProductname(rs.getString("productname"));
				store.setPrice(rs.getInt("price"));
				store.setAmount(rs.getInt("Amount"));
				
				list.add(store);
			}
			
		}catch(Exception e) {
			//예외 메세지를 출력
			System.out.printf("출 예외메세지: %s\n", e.getMessage());
			//예외가 발생한 곳 까지의 코드를 역 추적
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateStore(Store store) {
		int result = -1;
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement("update store set productname=?, price=?, amount=? where barcode=?");){
			
		
			pstmt.setString(1, store.getProductname());
			pstmt.setInt(2, store.getPrice());
			pstmt.setInt(3, store.getAmount());
			pstmt.setString(4, store.getBarcode());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.printf("수정 예외메세지: %s\n", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteStore(String barcode) {
int result = -1;
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement("delete from store where barcode = ?");){

			pstmt.setString(1, barcode);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.printf("삭제 예외메세지: %s\n", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
