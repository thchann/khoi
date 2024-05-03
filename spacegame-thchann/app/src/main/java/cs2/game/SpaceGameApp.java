package cs2.game;

import cs2.util.Vec2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.text.html.HTMLDocument.Iterator;

public class SpaceGameApp extends Application {

  protected static int FRAME_RATE = 1000;
  protected static int PLAYER_SHOOT_RATE = 10;
  public Set<KeyCode> codes = new HashSet<>();

  Image bullet = new Image("file:laser_beam.png", 5, 20, true, false);
  Image battleship = new Image("file:rocket.png", 10, 80, true, false);
  Image enemy_ship = new Image("file:enemy_ship.png", 30,30,true,false);
  Image heart = new Image("file:heart.png", 30,30,true,false);
  EnemySwarm enemyswarm = new EnemySwarm(3, 8, enemy_ship, bullet);
  Enemy enemy = new Enemy(enemy_ship, bullet, new Vec2(100,200));
  int counter = 0;


  public void start(Stage stage) {
    Player player = new Player(battleship, bullet, new Vec2(500,600), new Vec2(40,70));
    Player hearts1 = new Player(heart, heart, new Vec2(700, 50), new Vec2(40,30));
    Player hearts2 = new Player(heart, heart, new Vec2(640, 50), new Vec2(40,30));
    Player hearts3 = new Player(heart, heart, new Vec2(580, 50), new Vec2(40,30));
  
    ArrayList<Bullet> bullets = new ArrayList<>();
    AtomicBoolean enemyIsAlive = new AtomicBoolean(true);
    AtomicBoolean playerIsAlive = new AtomicBoolean(true);
    ArrayList<Integer> numHits = new ArrayList<Integer>();
    ArrayList<Enemy> enemyDead = new ArrayList<Enemy>();

    stage.setTitle("PROXY WARS");
    stage.show();
    //You can change the window size here if you want
    Canvas canvas = new Canvas(800,800);
    stage.setScene(new Scene(new StackPane(canvas)));
    GraphicsContext g = canvas.getGraphicsContext2D();

    AnimationTimer timer = new AnimationTimer() {

      long lastUpdateTime = 0;
      long PU = 0;
      public void handle(long now){
        long elapsedTime = (now - lastUpdateTime)/1000000;
        long playerElapsedTime = (now - PU)/1000000;

        if (elapsedTime >= FRAME_RATE) {
          lastUpdateTime = now;
          bullets.add(enemyswarm.shoot());
        }

        if (playerElapsedTime >= PLAYER_SHOOT_RATE) {
          PU = now;
          player.canShoot = true;
        }

        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 800, 800);
        player.display(g);

        if (enemyIsAlive.get()){
          enemy.display(g);
          if (elapsedTime >= FRAME_RATE){
            bullets.add(enemy.shoot());
          }
        }

        if (enemyswarm.size() == 0){
          enemy = new Enemy(enemy_ship, bullet, new Vec2(100,200));
          enemyswarm = new EnemySwarm(3, 8, enemy_ship, bullet);
          enemyswarm.display(g);
          enemyIsAlive.set(true);
        }

        enemy.move();
        enemyswarm.display(g);

        if (playerIsAlive.get() && numHits.size() == 0){
          hearts1.display(g);
          hearts2.display(g);
          hearts3.display(g);
        }

        if (numHits.size() == 1){
          hearts1.display(g);
          hearts2.display(g);
        }

        if (numHits.size() == 2){
          hearts1.display(g);
        }

        if (counter >= 100){
          FRAME_RATE = 250;
          PLAYER_SHOOT_RATE = 100;
          g.setFill(Color.WHITE);
          g.fillText("HEATING UP", 350, 20);
          }
          if (counter >= 200){
            FRAME_RATE = 50;
            PLAYER_SHOOT_RATE = 200;
          }

          for (int i = 0; i < bullets.size(); i++){
            Bullet b = bullets.get(i);
            if (b == null){
              System.out.println("Stop");
            } else {
              b.update();
              b.display(g);
            }
            if (player.overlaps(b)){
              player.reset();
              playerIsAlive.set(false);
              numHits.add(1);
            }
            if (enemy.overlaps(b)){
              bullets.remove(b);
              enemyIsAlive.set(false);
              counter += 1;
            }
            for (Enemy enemy : enemyswarm.swarm){
              if (enemy.overlaps(b)){
                counter += 1;
                bullets.remove(b);
                enemyDead.add(enemy);
              }
            }
            for (Enemy enemy : enemyDead){
              enemyswarm.swarm.remove(enemy);
            }
          }

          move();
          g.setFill(Color.WHITE);
          g.fillText("Opps dead: " + counter, 30, 30);

          if (numHits.size() == 3){
            g.clearRect(0, 0, 800, 800);
            g.setFill(Color.BLACK);
            g.fillRect(0, 0, 800, 800);
            g.setFill(Color.WHITE);
            g.fillText("DEAD", 350, 350);
            stop();
          }

        }


        public void move(){

          if(codes.contains(KeyCode.A)){
            player.moveLeft();
          } 
          if (codes.contains(KeyCode.S)){
            player.moveDown();
          } 
          if (codes.contains(KeyCode.D)){
            player.moveRight();
          } 
          if (codes.contains(KeyCode.W)){
            player.moveUp();
          } 
          if (codes.contains(KeyCode.SPACE) && player.canShoot){
            bullets.add(player.shoot());
            player.canShoot = false;
          }
        }

      };


    timer.start();
    canvas.requestFocus();

    canvas.setOnKeyReleased((KeyEvent k) -> {
      if (k.getCode().equals(KeyCode.A)) {
        codes.remove(KeyCode.A);

      } 
      if (k.getCode().equals(KeyCode.D)) {
        codes.remove(KeyCode.D);

      } 
      if (k.getCode().equals(KeyCode.W)) {
        codes.remove(KeyCode.W);

      } 
      if (k.getCode().equals(KeyCode.S)) {
        codes.remove(KeyCode.S);

      }
      if (k.getCode().equals(KeyCode.SPACE)) {
        codes.remove(KeyCode.SPACE);

    }
  });

    canvas.setOnKeyPressed((KeyEvent k) -> {
      canvas.requestFocus();
      if(k.getCode().equals(KeyCode.A)){
        codes.add(KeyCode.A);

      } 
      if (k.getCode().equals(KeyCode.D)){
        codes.add(KeyCode.D);
        
      }
      if (k.getCode().equals(KeyCode.W)){
        codes.add(KeyCode.W);

      } 
      if (k.getCode().equals(KeyCode.S)){
        codes.add(KeyCode.S);

      } 
      if (k.getCode().equals(KeyCode.SPACE)){
        codes.add(KeyCode.SPACE);

      }
    });

    
  }
}
