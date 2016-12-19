package domain.model.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import domain.model.ModelFacade;
import domain.model.Orientation;
import domain.model.Player;
import domain.model.ShipTemplate;

public class ModelFacadeTest {

	private ModelFacade facade;
	private final String name = "Jon";
	private final boolean isAi = false;
	private int x = 0;
	private int y = 0;
	private final ShipTemplate type = ShipTemplate.BATTLESHIP;
	private final Orientation orientation = Orientation.VERTICAL;

	@Before
	public void setup() throws Exception {
		facade = new ModelFacade();
	}
	
	@Test
    public void test_Get_Ship() {
		assertEquals(facade.getShip(type, x, y, orientation).getShipTemplate(), type);
		assertEquals(facade.getShip(type, x, y, orientation).getX(), y);
		assertEquals(facade.getShip(type, x, y, orientation).getY(), x);
		assertEquals(facade.getShip(type, x, y, orientation).getOrientation(), orientation);
    }

	@Test
    public void test_Get_Cells() {
        facade.addPlayer(name, isAi);
        Player player = facade.getPlayer(name);
        assertEquals(facade.getCells(player), player.getBoard().getCells());
    }

}
