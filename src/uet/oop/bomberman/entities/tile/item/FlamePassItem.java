/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

/**
 *
 * @author Admin
 */
public class FlamePassItem extends Item{
    public FlamePassItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
                if(e instanceof Bomber )
                {
                    ((Bomber) e).addPowerup(this);
			remove();
			return true;
                }
		return false;
	}
        @Override
	public void setValues() {
		_active = true;
		Game.bomberCollideFlame = true;
                
	}
}
