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
    
    public JMenu pestania1=new JMenu("File");
    public JMenuItem addCustomer=new JMenuItem("Add Customer");
    public JMenuItem deleteCustomer=new JMenuItem("Delete Customer");
    public JMenuItem Exit=new JMenuItem("Exit");
    
    public JMenu pestania2=new JMenu("QR");
    public JMenuItem generarQR=new JMenuItem("Abrir Generador QR");
    
    
    public Interface(){  
        super("Public Transport");
        setBounds(500,200,500,450);
        
        addCustomer.addActionListener(this);
        deleteCustomer.addActionListener(this);
        Exit.addActionListener(this);
        
        generarQR.addActionListener(this);
        
        pestania1.add(addCustomer);
        pestania1.add(deleteCustomer);
        pestania1.add(Exit);
        
        pestania2.add(generarQR);
        
        barraMenu.add(pestania1);
        barraMenu.add(pestania2);
        setJMenuBar(barraMenu);
        
        setVisible(true);
        JPanel panel= new JPanel();
        panel.setLayout(new GridLayout(6,7));
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    
    @Override
    public void actionPerformed(ActionEvent eventos){
        
        if(eventos.getSource()==addCustomer){
            addCustomer();
        }
        if(eventos.getSource()==deleteCustomer){
           deleteCustomer(); 
        }
        if(eventos.getSource()==Exit)
            dispose();
        
        if(eventos.getSource()==generarQR){
            generarQR();
        }
    }
    
    public void addCustomer(){
        ListaPersona metodo = new ListaPersona();
        System.out.println("Se está pulsando aderir cliente");
    }
    
    public void deleteCustomer(){
        ListaPersona metodo = new ListaPersona();
        System.out.println("Se está pulsando eliminar cliente");
    }
    
    public void generarQR(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GeneradorQR().setVisible(true);
            }
        });
        System.out.println("Se está pulsando generar");
    }
    
}
