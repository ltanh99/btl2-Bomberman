package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.entities.tile.Wall;

public class AIMedium extends AI {
	Bomber _bomber;
	Enemy _e;
	
	public AIMedium(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}
        @Override
	public int calculateDirection() {
		
		
		if((_bomber.getXTile() < _e.getXTile())&&(_bomber.getYTile() == _e.getYTile()))
			return 3;
		else if((_bomber.getXTile() > _e.getXTile())&&( _bomber.getYTile() == _e.getYTile()))
			return 1;
		
                else return rd.nextInt(4);
	}
	
	protected int calculateRowDirection() {
		if((_bomber.getYTile() < _e.getYTile())&&(_bomber.getXTile() == _e.getXTile()))
			return 0;
		else if((_bomber.getYTile() > _e.getYTile())&&(_bomber.getXTile()== _e.getXTile()))
			return 2;
		else return rd.nextInt(4);
	}
        
}