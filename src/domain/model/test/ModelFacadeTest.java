package domain.model.test;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import domain.model.Game;
import domain.model.ModelFacade;
import domain.model.Orientation;
import domain.model.Player;
import domain.model.Ship;
import domain.model.ShipFactory;
import domain.model.ShipTemplate;

public class ModelFacadeTest {

	private ModelFacade facade;
	private String name = "Jon";
	private boolean isAi = false;
	private int x = 0;
	private int y = 0;
	private ShipTemplate type = ShipTemplate.BATTLESHIP;
	private Orientation orientation = Orientation.VERTICAL;

	@Mock
	private Ship ship;
	private Game game;
	private Player player;
	private ShipFactory shipFactory;
	
	@Before
	public void setup() throws Exception {
		initMocks(this);
		facade = new ModelFacade();
		facade.setGame(game);
	}

	@Test
	public void test_Add_Player() {
		facade.addPlayer(name, isAi);
		verify(game).addPlayer(name, isAi);
    }
	
	@Test
    public void test_Get_Player() {
		facade.addPlayer(name, isAi);
		facade.getGame().getPlayer(name);
        verify(game).getPlayer(name);
    }
    
	@Test
    public void test_Get_Players() {
		facade.addPlayer(name, isAi);
		facade.getGame().getPlayers();
        verify(game).getPlayers();
    }
	
	@Test
    public void test_Apply_Ship() {
		player.getBoard().applyShip(ship);
		verify(player).getBoard().applyShip(ship);
    }

	@Test
    public void test_Get_Ship() {
		facade.getShip(type, x, y, orientation);
		verify(shipFactory).createShipFromTemplate(type, x, y, orientation);
    }

	@Test
    public void test_Get_Cells() {
        facade.getPlayer(name).getBoard().getCells();
        verify(player).getBoard().getCells();
    }

	@Test
    public void test_Get_Current_Player() {
		facade.getCurrentPlayer();
        verify(game).getCurrentTurnPlayer();
    }

	@Test
    public void test_Next_Turn() {
		facade.nextTurn();
		verify(game).nextTurn();
    }

	@Test
	public void test_Get_Game_State() {
		facade.getGameState();
		verify(game).getGameState();
	}

	@Test
    public void test_Get_Game() {
		facade.getGame();
        verify(game);
    }

}
