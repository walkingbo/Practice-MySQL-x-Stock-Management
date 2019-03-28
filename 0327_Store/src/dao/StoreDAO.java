package dao;

import java.util.List;


import domain.Store;

public interface StoreDAO {
	public int insertStore(Store store);
	public List<Store> getAllStore();
	public List<Store> getNameStore(String productname);
	public int updateStore(Store store);
	public int deleteStore(String barcode);
}
