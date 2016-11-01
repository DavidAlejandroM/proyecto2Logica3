/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MainFrame;
import javax.swing.JFrame;

/**
 *
 * @author Alejandro
 */
public class MainController {
    private JFrame MainFrame ;

    public MainController() {
        this.MainFrame = new MainFrame();
        this.MainFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        MainController mController = new MainController();
       
    }
    
    
}
