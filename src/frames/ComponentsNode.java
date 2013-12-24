/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frames;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import printfmeca.DB;

/**
 *
 * @author Stonum
 */
public class ComponentsNode {
    JPanel pan; 
    JLabel l0;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JLabel l6;
    JLabel l7;
    JLabel l8;
    JLabel l9;
    JLabel l10;
    JLabel l11;
    JLabel l12;
    JLabel l13;
    JLabel l101;
    JButton btnAddPotenc;
    
    JLabel lNodeFunc;
    public JTextField nodeFunc;
    JLabel lNodeView;
    public JTextField nodeView;
    JLabel lNodeAftermath;
    public JTextField nodeAftermath;
    
    JComboBox causeName;
    JSpinner causeCount;
    JScrollPane sp;
    
    public String nameNode;
    
    int y; // смещение панелей внутри таба
    
    final int CENTER = SwingConstants.CENTER;
    final int TOP = SwingConstants.TOP;
    
    Vector potencialModel;
    DB db = new DB();
    
    public ComponentsNode(String nameNode){
        y = 155;
        pan = new JPanel(null);
        
        potencialModel = db.getPotencialName();
        db.close();
        
        lNodeFunc = new JLabel("Функция агрегата/узла");
        lNodeFunc.setBounds(10, 10, 400, 20);
        nodeFunc = new JTextField();
        nodeFunc.setBounds(10, 35, 400, 20);
        lNodeView = new JLabel("Вид потенциального отказа");
        lNodeView.setBounds(420, 10, 400, 20);
        nodeView = new JTextField();
        nodeView.setBounds(420, 35, 400, 20);
        lNodeAftermath = new JLabel("Последствия потенциального отказа");
        lNodeAftermath.setBounds(830, 10, 380, 20);
        nodeAftermath = new JTextField();
        nodeAftermath.setBounds(830, 35, 380, 20);
        
        l0 = new JLabel("Потенциальная причина отказа");
        l0.setBounds(10, 60, 200, 20);
        causeName = new JComboBox();
        causeName.setBounds(200, 60, 210, 20);  
        causeName.setEditable(true);
        causeName.setModel(new DefaultComboBoxModel(potencialModel));
        
        causeCount = new JSpinner();
        causeCount.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        causeCount.setBounds(420, 60, 35, 20);
        btnAddPotenc = new JButton("Добавить потенциальные причины");
        btnAddPotenc.setBounds(465, 60, 250, 20);
        
        l1 = new JLabel("<html><center>Значимость (S)</center></html>");
        l1.setVerticalAlignment(TOP);
        l1.setBounds(20, 90, 70, 50);
        l2 = new JLabel("<html><center>Вероятность возникновения (О)<center></html>");
        l2.setVerticalAlignment(TOP);
        l2.setBounds(100, 90, 90, 50);
        l3 = new JLabel("<html><center>Корневая причина отказа</center></html>");
        l3.setVerticalAlignment(TOP);
        l3.setBounds(200, 90, 140, 50);
        l4 = new JLabel("<html><center>Методы предотвращения отказа</center></html>");
        l4.setVerticalAlignment(TOP);
        l4.setBounds(340, 90, 100, 50);
        l5 = new JLabel("<html><center>Методы обнаружения отказа</center></html>");
        l5.setVerticalAlignment(TOP);
        l5.setBounds(450, 90, 80, 50);
        l6 = new JLabel("<html><center>Вероятность обнаружения (D)</center></html>");
        l6.setVerticalAlignment(TOP);
        l6.setBounds(540, 90, 80, 50);
        l7 = new JLabel("<html><center>Рекомендуемые действия</center<</html>");
        l7.setVerticalAlignment(TOP);
        l7.setBounds(630, 90, 100, 50);
        l8 = new JLabel("<html><center>Классификация воздействия (ремонт/МРО)</center></html>");
        l8.setVerticalAlignment(TOP);
        l8.setBounds(740, 90, 100, 50);
        l9 = new JLabel("<html><center>Ответственный и срок выполнения</center></html>");
        l9.setVerticalAlignment(TOP);
        l9.setBounds(850, 90, 90, 50);
        l10 = new JLabel("<html><center>Предпринятые действия</center></html>");
        l10.setVerticalAlignment(TOP);
        l10.setBounds(950, 90, 90, 50);
        l101 = new JLabel("<html><center>Результаты действий</center><html>");
        l101.setVerticalAlignment(TOP);
        l101.setBounds(1055, 90, 200, 20);
        l11 = new JLabel("<html><center>(S)</center></html>");
        l11.setVerticalAlignment(TOP);
        l11.setBounds(1055, 110, 80, 20);
        l12 = new JLabel("<html><center>(О)</center></html>");
        l12.setVerticalAlignment(TOP);
        l12.setBounds(1110, 110, 80, 20);
        l13 = new JLabel("<html><center>(D)</center></html>");
        l13.setVerticalAlignment(TOP);
        l13.setBounds(1165, 110, 80, 20);
        
        
        pan.add(lNodeFunc);
        pan.add(nodeFunc);
        pan.add(lNodeView);
        pan.add(nodeView);
        pan.add(lNodeAftermath);
        pan.add(nodeAftermath);
        pan.add(l0);
        pan.add(causeName);
        pan.add(causeCount);
        pan.add(btnAddPotenc);
        pan.add(l1);
        pan.add(l2);
        pan.add(l3);
        pan.add(l4);
        pan.add(l5);
        pan.add(l6);
        pan.add(l7);
        pan.add(l8);
        pan.add(l9);
        pan.add(l101);
        pan.add(l10);
        pan.add(l11);
        pan.add(l12);
        pan.add(l13);
        
        sp = new JScrollPane(pan);
        sp.setBounds(10, 10, 10, 10);

        this.nameNode = nameNode;
    }
    
}
