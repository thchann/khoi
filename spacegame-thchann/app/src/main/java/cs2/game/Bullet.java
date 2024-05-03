package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Bullet extends Sprite {
  public Vec2 vel; // the velocity/speed the bullet should move

  /*
  //This constructor should initialize all fields
  //**Remember that some fields are inherited from Sprite
    */
  public Bullet(Image bul, Vec2 p, Vec2 v) { 
    super(bul, p, new Vec2(10,20));
    vel = v;
  }

  /*
  // This method should update the position of the bullet by adding
  // the velocity to the current position
    */
  public void update() {
    this.pos.addThis(this.vel);
   }

  

}