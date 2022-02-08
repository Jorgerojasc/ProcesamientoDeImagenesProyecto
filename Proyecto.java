import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.regex.*;
import java.util.Arrays;

public class Proyecto extends Application {
    private double imgWidth;
    private double imgHeight;
    private Image image;
    private BufferedImage img;
    private File imageFile;
    private File selectedFile;
    private double count = 0;
    private HBox hImage;
    private BufferedImage original;
    private BufferedImage secondOriginal;
    private double countC = 0;
    private File secondSelectedFile;

    public void start(Stage primaryStage) {

        BorderPane procesamiento = new BorderPane();
        FileChooser fc = new FileChooser();
        HBox topButton = new HBox(8);
        topButton.setPrefHeight(50);
        Button button = new Button("Subir Imagen");
        button.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        topButton.getChildren().addAll(button);
        button.setMinWidth(150);
        button.setMinHeight(30);
        hImage = new HBox(8);
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {
                try {
                    selectedFile = fc.showOpenDialog(null);

                    if (selectedFile != null) {
                        image = new Image(selectedFile.toURI().toString());
                        imageFile = new File(selectedFile.toURI());
                        imgWidth = image.getWidth();
                        imgHeight = image.getHeight();
                        ImageView vImage = new ImageView(image);
                        img = ImageIO.read(imageFile);
                        original = ImageIO.read(new File(selectedFile.toURI()));

                        vImage.setFitHeight(500);
                        vImage.setFitWidth(700);
                        hImage.getChildren().addAll(vImage);
                    } else {
                        System.out.println("Archivo no valido!");
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();

                }

            }
        });
        HBox hBottom = new HBox(10);
        Button negative = new Button("Negativo");
        negative.setMinWidth(100);
        negative.setMinHeight(30);
        negative.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        negative.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {
                try {
                    negativo(imgWidth, imgHeight, selectedFile, hImage);

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            }
        });
        Button brilloSuma = new Button("Brillo(+)");
        brilloSuma.setMinWidth(100);
        brilloSuma.setMinHeight(30);
        brilloSuma.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        brilloSuma.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {
                count++;

                try {
                    brillo(count, imgWidth, imgHeight, selectedFile, hImage, original);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
        Button brilloResta = new Button("Brillo(-)");
        brilloResta.setMinWidth(100);
        brilloResta.setMinHeight(30);
        brilloResta.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        brilloResta.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {

                count--;
                try {
                    brillo(count, imgWidth, imgHeight, imageFile, hImage, original);
                } catch (IOException ioe) {

                    ioe.printStackTrace();
                }

            }
        });
        Button contrasteSuma = new Button("Contraste(+)");

        contrasteSuma.setMinWidth(100);
        contrasteSuma.setMinHeight(30);
        contrasteSuma.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        contrasteSuma.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {
                countC++;
                try {
                    contraste(countC, imgWidth, imgHeight, selectedFile, hImage, original);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
        Button contrasteResta = new Button("Contraste(-)");
        contrasteResta.setMinWidth(100);
        contrasteResta.setMinHeight(30);
        contrasteResta.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        contrasteResta.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {
                countC--;
                try {
                    contraste(countC, imgWidth, imgHeight, selectedFile, hImage, original);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
        Button andI = new Button("AND");
        andI.setMinWidth(100);
        andI.setMinHeight(30);
        andI.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        andI.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {

                try {
                    secondSelectedFile = fc.showOpenDialog(null);
                    if (secondSelectedFile != null) {
                        secondOriginal = ImageIO.read(new File(secondSelectedFile.toURI()));
                        operacionAND(hImage, secondOriginal, secondSelectedFile, original, selectedFile);

                    } else {
                        System.out.println("Archivo no valido!");
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            }
        });
        Button orI = new Button("OR");
        orI.setMinWidth(100);
        orI.setMinHeight(30);
        orI.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        orI.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {

                try {
                    secondSelectedFile = fc.showOpenDialog(null);
                    if (secondSelectedFile != null) {
                        secondOriginal = ImageIO.read(new File(secondSelectedFile.toURI()));
                        operacionOR(hImage, secondOriginal, secondSelectedFile, original, selectedFile);

                    } else {
                        System.out.println("Archivo no valido!");
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            }
        });
        Button xorI = new Button("XOR");
        xorI.setMinWidth(100);
        xorI.setMinHeight(30);
        xorI.setStyle("-fx-cursor: hand;-fx-background-color:#99ccff;");
        xorI.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) throws RuntimeException {

                try {
                    secondSelectedFile = fc.showOpenDialog(null);
                    if (secondSelectedFile != null) {
                        secondOriginal = ImageIO.read(new File(secondSelectedFile.toURI()));
                        operacionXOR(hImage, secondOriginal, secondSelectedFile, original, selectedFile);

                    } else {
                        System.out.println("Archivo no valido!");
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            }
        });
        hBottom.getChildren().addAll(negative, brilloSuma, brilloResta, contrasteSuma, contrasteResta, andI, orI, xorI);

        topButton.setAlignment(Pos.CENTER);
        hImage.setAlignment(Pos.CENTER);

        procesamiento.setTop(topButton);
        procesamiento.setCenter(hImage);
        procesamiento.setBottom(hBottom);
        Scene scene = new Scene(procesamiento, 900, 680);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Procesamiento de Imagenes");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void operacionXOR(HBox hImage, BufferedImage secondOriginal, File secondSelectedFile,
            BufferedImage original, File selectedFile) throws IOException {
        try {
            double imgWidth = original.getWidth();
            double imgHeight = original.getHeight();
            double imgWidth2 = secondOriginal.getWidth();
            double imgHeight2 = secondOriginal.getHeight();

            BufferedImage auxImg = ImageIO.read(new File(selectedFile.toURI()));
            BufferedImage secondauxImg = ImageIO.read(new File(secondSelectedFile.toURI()));
            BufferedImage img2 = secondauxImg;
            BufferedImage img1 = auxImg;
            if (imgWidth <= imgWidth2 && imgHeight <= imgHeight2) {
                for (int i = 0; i < imgHeight; i++) {
                    for (int x = 0; x < imgWidth; x++) {
                        int pixel1 = img1.getRGB(x, i);
                        int pixel2 = img2.getRGB(x, i);
                        int red = (pixel1 >> 16) & 0xff;
                        int green = (pixel1 >> 8) & 0xff;
                        int blue = pixel1 & 0xff;
                        int red2 = (pixel2 >> 16) & 0xff;
                        int green2 = (pixel2 >> 8) & 0xff;
                        int blue2 = pixel2 & 0xff;
                        red = red ^ red2;
                        green = green ^ green2;
                        blue = blue ^ blue2;
                        pixel1 = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(x, i, pixel1);

                    }
                }
            }
            if (imgWidth > imgWidth2 && imgHeight > imgHeight2) {
                for (int i = 0; i < imgHeight2; i++) {
                    for (int x = 0; x < imgWidth2; x++) {
                        int pixel1 = img1.getRGB(x, i);
                        int pixel2 = img2.getRGB(x, i);
                        int red = (pixel1 >> 16) & 0xff;
                        int green = (pixel1 >> 8) & 0xff;
                        int blue = pixel1 & 0xff;
                        int red2 = (pixel2 >> 16) & 0xff;
                        int green2 = (pixel2 >> 8) & 0xff;
                        int blue2 = pixel2 & 0xff;
                        red = red ^ red2;
                        green = green ^ green2;
                        blue = blue ^ blue2;
                        pixel1 = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(x, i, pixel1);

                    }
                }
            }

            File nImg;
            String ruta =  selectedFile.toString();
            String[] rutaSplitted = ruta.split(Pattern.quote(File.separator));
            int nombre_antiguo= rutaSplitted.length-1;
            String nuevaRuta = String.join("/", Arrays.copyOfRange(rutaSplitted, 0, nombre_antiguo));
            ImageIO.write(img1, "jpg", nImg = new File(nuevaRuta + "/imagenModificada_" + rutaSplitted[nombre_antiguo]));// Directorio donde
            // se va
            // a guardar la imagen
            // modificada
            ImageView vImage = new ImageView(nImg.toURI().toString());
            vImage.setFitHeight(500);
            vImage.setFitWidth(700);
            System.out.println("Se uso la operacion XOR!");
            hImage.getChildren().clear();
            hImage.getChildren().addAll(vImage);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void operacionOR(HBox hImage, BufferedImage secondOriginal, File secondSelectedFile,
            BufferedImage original, File selectedFile) throws IOException {
        try {
            double imgWidth = original.getWidth();
            double imgHeight = original.getHeight();
            double imgWidth2 = secondOriginal.getWidth();
            double imgHeight2 = secondOriginal.getHeight();
            BufferedImage auxImg = ImageIO.read(new File(selectedFile.toURI()));
            BufferedImage secondauxImg = ImageIO.read(new File(secondSelectedFile.toURI()));
            BufferedImage img2 = secondauxImg;
            BufferedImage img1 = auxImg;
            if (imgWidth <= imgWidth2 && imgHeight <= imgHeight2) {
                for (int i = 0; i < imgHeight; i++) {
                    for (int x = 0; x < imgWidth; x++) {
                        int pixel1 = img1.getRGB(x, i);
                        int pixel2 = img2.getRGB(x, i);
                        int red = (pixel1 >> 16) & 0xff;
                        int green = (pixel1 >> 8) & 0xff;
                        int blue = pixel1 & 0xff;
                        int red2 = (pixel2 >> 16) & 0xff;
                        int green2 = (pixel2 >> 8) & 0xff;
                        int blue2 = pixel2 & 0xff;
                        red = red | red2;
                        green = green | green2;
                        blue = blue | blue2;
                        pixel1 = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(x, i, pixel1);

                    }
                }
            } else {
                for (int i = 0; i < imgHeight2; i++) {
                    for (int x = 0; x < imgWidth2; x++) {
                        int pixel1 = img1.getRGB(x, i);
                        int pixel2 = img2.getRGB(x, i);
                        int red = (pixel1 >> 16) & 0xff;
                        int green = (pixel1 >> 8) & 0xff;
                        int blue = pixel1 & 0xff;
                        int red2 = (pixel2 >> 16) & 0xff;
                        int green2 = (pixel2 >> 8) & 0xff;
                        int blue2 = pixel2 & 0xff;
                        red = red | red2;
                        green = green | green2;
                        blue = blue | blue2;
                        pixel1 = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(x, i, pixel1);

                    }
                }
            }

            File nImg;
            String ruta =  selectedFile.toString();
            String[] rutaSplitted = ruta.split(Pattern.quote(File.separator));
            int nombre_antiguo= rutaSplitted.length-1;
            String nuevaRuta = String.join("/", Arrays.copyOfRange(rutaSplitted, 0, nombre_antiguo));
            ImageIO.write(img1, "jpg", nImg = new File(nuevaRuta + "/imagenModificada_" + rutaSplitted[nombre_antiguo]));
            ImageView vImage = new ImageView(nImg.toURI().toString());
            vImage.setFitHeight(500);
            vImage.setFitWidth(700);
            System.out.println("Se uso la operacion OR!");
            hImage.getChildren().clear();
            hImage.getChildren().addAll(vImage);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void operacionAND(HBox hImage, BufferedImage secondOriginal, File secondSelectedFile,
            BufferedImage original, File selectedFile) throws IOException {
        try {
            double imgWidth = original.getWidth();
            double imgHeight = original.getHeight();
            double imgWidth2 = secondOriginal.getWidth();
            double imgHeight2 = secondOriginal.getHeight();
            BufferedImage auxImg = ImageIO.read(new File(selectedFile.toURI()));
            BufferedImage secondauxImg = ImageIO.read(new File(secondSelectedFile.toURI()));
            BufferedImage img2 = secondauxImg;
            BufferedImage img1 = auxImg;
            if (imgWidth <= imgWidth2 && imgHeight <= imgHeight2) {
                for (int i = 0; i < imgWidth; i++) {
                    for (int x = 0; x < imgHeight; x++) {
                        int pixel1 = img1.getRGB(i, x);
                        int pixel2 = img2.getRGB(i, x);
                        int red = (pixel1 >> 16) & 0xff;
                        int green = (pixel1 >> 8) & 0xff;
                        int blue = pixel1 & 0xff;
                        int red2 = (pixel2 >> 16) & 0xff;
                        int green2 = (pixel2 >> 8) & 0xff;
                        int blue2 = pixel2 & 0xff;
                        red = red & red2;
                        green = green & green2;
                        blue = blue & blue2;
                        pixel1 = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(i, x, pixel1);

                    }
                }
            } else {
                for (int i = 0; i < imgWidth2; i++) {
                    for (int x = 0; x < imgHeight2; x++) {
                        int pixel1 = img1.getRGB(i, x);
                        int pixel2 = img2.getRGB(i, x);
                        int red = (pixel1 >> 16) & 0xff;
                        int green = (pixel1 >> 8) & 0xff;
                        int blue = pixel1 & 0xff;
                        int red2 = (pixel2 >> 16) & 0xff;
                        int green2 = (pixel2 >> 8) & 0xff;
                        int blue2 = pixel2 & 0xff;
                        red = red & red2;
                        green = green & green2;
                        blue = blue & blue2;
                        pixel1 = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(i, x, pixel1);

                    }
                }
            }

            File nImg;
            String ruta =  selectedFile.toString();
            String[] rutaSplitted = ruta.split(Pattern.quote(File.separator));
            int nombre_antiguo= rutaSplitted.length-1;
            String nuevaRuta = String.join("/", Arrays.copyOfRange(rutaSplitted, 0, nombre_antiguo));
            ImageIO.write(img1, "jpg", nImg = new File(nuevaRuta + "/imagenModificada_" + rutaSplitted[nombre_antiguo]));
            ImageView vImage = new ImageView(nImg.toURI().toString());
            vImage.setFitHeight(500);
            vImage.setFitWidth(700);
            System.out.println("Se uso la operacion AND!");
            hImage.getChildren().clear();
            hImage.getChildren().addAll(vImage);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    private static void negativo( double imgWidth, double imgHeight, File selectedFile, HBox hImage
            )throws IOException{
        try{

            BufferedImage auxImg = ImageIO.read(new File(selectedFile.toURI()));
            BufferedImage img1 = auxImg;
            for (int i = 0; i < imgHeight; i++) {
                for (int x = 0; x < imgWidth; x++) {
                    int pixel = img1.getRGB(x, i);
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = pixel & 0xff;
                    red = 255 - red;
                    green = 255 - green;
                    blue = 255 - blue;
                    pixel = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                            + (blue * (int) Math.pow(256, 0));
                    img1.setRGB(x, i, pixel);

                }
            }
            File nImg;
            String ruta =  selectedFile.toString();
            String[] rutaSplitted = ruta.split(Pattern.quote(File.separator));
            int nombre_antiguo= rutaSplitted.length-1;
            String nuevaRuta = String.join("/", Arrays.copyOfRange(rutaSplitted, 0, nombre_antiguo));
            ImageIO.write(img1, "jpg", nImg = new File(nuevaRuta + "/imagenModificada_" + rutaSplitted[nombre_antiguo]));
           
            ImageView vImage = new ImageView(nImg.toURI().toString());
            vImage.setFitHeight(500);
            vImage.setFitWidth(700);
            System.out.println("Negativo");
            hImage.getChildren().clear();
            hImage.getChildren().addAll(vImage);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    private static void contraste(double countC, double imgWidth, double imgHeight, File selectedFile, HBox hImage,
            BufferedImage original) throws IOException {
        try {
            BufferedImage auxImg = ImageIO.read(new File(selectedFile.toURI()));
            BufferedImage img1 = auxImg;
            if (countC != 0) {
                for (int i = 0; i < imgHeight; i++) {
                    for (int x = 0; x < imgWidth; x++) {

                        double cFinal = (countC + 100.0f) / 100.0f;
                        int pixel = img1.getRGB(x, i);
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = pixel & 0xff;
                        red = (int) (((((red / 255.0f) - 0.5f) * cFinal) + 0.5f) * 255.0f);
                        green = (int) (((((green / 255.0f) - 0.5f) * cFinal) + 0.5f) * 255.0f);
                        blue = (int) (((((blue / 255.0f) - 0.5f) * cFinal) + 0.5f) * 255.0f);
                        if (red > 255) {
                            red = 255;

                        }
                        if (green > 255) {
                            green = 255;

                        }
                        if (blue > 255) {
                            blue = 255;
                        }
                        if (red < 0) {
                            red = 0;
                        }
                        if (green < 0) {
                            green = 0;
                        }
                        if (blue < 0) {
                            blue = 0;
                        }
                        pixel = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(x, i, pixel);

                    }
                }
            }
            if (countC == 0) {
                for (int i = 0; i < imgHeight; i++) {
                    for (int x = 0; x < imgWidth; x++) {
                        img1.setRGB(x, i, original.getRGB(x, i));
                    }
                }
            }
            File nImg;
            String ruta =  selectedFile.toString();
            String[] rutaSplitted = ruta.split(Pattern.quote(File.separator));
            int nombre_antiguo= rutaSplitted.length-1;
            String nuevaRuta = String.join("/", Arrays.copyOfRange(rutaSplitted, 0, nombre_antiguo));
            ImageIO.write(img1, "jpg", nImg = new File(nuevaRuta + "/imagenModificada_" + rutaSplitted[nombre_antiguo]));
            ImageView vImage = new ImageView(nImg.toURI().toString());
            vImage.setFitHeight(500);
            vImage.setFitWidth(700);
            System.out.println("Contraste en %:" + countC);
            hImage.getChildren().clear();
            hImage.getChildren().addAll(vImage);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private static void brillo(double count, double imgWidth, double imgHeight, File selectedFile, HBox hImage,
            BufferedImage original) throws IOException {
        try {
            BufferedImage auxImg = ImageIO.read(new File(selectedFile.toURI()));
            BufferedImage img1 = auxImg;
            if (count > 0) {
                for (int i = 0; i < imgHeight; i++) {

                    for (int x = 0; x < imgWidth; x++) {
                        int pixel = img1.getRGB(x, i);
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = pixel & 0xff;
                        double brillo = ((count * 2) / 100) + 1;// El brillo ira desde 0 a 2, en donde de 1.0 hacia 2 es
                                                                // aumento de brillo. Por eso se multiplica count *2
                        red = (int) (red * brillo);
                        green = (int) (green * brillo);
                        blue = (int) (blue * brillo);
                        // red = (int) (red + count);
                        // green = (int) (green + count);
                        // blue = (int) (blue + count);
                        if (red > 255) {
                            red = 255;

                        }
                        if (green > 255) {
                            green = 255;

                        }
                        if (blue > 255) {
                            blue = 255;
                        }
                        if (red < 0) {
                            red = 0;
                        }
                        if (green < 0) {
                            green = 0;
                        }
                        if (blue < 0) {
                            blue = 0;
                        }
                        pixel = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(x, i, pixel);

                    }
                }

            }
            if (count < 0) {

                for (int i = 0; i < imgHeight; i++) {

                    for (int x = 0; x < imgWidth; x++) {
                        int pixel = img1.getRGB(x, i);
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = pixel & 0xff;
                        double brillo = ((count * 1) / 100) + 1;

                        // red = (int) (red * brillo);
                        // green = (int) (green * brillo);
                        // blue = (int) (blue * brillo);
                        red = (int) (red + count);
                        green = (int) (green + count);
                        blue = (int) (blue + count);

                        if (red > 255) {
                            red = 255;

                        }
                        if (green > 255) {
                            green = 255;

                        }
                        if (blue > 255) {
                            blue = 255;
                        }
                        if (red < 0) {
                            red = 0;
                        }
                        if (green < 0) {
                            green = 0;
                        }
                        if (blue < 0) {
                            blue = 0;
                        }
                        pixel = (red * (int) Math.pow(256, 2)) + (green * (int) Math.pow(256, 1))
                                + (blue * (int) Math.pow(256, 0));
                        img1.setRGB(x, i, pixel);

                    }
                }
            }
            if (count == 0) {
                for (int i = 0; i < imgHeight; i++) {
                    for (int x = 0; x < imgWidth; x++) {
                        img1.setRGB(x, i, original.getRGB(x, i));
                    }
                }
            }
            File nImg;
            String ruta =  selectedFile.toString();
            String[] rutaSplitted = ruta.split(Pattern.quote(File.separator));
            int nombre_antiguo= rutaSplitted.length-1;
            String nuevaRuta = String.join("/", Arrays.copyOfRange(rutaSplitted, 0, nombre_antiguo));
            ImageIO.write(img1, "jpg", nImg = new File(nuevaRuta + "/imagenModificada_" + rutaSplitted[nombre_antiguo]));
            ImageView vImage = new ImageView(nImg.toURI().toString());
            vImage.setFitHeight(500);
            vImage.setFitWidth(700);
            System.out.println("Brillo en %:" + count);
            hImage.getChildren().clear();
            hImage.getChildren().addAll(vImage);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

}
