/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities.tile.item;

import java.util.Random;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Bomber2;
import uet.oop.bomberman.graphics.Sprite;

/**
 *
 * @author Admin
 */
public class RandomItem extends Item {

    public RandomItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý Bomber ăn Item
        if (e instanceof Bomber) {

            ((Bomber) e).addPowerup(this);
            remove();
            return true;
        }
        if (e instanceof Bomber2) {

            ((Bomber2) e).addPowerup(this);
            remove();
            return true;
        }
        return true;
    }

    @Override
    public void setValues() {
        _active = true;
        Random rand = new Random();
        Game.randomItem = rand.nextInt(4) + 1;
        if (Game.randomItem == 1) {
            Game.bomberCollideFlame = true;
        } else {
            if (Game.randomItem == 2) {
                Game.addBombRadius(1);
            } else {
                if (Game.randomItem == 3) {
                    Game.addBomberSpeed(0.1);
                } else {
                    if (Game.randomItem == 4) {
                        Game.bomberCollideBrick = true;
                    } else {
                        if (Game.randomItem == 5) {
                            Game.addBombRate(1);
                        }
                    }
                }
            }
        }

    }
    @Override
    public void setValues2() {
        _active = true;
        Random rand = new Random();
        Game.randomItem = rand.nextInt(4) + 1;
        if (Game.randomItem == 1) {
            Game.bomberCollideFlame2 = true;
        } else {
            if (Game.randomItem == 2) {
                Game.addBombRadius2(1);
            } else {
                if (Game.randomItem == 3) {
                    Game.addBomberSpeed2(0.5);
                } else {
                    if (Game.randomItem == 4) {
                        Game.bomberCollideBrick2 = true;
                    } else {
                        if (Game.randomItem == 5) {
                            Game.addBombRate2(1);
                        }
                    }
                }
            }
        }
    }
}
