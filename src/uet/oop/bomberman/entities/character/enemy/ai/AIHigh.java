/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.level.FileLevelLoader;

/**
 *
 * @author Admin
 */
public class AIHigh extends AI{
        Bomber _bomber;
	Enemy _e;
	
	public AIHigh(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đư�?ng đi
//                char[][] _map1 = new char[13][31];
                
//                _map1 = FileLevelLoader.getMap();
		if(_bomber == null) return random.nextInt(4);
                int vertical = random.nextInt(2);
		
		if(vertical == 1) {
			int v = calculateRowDirection();
			if(v != -1)
				return v;
			else
				return calculateColDirection();
			
		} else {
			int h = calculateColDirection();
			
			if(h != -1)
				return h;
			else
				return calculateRowDirection();
		}
		
	}
        protected int calculateColDirection() {
		if(_bomber.getXTile() < _e.getXTile())
			return 3;
		else if(_bomber.getXTile() > _e.getXTile())
			return 1;
		
		return -1;
	}
	
	protected int calculateRowDirection() {
		if(_bomber.getYTile() < _e.getYTile())
			return 0;
		else if(_bomber.getYTile() > _e.getYTile())
			return 2;
		return -1;
	}
        protected int checkXEntity(Entity e)
        {
            if (e instanceof Wall)
            {
                if(_bomber.getXTile() < _e.getXTile())
			return 1;
		else if(_bomber.getXTile() > _e.getXTile())
			return 3;
            }
            if (e instanceof Brick)
            {
                if(_bomber.getXTile() < _e.getXTile())
			return 1;
		else if(_bomber.getXTile() > _e.getXTile())
			return 3;
            }
            return -1;
        }
        protected int checkYEntity(Entity e)
        {
            if (e instanceof Wall)
            {
                if(_bomber.getYTile() < _e.getYTile())
			return 2;
		else if(_bomber.getYTile() > _e.getYTile())
			return 0;
            }
            if (e instanceof Brick)
            {
                if(_bomber.getYTile() < _e.getYTile())
			return 2;
		else if(_bomber.getYTile() > _e.getYTile())
			return 0;
            }
            return -1;
        }
}
