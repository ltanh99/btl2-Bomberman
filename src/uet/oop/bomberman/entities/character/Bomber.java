package uet.oop.bomberman.entities.character;

import java.util.ArrayList;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.util.Iterator;
import java.util.List;
import uet.oop.bomberman.Sound_cdjv.Sound_cdjv;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.level.Coordinates;

public class Bomber extends Character {

    private List<Bomb> _bombs;
    protected Keyboard _input;
    Sound_cdjv ItemSound=new Sound_cdjv("C:\\Users\\Admin\\Documents\\NetBeansProjects\\bomberman-starter-starter-project-1\\src\\uet\\oop\\bomberman\\Sound_cdjv\\bomberman_music-master\\power03.wav");
    /**
     * nếu giá trị này < 0 thì cho phép đặt đối tượng Bomb
     * tiếp theo, cứ mỗi lần đặt 1 Bomb mới, giá trị này sẽ
     * được reset v�? 0 và giảm dần trong mỗi lần update()
     */
    protected int _timeBetweenPutBombs = 0;
    
    public static List<Item> _powerups = new ArrayList<Item>();
    
    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        _bombs = _board.getBombs();
        _input = _board.getInput();
        _sprite = Sprite.player_right;
    }

    @Override
    public void update() {
        clearBombs();
        if (!_alive) {
            afterKill();
            return;
        }

        if (_timeBetweenPutBombs < -7500) {
            _timeBetweenPutBombs = 0;
        } else {
            _timeBetweenPutBombs--;
        }

        animate();
        ItemSound.suspend();
        calculateMove();

        detectPlaceBomb();
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();

        if (_alive) {
            chooseSprite();
        } else {
            _sprite = Sprite.player_dead1;
        }

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    public void calculateXOffset() {
        int xScroll = Screen.calculateXOffset(_board, this);
        Screen.setOffset(xScroll, 0);
    }

    /**
     * Kiểm tra xem có đặt được bom hay không? nếu có thì đặt
     * bom tại vị trí hiện tại của Bomber
     */
    private void detectPlaceBomb() {
        if(_input.space && Game.getBombRate() > 0 && _timeBetweenPutBombs < 0) {

			

			int xt = Coordinates.pixelToTile(_x + _sprite.getSize() / 2);

			int yt = Coordinates.pixelToTile( (_y + _sprite.getSize() / 2) - _sprite.getSize() ); //subtract half player height and minus 1 y position

			

			placeBomb(xt,yt);

			Game.addBombRate(-1);
			_timeBetweenPutBombs = 30;

		}
    }
    protected void placeBomb(int x, int y) {
        Bomb b = new Bomb(x, y, _board);

		_board.addBomb(b);
    }
    private void clearBombs() {
        Iterator<Bomb> bs = _bombs.iterator();

        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.isRemoved()) {
                bs.remove();
                Game.addBombRate(1);
            }
        }

    }

    @Override
    public void kill() {
        if (!_alive) {
            return;
        }
        _alive = false;
    }

    @Override
    protected void afterKill() {
        if (_timeAfter > 0) {
            --_timeAfter;
        } else {
            _board.endGame();
        }
    }

    @Override
    protected void calculateMove() {
        // TODO: xử lý nhận tín hiệu đi�?u khiển hướng đi từ _input và g�?i move() để thực hiện di chuyển
        // TODO: nhớ cập nhật lại giá trị c�? _moving khi thay đổi trạng thái di chuyển
        int xa = 0, ya = 0;
        if (_input.up) {
            ya--;
        }
        if (_input.down) {
            ya++;
        }

        if (_input.left) {
            xa--;
        }

        if (_input.right) {
            xa++;
        }

        if (xa != 0 || ya != 0) {

            move(xa * Game.getBomberSpeed(), ya * Game.getBomberSpeed());

            _moving = true;

        } else {

            _moving = false;

        }
    }

    @Override
    public boolean canMove(double x, double y) {
        // TODO: kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không
        for (int i = 0; i < 4; i++) { //colision detection for each corner of the player

            double xt = ((_x + x) + i % 2 * 11) / Game.TILES_SIZE; //divide with tiles size to pass to tile coordinate

            double yt = ((_y + y) + i / 2 * 12 - 13) / Game.TILES_SIZE; //these values are the best from multiple tests
            Entity a = _board.getEntity(xt, yt, this);
            if (!a.collide(this)) {
                return false;
            }

        }
        return true;
        
    }

    @Override
    public void move(double xa, double ya) {
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi t�?a độ _x, _y
        // TODO: nhớ cập nhật giá trị _direction sau khi di chuyển
        if (xa > 0) {
            _direction = 1;//right
        }
        if (xa < 0) {
            _direction = 3;//left
        }
        if (ya > 0) {
            _direction = 2;//down
        }
        if (ya < 0) {
            _direction = 0;//up
        }
        if (canMove(0, ya)) {
            _y += ya;
        }
        if (canMove(xa, 0)) {
            _x += xa;
        }
    }
    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý va chạm với Flame
        // TODO: xử lý va chạm với Enemy
        if (e instanceof Flame) {
            if (Game.bomberCollideFlame ) {
                _alive = true;
            }
            else kill();

            return false;
        }
         if (e instanceof Enemy) {
            kill();
            return true;
        }
        return true;
    }
    
    public void addPowerup(Item p) {
		if(p.isRemoved()) return;
		
		_powerups.add(p);
		ItemSound.start();
		p.setValues();
	}
    public void clearUsedPowerups() {
		Item p;
		for (int i = 0; i < _powerups.size(); i++) {
			p = _powerups.get(i);
			if(p.isActive() == false)
				_powerups.remove(i);
		}
	}
    public void removePowerups() {
		for (int i = 0; i < _powerups.size(); i++) {
			_powerups.remove(i);
		}
	}
    private void chooseSprite() {
        switch (_direction) {
            case 0:
                _sprite = Sprite.player_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }
}
