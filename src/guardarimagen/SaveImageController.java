/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guardarimagen;

import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author w7
 */
public class SaveImageController implements Initializable {

    FileChooser fc;
    File seleccionarArchivo;
    FileInputStream fis;
    Main s = new Main();
    @FXML
    private JFXButton btnAbrir;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private ImageView imgView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }

    private void showFileChooser() {
        
        //Se instancia fileChooeser para mostrar el cuadro de dialogo: en este caso se abrir archivo;
        fc = new FileChooser();
        fc.setTitle("Elige una imagen representativa");
        fc.getExtensionFilters().addAll(new ExtensionFilter("Imagenes", "*.png", "*.jpg"));
        seleccionarArchivo = fc.showOpenDialog(null);
        int fcLengthImg = (int)seleccionarArchivo.length();
        if (fcLengthImg > 1024) {
            System.out.println("EL PESO DE LA IMAGEN ES MAYOR A 1024KB");
            try {
            fis = new FileInputStream(seleccionarArchivo);
            Image img = new Image(fis);
            if (seleccionarArchivo != null) {
                imgView.setImage(img);
            }
        } catch (FileNotFoundException ex) {
                System.out.println("Exception: " + ex.getMessage());
        }
        }
        
    }

    //método que convierte una imagen a un array de bytes
    private byte[] toBlob() {
        byte[] result = new byte[(int) seleccionarArchivo.length()];
        try {
            fis = new FileInputStream(seleccionarArchivo);
            fis.read(result);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveImageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaveImageController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(SaveImageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    //método que ubica en donde se va guardar las imagenes
    private void guardarImagen() throws URISyntaxException {
        //compruebo si seleccionarArchivo contiene alguna imagen
        if (seleccionarArchivo != null) {
            fc = new FileChooser();
            fc.setInitialFileName(seleccionarArchivo.getName());
            //inicializo el tipo de extension de imagenes que va recibir
            fc.getExtensionFilters().addAll(new ExtensionFilter("Images Files", "*.png", "*.jpg"));
            File f = seleccionarArchivo;
            //seleccionarArchivo = fc.showSaveDialog(null);
            BufferedImage bImage = null;
            try {
                //lee la imagen y lo guarda en memoria
                bImage = ImageIO.read(f);
                //escribe la imagen en el lugar especificado en este caso otra
                ImageIO.write(bImage, "jpg", new File("C:\\otra\\" + seleccionarArchivo.getName()));
            } catch (IOException ex) {
                System.out.println("Exception: " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor selecciona tu imagen preferida");
        }
    }

    @FXML
    private void btnAbrirClick(MouseEvent event) {
        if (event.getSource().equals(btnAbrir)) {
            showFileChooser();
        }
    }

    @FXML
    private void btnGuardarClick(MouseEvent event) {
        if (event.getSource().equals(btnGuardar)) {
            try {
                /*BufferedImage bi = null;
                URL url;
                try {
                File f = new File("C:\\Inicia\\Chrysanthemum.jpg");
                bi = ImageIO.read(f);
                ImageIO.write(bi, "jpg", new File("C:\\salida\\salida.jpg"));
                } catch (MalformedURLException ex) {
                Logger.getLogger(SaveImageController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                Logger.getLogger(SaveImageController.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                guardarImagen();
            } catch (URISyntaxException ex) {
                Logger.getLogger(SaveImageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
