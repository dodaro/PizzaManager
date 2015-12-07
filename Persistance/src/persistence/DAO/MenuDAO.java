package persistence.DAO;

import java.util.List;

import persistence.Menu;

public interface MenuDAO {

	public void create(Menu menu);

	public void delete(Menu menu);

	public void update(Menu menu);

	public List<Menu> get();
}
