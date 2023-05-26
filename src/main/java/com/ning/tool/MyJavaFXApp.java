package com.ning.tool;

import com.ning.tool.config.MainConfig;
import com.ning.tool.execute.MdExecute;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class MyJavaFXApp extends Application {
    private MainConfig mainConfig;
    private MdExecute execute;
    {
        execute=new MdExecute();
        mainConfig=new MainConfig();
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        Label label = new Label("md5修改神器");
        StackPane root = new StackPane(label);
// 创建GridPane布局
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER); // 设置内容居中对齐
        // 创建文本框
        final TextField textField1 = new TextField();
        final TextField textField2 = new TextField();

        // 创建按钮
        Button button1 = new Button("选择文件");
        Button button2 = new Button("修改md5");
        // 给按钮1添加点击事件处理器
        button1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // 创建文件选择器
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("选择文件");

                // 打开文件选择对话框
                File selectedFile = fileChooser.showOpenDialog(primaryStage);

                // 如果选择了文件，更新文本框1的内容为文件路径
                if (selectedFile != null) {
                    // 保存为全局变量
                    if(selectedFile.getAbsolutePath()==null){
                        textField1.setText("未解析到文件。。。");
                    }
                    mainConfig.setOrgPath(selectedFile.getAbsolutePath());
                    //查询md5结果，形式在文本框中

                    try {
                        String md5 = execute.getMd5(mainConfig.getOrgPath());
                        textField1.setText(md5);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    String outputPath = execute.changeMd5(mainConfig.getOrgPath(),mainConfig.getNulPath());
                    String md5 = execute.getMd5(outputPath);
                    textField2.setText(md5);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        // 将组件添加到GridPane布局中
        gridPane.add(textField1, 0, 0);
        gridPane.add(button1, 1, 0);
        gridPane.add(textField2, 0, 1);
        gridPane.add(button2, 1, 1);

        // 创建Scene并设置到Stage
        Scene scene = new Scene(gridPane);

        primaryStage.setTitle("md5修改神器");
        primaryStage.setScene(scene);
        // 设置窗口大小
        primaryStage.setWidth(400); // 设置宽度为400像素
        primaryStage.setHeight(300); // 设置高度为300像素
        primaryStage.show();
    }
}

