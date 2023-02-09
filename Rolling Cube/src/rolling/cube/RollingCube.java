package rolling.cube;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.AmbientLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;


public class RollingCube extends Application {
  private static final int W = 800;
  private static final int H = 600;

  
  private double mouseX;
  private double mouseY;
  private double mouseZ;
  private double mouseOldX;
  private double mouseOldZ;
  private double mouseOldY;
  private Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
  private Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
  private Rotate rotateY= new Rotate(0, Rotate.Y_AXIS);

  @Override
  public void start(Stage primaryStage) {
    Group g = new Group();
    Scene scene = new Scene(g, W, H);

    Box floor = new Box(1000, 50, 1000);
    floor.setMaterial(new PhongMaterial(Color.GRAY));
    floor.setTranslateY(245);
   
    Box cube = new Box(50, 50, 50);
    cube.setMaterial(new PhongMaterial(Color.RED));
    cube.setTranslateY(195);
    
    cube.getTransforms().addAll(rotateX, rotateY,rotateZ);
    PointLight light = new PointLight();
      light.setColor(Color.WHITE);
      light.setTranslateY(100);
      light.setTranslateZ(-100);
    g.getChildren().addAll(floor,cube,light);

    PerspectiveCamera camera = new PerspectiveCamera();
    camera.setFieldOfView(45);
    camera.setTranslateZ(500);
    camera.setTranslateX(-390);
    camera.setTranslateY(-120);
    scene.setCamera(camera);

    scene.setOnKeyPressed(event -> {
      KeyCode keyCode = event.getCode();
        
      if (keyCode == KeyCode.A) {cube.setTranslateX(cube.getTranslateX() - 10);
        cube.getTransforms().add(new Rotate(-10, Rotate.Z_AXIS));} 
        else if (keyCode == KeyCode.D) {cube.setTranslateX(cube.getTranslateX() + 10);
        cube.getTransforms().add(new Rotate(+10, Rotate.Z_AXIS));}
        //if (keyCode == KeyCode.W) {cube.setTranslateZ(cube.getTranslateZ() + S);
        //cube.getTransforms().add(new Rotate(-10, Rotate.X_AXIS));} 
        //else if (keyCode == KeyCode.S) {cube.setTranslateZ(cube.getTranslateZ() - S);
        //cube.getTransforms().add(new Rotate(+10, Rotate.X_AXIS));} 
        
      
        if (keyCode == KeyCode.NUMPAD7) {camera.setTranslateY(camera.getTranslateY() - 10);}
        else if (keyCode == KeyCode.NUMPAD9) {camera.setTranslateY(camera.getTranslateY() + 10);}
        else if (keyCode == KeyCode.NUMPAD4) {camera.setTranslateX(camera.getTranslateX() - 10);} 
        else if (keyCode == KeyCode.NUMPAD6) {camera.setTranslateX(camera.getTranslateX() + 10);} 
        else if (keyCode == KeyCode.NUMPAD2) {camera.setTranslateZ(camera.getTranslateZ() - 10);} 
        else if (keyCode == KeyCode.NUMPAD8) {camera.setTranslateZ(camera.getTranslateZ() + 10);}
     });
    

  
  scene.setOnMousePressed(event -> {
  if (event.isPrimaryButtonDown()) {
    mouseOldX = event.getSceneX();
    mouseOldY = event.getSceneY();
  }
});

scene.setOnMouseDragged(event -> {
  if (event.isPrimaryButtonDown()) {
    mouseX = event.getSceneX();
    mouseY = event.getSceneY();

    cube.setTranslateX(cube.getTranslateX() + (mouseX - mouseOldX));
    cube.setRotate(cube.getRotate() + (mouseX - mouseOldX));

    mouseOldX = mouseX;
    mouseOldY = mouseY;
  }
});

primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}