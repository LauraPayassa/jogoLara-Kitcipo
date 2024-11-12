import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.swing.*;


public class PikachuDinossauro extends JPanel {
    int boardWidth = 750;
    int boardHeight = 250;

//midia >:)
private ImageIcon pikachuparado = new ImageIcon("src\\imagens\\pikachu.png");

public PikachuDinossauro() {
    // Cria um JLabel com a imagem 'pikachuparado'
    JLabel labelPikachu = new JLabel(pikachuparado);
    
    // Configurações da janela ou painel, adicionando o JLabel
    JFrame frame = new JFrame("Jogo do Pikachu");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(750, 250);
    frame.add(labelPikachu);
    frame.setVisible(true);
}
    

}
