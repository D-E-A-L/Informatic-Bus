/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publictransport;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JPanel; 

/**
 *
 * @author CASPED
 */
public class Interface extends JFrame implements ActionListener {
    public boolean termino; 
    public JMenuBar barraMenu=new JMenuBar();
    public JMenu stock=new JMenu("File");
    public JMenuItem AddCustomer=new JMenuItem("Add Customer");
    public JMenuItem generateQR=new JMenuItem("Generate QR");
    public JMenuItem Exit=new JMenuItem("Exit");
    public Interface(){  
        super("Public Transport");
        setBounds(500,200,500,450);
        
        AddCustomer.addActionListener(this);
        generateQR.addActionListener(this);
        Exit.addActionListener(this);
        
        stock.add(AddCustomer);
        stock.add(generateQR);
        stock.add(Exit);
        
        barraMenu.add(stock);
        setJMenuBar(barraMenu);
        
        setVisible(true);
        JPanel panel= new JPanel();
        panel.setLayout(new GridLayout(6,7));
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    
    @Override
    public void actionPerformed(ActionEvent eventos){
        
        if(eventos.getSource()==AddCustomer){
            addCustomer();
        }
        if(eventos.getSource()==generateQR){
            System.out.println("Se está pulsando eliminar producto");
        }
        if(eventos.getSource()==Exit)
                dispose();
    }
    
    public void addCustomer(){
        ListaPersona metodo = new ListaPersona();
        //etodo.addCustomer("Customer");
        System.out.println("Se está pulsando aderir producto");
    }
}
