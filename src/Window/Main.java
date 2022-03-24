package Window;


import Matrix.SeqSparseMatrix;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Application.setUserAgentStylesheet(getClass().getResource("CSS.css").toExternalForm());

        //容器
        primaryStage.show();
        primaryStage.setTitle("稀疏矩阵计算器");
        primaryStage.setHeight(600);
        primaryStage.setWidth(1200);
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane);
        anchorPane.setId("root");
        primaryStage.setScene(scene);
        URL sampleCss = getClass().getResource("/Window/CSS.css");
        scene.getStylesheets().add(sampleCss.toExternalForm());
        //文本框
        TextArea Text_Area_A = new TextArea();
        TextArea Text_Area_B = new TextArea();
        TextArea Text_Area_C = new TextArea();
        Text_Area_A.setPrefSize(250, 400);
        Text_Area_B.setPrefSize(250, 400);
        Text_Area_C.setPrefSize(400, 400);
        Text_Area_A.setStyle("-fx-font-size: 12");
        Text_Area_B.setStyle("-fx-font-size: 12");
        Text_Area_C.setStyle("-fx-font-size: 20");



        Text_Area_A.setText("" +
                "输入格式如下\n" +
                "矩阵的行X 矩阵的列Y 非零元个数Z\n" +
                "下面Z行输入Z个非零元\n" +
                "行号 列号 值\n" +
                "行号 列号 值\n" +
                "... ... ...\n" +
                "... ... ...");
        Text_Area_B.setText("" +
                "输入格式如下\n" +
                "矩阵的行X 矩阵的列Y 非零元个数Z\n" +
                "下面Z行输入Z个非零元\n" +
                "行号 列号 值\n" +
                "行号 列号 值\n" +
                "... ... ...\n" +
                "... ... ...");


        anchorPane.getChildren().addAll(Text_Area_A);
        anchorPane.getChildren().addAll(Text_Area_B);
        anchorPane.getChildren().addAll(Text_Area_C);
        AnchorPane.setLeftAnchor(Text_Area_A, 70.0);
        AnchorPane.setLeftAnchor(Text_Area_B, 400.0);
        AnchorPane.setLeftAnchor(Text_Area_C, 720.0);
        AnchorPane.setTopAnchor(Text_Area_A, 50.0);
        AnchorPane.setTopAnchor(Text_Area_B, 50.0);
        AnchorPane.setTopAnchor(Text_Area_C, 50.0);

        //按钮
        Button Button_Plus = new Button("A+B");
        Button Button_Minus = new Button("A-B");
        Button Button_Multiply = new Button("A*B");
        Button Button_Transpose = new Button("A'");
        Button_Plus.setPrefSize(80, 40);
        Button_Minus.setPrefSize(80, 40);
        Button_Multiply.setPrefSize(80, 40);
        Button_Transpose.setPrefSize(80, 40);
        Button_Plus.setStyle("-fx-font-size: 16");
        Button_Minus.setStyle("-fx-font-size: 16");
        Button_Multiply.setStyle("-fx-font-size: 16");
        Button_Transpose.setStyle("-fx-font-size: 16");

        anchorPane.getChildren().addAll(Button_Plus);
        anchorPane.getChildren().addAll(Button_Minus);
        anchorPane.getChildren().addAll(Button_Multiply);
        anchorPane.getChildren().addAll(Button_Transpose);
        AnchorPane.setBottomAnchor(Button_Plus, 40.0);
        AnchorPane.setLeftAnchor(Button_Plus, 300.0);
        AnchorPane.setBottomAnchor(Button_Minus, 40.0);
        AnchorPane.setLeftAnchor(Button_Minus, 440.0);
        AnchorPane.setBottomAnchor(Button_Multiply, 40.0);
        AnchorPane.setLeftAnchor(Button_Multiply, 580.0);
        AnchorPane.setBottomAnchor(Button_Transpose, 40.0);
        AnchorPane.setLeftAnchor(Button_Transpose, 720.0);

        //标签
        Label Label_A = new Label("A");
        Label Label_B = new Label("B");
        Label Label_C = new Label("C");
        Label_A.setPrefSize(35, 50);
        Label_B.setPrefSize(35, 50);
        Label_C.setPrefSize(35, 50);
        Label_A.setScaleX(3);
        Label_A.setScaleY(3);
        Label_B.setScaleX(3);
        Label_B.setScaleY(3);
        Label_C.setScaleX(3);
        Label_C.setScaleY(3);
        anchorPane.getChildren().addAll(Label_A);
        anchorPane.getChildren().addAll(Label_B);
        anchorPane.getChildren().addAll(Label_C);
        AnchorPane.setLeftAnchor(Label_A, 210.0);
        AnchorPane.setLeftAnchor(Label_B, 540.0);
        AnchorPane.setLeftAnchor(Label_C, 930.0);

        //文本框输入提示
        final boolean[] flag_1 = {false};
        Text_Area_A.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (flag_1[0] ==true) return;
                Text_Area_A.setStyle("-fx-font-size: 25");
                Text_Area_A.clear();
                flag_1[0] =true;
            }
        });
        final boolean[] flag_2 = {false};
        Text_Area_B.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (flag_2[0] ==true) return;
                Text_Area_B.setStyle("-fx-font-size: 25");
                Text_Area_B.clear();
                flag_2[0] =true;
            }
        });



        Button_Plus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String[] String_Matrix_A = Text_Area_A.getText().split("\n");
                    String[] Temp_A = String_Matrix_A[0].split(" ");
                    int Count_A = Integer.parseInt(Temp_A[2]);
                    SeqSparseMatrix Matrix_A = new SeqSparseMatrix(Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]));
                    System.out.printf("Matrix_A:%d行 %d列 %d个非零元\n", Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                    for (int i=1 ; i<=Count_A ; i++) {
                        Temp_A = String_Matrix_A[i].split(" ");
                        Matrix_A.set(Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                        System.out.printf("第%d个非零元:%d行 %d列 %d 被添加\n", i, Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                    }
                    System.out.println("Matrix_A输入完成");

                    String[] String_Matrix_B = Text_Area_B.getText().split("\n");
                    String[] Temp_B = String_Matrix_B[0].split(" ");
                    int Count_B = Integer.parseInt(Temp_B[2]);
                    SeqSparseMatrix Matrix_B = new SeqSparseMatrix(Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]));
                    System.out.printf("Matrix_B:%d行 %d列 %d个非零元\n", Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                    for (int i=1 ; i<=Count_B ; i++) {
                        Temp_B = String_Matrix_B[i].split(" ");
                        Matrix_B.set(Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                        System.out.printf("第%d个非零元:%d行 %d列 %d 被添加\n", i, Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                    }
                    System.out.println("Matrix_B输入完成");

                    SeqSparseMatrix Matrix_C = Matrix_A.plus(Matrix_B);
                    System.out.println(Matrix_C.toString());
                    Text_Area_C.setText(String.valueOf(Matrix_C));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入错误");
                    alert.showAndWait();
                }
            }
        });

        Button_Minus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String[] String_Matrix_A = Text_Area_A.getText().split("\n");
                    String[] Temp_A = String_Matrix_A[0].split(" ");
                    int Count_A = Integer.parseInt(Temp_A[2]);
                    SeqSparseMatrix Matrix_A = new SeqSparseMatrix(Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]));
                    System.out.printf("Matrix_A:%d行 %d列 %d个非零元\n", Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                    for (int i=1 ; i<=Count_A ; i++) {
                        Temp_A = String_Matrix_A[i].split(" ");
                        Matrix_A.set(Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                        System.out.printf("第%d个非零元:%d行 %d列 %d 被添加\n", i, Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                    }
                    System.out.println("Matrix_A输入完成");

                    String[] String_Matrix_B = Text_Area_B.getText().split("\n");
                    String[] Temp_B = String_Matrix_B[0].split(" ");
                    int Count_B = Integer.parseInt(Temp_B[2]);
                    SeqSparseMatrix Matrix_B = new SeqSparseMatrix(Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]));
                    System.out.printf("Matrix_B:%d行 %d列 %d个非零元\n", Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                    for (int i=1 ; i<=Count_B ; i++) {
                        Temp_B = String_Matrix_B[i].split(" ");
                        Matrix_B.set(Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                        System.out.printf("第%d个非零元:%d行 %d列 %d 被添加\n", i, Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                    }
                    System.out.println("Matrix_B输入完成");

                    SeqSparseMatrix Matrix_C = Matrix_A.minus(Matrix_B);
                    Text_Area_C.setText(String.valueOf(Matrix_C));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入错误");
                    alert.showAndWait();
                }
            }
        });

        Button_Multiply.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String[] String_Matrix_A = Text_Area_A.getText().split("\n");
                    String[] Temp_A = String_Matrix_A[0].split(" ");
                    int Count_A = Integer.parseInt(Temp_A[2]);
                    SeqSparseMatrix Matrix_A = new SeqSparseMatrix(Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]));
                    System.out.printf("Matrix_A:%d行 %d列 %d个非零元\n", Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                    for (int i=1 ; i<=Count_A ; i++) {
                        Temp_A = String_Matrix_A[i].split(" ");
                        Matrix_A.set(Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                        System.out.printf("第%d个非零元:%d行 %d列 %d 被添加\n", i, Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                    }
                    System.out.println("Matrix_A输入完成");

                    String[] String_Matrix_B = Text_Area_B.getText().split("\n");
                    String[] Temp_B = String_Matrix_B[0].split(" ");
                    int Count_B = Integer.parseInt(Temp_B[2]);
                    SeqSparseMatrix Matrix_B = new SeqSparseMatrix(Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]));
                    System.out.printf("Matrix_B:%d行 %d列 %d个非零元\n", Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                    for (int i=1 ; i<=Count_B ; i++) {
                        Temp_B = String_Matrix_B[i].split(" ");
                        Matrix_B.set(Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                        System.out.printf("第%d个非零元:%d行 %d列 %d 被添加\n", i, Integer.parseInt(Temp_B[0]), Integer.parseInt(Temp_B[1]), Integer.parseInt(Temp_B[2]));
                    }
                    System.out.println("Matrix_B输入完成");
                    SeqSparseMatrix Matrix_C = Matrix_A.multiply(Matrix_B);
                    Text_Area_C.setText(String.valueOf(Matrix_C));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入错误");
                    alert.showAndWait();
                }
            }
        });

        Button_Transpose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


                try {
                    String[] String_Matrix_A = Text_Area_A.getText().split("\n");
                    String[] Temp_A = String_Matrix_A[0].split(" ");
                    int Count_A = Integer.parseInt(Temp_A[2]);
                    SeqSparseMatrix Matrix_A = new SeqSparseMatrix(Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]));
                    System.out.printf("Matrix_A:%d行 %d列 %d个非零元\n", Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                    for (int i=1 ; i<=Count_A ; i++) {
                        Temp_A = String_Matrix_A[i].split(" ");
                        Matrix_A.set(Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                        System.out.printf("第%d个非零元:%d行 %d列 %d 被添加\n", i, Integer.parseInt(Temp_A[0]), Integer.parseInt(Temp_A[1]), Integer.parseInt(Temp_A[2]));
                    }
                    System.out.println("Matrix_A输入完成");
                    SeqSparseMatrix Matrix_C = Matrix_A.transpose();
                    Text_Area_C.setText(String.valueOf(Matrix_C));
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText("输入错误");
                    alert.showAndWait();
                }
            }
        });


    }

    public static void main (String args[]) {
        launch(args);
    }
}
