package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Doll;
import uet.oop.bomberman.entities.character.enemy.Kondoria;
import uet.oop.bomberman.entities.character.enemy.Minvo;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.character.enemy.Ovape;
import uet.oop.bomberman.entities.character.enemy.Pass;
import uet.oop.bomberman.entities.character.enemy.Pontan1;
import uet.oop.bomberman.entities.character.enemy.Pontan2;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

    /**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá
     * trị kí tự đ�?c được từ ma trận bản đồ trong tệp
     * cấu hình
     */
    private static char[][] _map;

    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) throws LoadLevelException {
        try {

            URL absPath = FileLevelLoader.class.getResource("/levels/Level" + level + ".txt");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(absPath.openStream()));

            String data = in.readLine();

            StringTokenizer tokens = new StringTokenizer(data);

            _level = Integer.parseInt(tokens.nextToken());

            _height = Integer.parseInt(tokens.nextToken());

            _width = Integer.parseInt(tokens.nextToken());

            _map = new char[getHeight()][];
            for (int y = 0; y < getHeight(); y++) {
                _map[y] = new char[getWidth()];
            }
            _lineTiles = new String[getHeight()];

            for (int i = 0; i < getHeight(); ++i) {

                _lineTiles[i] = in.readLine().substring(0, getWidth());

            }

            in.close();

        } catch (IOException e) {

            throw new LoadLevelException("Error loading level " + level, e);

        }

        for (int x = 0; x < getHeight(); x++) {
            for (int y = 0; y < getWidth(); y++) {

                _map[x][y] = _lineTiles[x].charAt(y);

            }
        }
    }
//	@Override
//	public void createEntities() {
//                
//               
//		for (int x = 0; x < 20; x++) {
//			for (int y = 0; y < 20; y++) {
//				int pos = x + y * _width;
//				Sprite sprite = y == 0 || x == 0 || x == 10 || y == 10 ? Sprite.wall : Sprite.grass;
//				_board.addEntity(pos, new Grass(x, y, sprite));
//			}
//		}
//
//		// thêm Bomber
//		int xBomber = 1, yBomber = 1;
//		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
//		Screen.setOffset(0, 0);
//		_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
//
//		// thêm Enemy
//		int xE = 2, yE = 1;
//		_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//		_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
//
//		// thêm Brick
//		int xB = 3, yB = 1;
//		_board.addEntity(xB + yB * _width,
//				new LayeredEntity(xB, yB,
//					new Grass(xB, yB, Sprite.grass),
//					new Brick(xB, yB, Sprite.brick)
//				)
//		);
//
//		// thêm Item kèm Brick che phủ ở trên
//		int xI = 5, yI = 5;
//		_board.addEntity(xI + yI * _width,
//				new LayeredEntity(xI, yI,
//					new Grass(xI ,yI, Sprite.grass),
//					new SpeedItem(xI, yI, Sprite.powerup_flames),
//					new Brick(xI, yI, Sprite.brick)
//				)
//		);
//	}

    @Override
    public void createEntities() {
//        
        for (int x = 0; x < getHeight(); x++) {
            for (int y = 0; y < getWidth(); y++) {
                addLevelEntity(_map[x][y], y, x);
            }
        }
//        for (int y = 0; y < getHeight(); y++) {
//
//            for (int x = 0; x < getWidth(); x++) {
//
//                addLevelEntity (_map[y][x], x, y);
//
//            }
//
//        }
    }

    public void addLevelEntity(char c, int x, int y) {
        int pos = x + y * getWidth();
        switch (c) {
            case '#':
                _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.wall)));
                break;
            case '*':
                _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.brick)));
                break;
            case 'x':
                _board.addEntity(pos, new LayeredEntity(x, y,
                        new Grass(x, y, Sprite.portal),
                        new Grass(x, y, Sprite.brick)
                ));
                break;
            case 'p':
                _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                Screen.setOffset(0, 0);
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '1':
                _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '2':
                _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '3':
                _board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '4':
                _board.addCharacter(new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '5':

                _board.addCharacter(new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '6':

                _board.addCharacter(new Ovape(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '7':

                _board.addCharacter(new Pontan1(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '8':

                _board.addCharacter(new Pontan2(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '9':

                _board.addCharacter(new Pass(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case 'b':
                _board.addEntity(pos, new LayeredEntity(x, y,
                        new Grass(x, y, Sprite.grass),
                        new SpeedItem(x, y, Sprite.powerup_bombs),
                        new Brick(x, y, Sprite.brick)
                ));
                break;
            case 'f':
                _board.addEntity(pos,
                        new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new SpeedItem(x, y, Sprite.powerup_flames),
                                new Brick(x, y, Sprite.brick)
                        ));

                break;
            case 's':
                _board.addEntity(pos,
                        new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass),
                                new SpeedItem(x, y, Sprite.powerup_speed),
                                new Brick(x, y, Sprite.brick)
                        ));
                break;
            default:
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
        }
    }

}
