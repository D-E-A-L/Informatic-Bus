
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JPanel; 
import Model.ListPersona;

/**
 *
 * @author CASPED
 */
public class Interface extends JFrame implements ActionListener {
    public boolean termino; 
    
    public JMenuBar barraMenu=new JMenuBar();
    
    public JMenu pestania1=new JMenu("Archivo");
    public JMenuItem loguearse=new JMenuItem("Administrador");
    public JMenuItem addCustomer=new JMenuItem("Nuevo Cliente");
    public JMenuItem deleteCustomer=new JMenuItem("Eliminar Cliente");
    public JMenuItem Exit=new JMenuItem("Exit");
    
    public JMenu pestania2=new JMenu("Targuetero");
    public JMenuItem designingCards=new JMenuItem("Nueva Tarjeta");
    public JMenuItem designingQR=new JMenuItem("QR Tarjeta Reverso");
    
    private javax.swing.JLabel jLabel1;
    JFrame panelillo=new JFrame();
    JPanel panel = new javax.swing.JPanel();
    
    public Interface(){  
        initComponents();
        panelillo.setTitle("Public Transport");
        panelillo.setBounds(600, 220, 718, 590);
        
        loguearse.addActionListener(this);
        addCustomer.addActionListener(this);
        deleteCustomer.addActionListener(this);
        Exit.addActionListener(this);
        
        designingCards.addActionListener(this);
        designingQR.addActionListener(this);
        
        pestania1.add(loguearse);
        pestania1.add(addCustomer);
        pestania1.add(deleteCustomer);
        pestania1.add(Exit);
        
        pestania2.add(designingCards);
        pestania2.add(designingQR);
        
        barraMenu.add(pestania1);
        barraMenu.add(pestania2);
        panelillo.setJMenuBar(barraMenu);
        
        panelillo.setVisible(true);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    
    private void initComponents() {
        
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondo.png"))); 
        panelillo.getContentPane().add(jLabel1);
        
        //pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent eventos){
        
        if(eventos.getSource()==loguearse){
            loguearseUser();
        }
        if(eventos.getSource()==addCustomer){
            addCustomer();
        }
        if(eventos.getSource()==deleteCustomer){
           deleteCustomer(); 
        }
        if(eventos.getSource()==Exit){
            panelillo.dispose();
            System.out.println("Se está pulsando EXIT");
        }
        
        if(eventos.getSource()==designingCards){
            designerCards();
        } 
        
        if(eventos.getSource()==designingQR){
            designerQR();
        }
    }
    
    public void loguearseUser(){
        panelillo.getContentPane().remove(jLabel1);
        panel= new Login();
        panelillo.getContentPane().add(panel);
        panelillo.setVisible(true);
        System.out.println("Se está pulsando administrador");
    }
    
    public void addCustomer(){
        //ListaPersona metodo = new ListPersona();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCliente().setVisible(true);
            }
        });
        System.out.println("Se está pulsando aderir cliente");
    }
    
    public void deleteCustomer(){
        //ListaPersona metodo = new ListPersona();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteCliente().setVisible(true);
            }
        });
        System.out.println("Se está pulsando eliminar cliente");
    }
    
    public void designerCards(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DesigningCards().setVisible(true);
            }
        });
        System.out.println("Se está pulsando targetero");
    }
    
    public void designerQR(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DesigningQR().setVisible(true);
            }
        });
        System.out.println("Se está pulsando generar");
    }
    
}
