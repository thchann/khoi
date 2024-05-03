package cs2.game;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Sprite {
  private Image bulletPicture;
  public boolean canShoot = true;
  private Vec2 originalPosition;

  //public Image image = new Image("/users/gbelaund/Pictures/Player.png");

  /*
  //This constructor should initialize all fields
  //**Remember that some fields are inherited from Sprite
  */
  public Player(Image avatar, Image bullPic, Vec2 p, Vec2 size) {
    super(avatar, p, size);
    this.bulletPicture = bullPic;
    originalPosition = p;
   }

  /*
  // This method should create a new Bullet object and return it
  // The Bullet should be initialized with the bulletPicture, the
  // current position of the player, and a velocity going up the screen
    */

  public Bullet shoot() {
    Vec2 clonedPos = new Vec2(pos.getX() + 20, pos.getY() - 5);
    return new Bullet(bulletPicture, clonedPos, new Vec2(0,-30));
  }

  /*
  // This method should move the player left by some amount
  */
  public void moveLeft() { 
    if(pos.getX() >= 30){
      pos = new Vec2(pos.getX() - 10, pos.getY());
    }
  }

  /*
  // This method should move the player right by some amount
  */
  public void moveRight() { 
    if(pos.getX() <= 720){
      pos = new Vec2(pos.getX() + 10, pos.getY());
    }
  }

  public void moveUp(){
    if(pos.getY() >= 50){
      pos = new Vec2(pos.getX(), pos.getY() - 10);
    }
  }

  public void moveDown(){
    if(pos.getY() <= 700){
      pos = new Vec2(pos.getX(), pos.getY() + 10);
    }
  }

  public void reset(){
    pos = new Vec2(originalPosition.getX(), originalPosition.getY());

  }

}