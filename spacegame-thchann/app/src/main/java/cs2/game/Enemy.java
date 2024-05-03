package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Enemy extends Sprite {
  public boolean isRight = true, isUp = true;
  private Image bulletPicture;

  /*
  //This constructor should initialize all fields
  //**Remember that some fields are inherited from Sprite
    */
  public Enemy(Image avatar, Image bulletPic, Vec2 p) { 
    super(avatar, p, new Vec2(40,70));
    this.bulletPicture = bulletPic;
  }


  /*
  // This method should create a new Bullet object and return it
  // The Bullet should be initialized with the bulletPicture, the
  // current position of the enemy, and a velocity going down the screen
    */
  public Bullet shoot() {
    return new Bullet(bulletPicture, new Vec2(pos.getX() + 20, pos.getY() + 20), new Vec2(0,10));
   }

  public void move() {

    if(pos.getX() > 700){
      isRight = false;
    } else if (pos.getX() < 50){
      isRight = true;
    }

    if(pos.getY() < 50){
      isUp = false;
    } else if (pos.getY() > 500){
      isUp = true;
    }

    if(isRight && isUp){
      pos = new Vec2(pos.getX() + 3, pos.getY() - 1);
    } else if (!isRight && !isUp) {
      pos = new Vec2(pos.getX() - 3, pos.getY() + 1);
    } else if (!isRight && isUp){
      pos = new Vec2(pos.getX() - 3, pos.getY() - 1);
    } else {
      pos = new Vec2(pos.getX() + 3, pos.getY() + 1);
    }



   }


}

