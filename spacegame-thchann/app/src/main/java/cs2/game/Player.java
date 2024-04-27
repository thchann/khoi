package cs2.game;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Sprite {
  private Image bulletPicture;

  //public Image image = new Image("/users/gbelaund/Pictures/Player.png");

  /*
  //This constructor should initialize all fields
  //**Remember that some fields are inherited from Sprite
  */
  public Player(Image avatar, Image bullPic, Vec2 p) {
    super(avatar, p);
    this.bulletPicture = bullPic;
   }
  
  public void display(GraphicsContext g){
    g.drawImage(this.img, pos.getX(), pos.getY(), 40, 70);
  }

  /*
  // This method should create a new Bullet object and return it
  // The Bullet should be initialized with the bulletPicture, the
  // current position of the player, and a velocity going up the screen
    */

  public Bullet shoot() {
    Vec2 clonedPos = new Vec2(pos.getX() - 5, pos.getY() - 5);
    this.position = new Bullet(bulletPicture, clonedPos, new Vec2(0,-50));
    return position;
  }

  /*
  // This method should move the player left by some amount
  */
  public void moveLeft() { 
    if(pos.getX() >= 30){
      pos = new Vec2(pos.getX() - 30, pos.getY());
    }
  }

  /*
  // This method should move the player right by some amount
  */
  public void moveRight() { 
    if(pos.getX() <= 720){
      pos = new Vec2(pos.getX() + 30, pos.getY());
    }
  }

  public void moveUp(){
    if(pos.getY() >= 50){
      pos = new Vec2(pos.getX(), pos.getY() - 30);
    }
  }

  public void moveDown(){
    if(pos.getY() <= 700){
      pos = new Vec2(pos.getX(), pos.getY() + 30);
    }
  }

}
