package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.Sound_cdjv.Sound_cdjv;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Bomber2;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Ovape;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

public class Bomb extends AnimatedEntitiy {

    protected double _timeToExplode = 120; //2 seconds
    public int _timeAfter = 20;

    protected Board _board;
    protected Flame[] _flames = null;
    protected boolean _exploded = false;
    protected boolean _allowedToPassThru = true;
    protected boolean _allowedToPassThru2 = true;
    
    Sound_cdjv explosionSound = new Sound_cdjv("C:\\Users\\nguyen ngoc gioi\\Downloads\\btl2-Bomberman-master\\src\\uet\\oop\\bomberman\\Sound_cdjv\\bomberman_music-master\\explosion.wav");
    public Bomb(int x, int y, Board board) {
        _x = x;
        _y = y;
        _board = board;
        _sprite = Sprite.bomb;
    }

    @Override
    public void update() {
        if (_timeToExplode > 0) {
            _timeToExplode--;
        } else {
            if (!_exploded) {
                explode();
            } else {                
                updateFlames();
            }

            if (_timeAfter > 0) {
                _timeAfter--;
            } else {
                
                remove();
                explosionSound.suspend();
            }
        }

        animate();
    }

    @Override
    public void render(Screen screen) {
        if (_exploded) {
            _sprite = Sprite.bomb_exploded2;
            renderFlames(screen);
        } else {
            _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
        }

        int xt = (int) _x << 4;
        int yt = (int) _y << 4;

        screen.renderEntity(xt, yt, this);
    }

    public void renderFlames(Screen screen) {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].render(screen);
        }
    }

    public void updateFlames() {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].update();
        }
    }

    /**
     * X? l� Bomb n?
     */
    protected void explode() {
        boolean check = true;
        _exploded = true;

        _allowedToPassThru = true;

        

        Character a = _board.getCharacterAt(_x, _y);

        if (a != null) {
            
            if (a instanceof Bomber){
                if(Game.bomberCollideFlame){}
                else a.kill();
            }
            if (a instanceof Bomber2){
                if(Game.bomberCollideFlame2){}
                else a.kill();
            }
            if(!(a instanceof Bomber)&&!(a instanceof Bomber2)) a.kill();


        }
        
        _flames = new Flame[4];
        for (int i = 0; i < _flames.length; i++) {

            _flames[i] = new Flame((int) _x, (int) _y, i, Game.getBombRadius(), _board);

        }
        explosionSound.start();                         
    }
    
    public FlameSegment flameAt(int x, int y) {
        
        if (!_exploded) {
            return null;
        }
        
        for (int i = 0; i < _flames.length; i++) {
            if (_flames[i] == null) {
                return null;
            }
            FlameSegment e = _flames[i].flameSegmentAt(x, y);
            if (e != null) {
                return e;
            }
        }

        return null;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: x? l� khi Bomber ?i ra sau khi v?a ??t bom (_allowedToPassThru)
        // TODO: x? l� va ch?m v?i Flame c?a Bomb kh�c
        if (e instanceof Flame) {
            _timeToExplode = 0;
            return true;
        }
        if (e instanceof Bomber) {
           
            double diffX = e.getX() - Coordinates.tileToPixel(getX());

            double diffY = e.getY() - Coordinates.tileToPixel(getY());

            if (!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // differences to see if the player has moved out of the bomb, tested values
                
                _allowedToPassThru = false;

            }

            return _allowedToPassThru;

        }
        if (e instanceof Bomber2) {
           
            double diffX = e.getX() - Coordinates.tileToPixel(getX());

            double diffY = e.getY() - Coordinates.tileToPixel(getY());

            if (!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // differences to see if the player has moved out of the bomb, tested values
                
                _allowedToPassThru2 = false;

            }

            return _allowedToPassThru2;

        }if(e instanceof Ovape) return true;
        return false;
    }
    
}