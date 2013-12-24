/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import printfmeca.DB;

/**
 *
 * @author Stonum
 */
public class ComponentsRootCauses {

    public JComboBox c1;
    public JComboBox c2;
    public JComboBox c3;
    public JTextField t4;
    public JTextField t5;
    public JComboBox c6;
    public JTextField t7;
    public JTextField t8;
    public JTextField t9;
    public JTextField t10;
    public JComboBox c11;
    public JComboBox c12;
    public JComboBox c13;
    public JScrollPane sp;
    int indexNode;
    public String namePotencialCause;
    JLabel statusLabel;

    Object[] cModel = new Object[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    Vector rootModel;
    DB db = new DB();

    public ComponentsRootCauses(int indexNode, String namePotencialCause, JPanel pan, int count, final JLabel statusLabel) {
        this.statusLabel = statusLabel;

        rootModel = db.getRootName();
        db.close();

        c1 = new JComboBox();
        c1.setBounds(10, 20 + 20 * count, 70, 20);
        c1.setModel(new DefaultComboBoxModel(cModel));
        c1.addItemListener(new IsChange());
        c2 = new JComboBox();
        c2.setBounds(90, 20 + 20 * count, 90, 20);
        c2.setModel(new DefaultComboBoxModel(cModel));
        c2.addItemListener(new IsChange());
        c3 = new JComboBox();
        c3.setBounds(190, 20 + 20 * count, 130, 20);
        c3.setModel(new DefaultComboBoxModel(rootModel));
        c3.addItemListener(new IsChange());
        c3.setEditable(true);
        t4 = new JTextField();
        t4.setBounds(330, 20 + 20 * count, 100, 20);
        t4.addFocusListener(new IsFocus());
        t4.addKeyListener(new IsKeyPress());
        t5 = new JTextField();
        t5.setBounds(440, 20 + 20 * count, 80, 20);
        t5.addFocusListener(new IsFocus());
        t5.addKeyListener(new IsKeyPress());
        c6 = new JComboBox();
        c6.setBounds(530, 20 + 20 * count, 80, 20);
        c6.setModel(new DefaultComboBoxModel(cModel));
        c6.addItemListener(new IsChange());
        t7 = new JTextField();
        t7.setBounds(620, 20 + 20 * count, 100, 20);
        t7.addFocusListener(new IsFocus());
        t7.addKeyListener(new IsKeyPress());
        t8 = new JTextField();
        t8.setBounds(730, 20 + 20 * count, 100, 20);
        t8.addFocusListener(new IsFocus());
        t8.addKeyListener(new IsKeyPress());
        t9 = new JTextField();
        t9.setBounds(840, 20 + 20 * count, 90, 20);
        t9.addFocusListener(new IsFocus());
        t9.addKeyListener(new IsKeyPress());
        t10 = new JTextField();
        t10.setBounds(940, 20 + 20 * count, 90, 20);
        t10.addFocusListener(new IsFocus());
        t10.addKeyListener(new IsKeyPress());
        c11 = new JComboBox();
        c11.setBounds(1040, 20 + 20 * count, 40, 20);
        c11.setModel(new DefaultComboBoxModel(cModel));
        c11.addItemListener(new IsChange());
        c12 = new JComboBox();
        c12.setBounds(1095, 20 + 20 * count, 40, 20);
        c12.setModel(new DefaultComboBoxModel(cModel));
        c12.addItemListener(new IsChange());
        c13 = new JComboBox();
        c13.setBounds(1150, 20 + 20 * count, 40, 20);
        c13.setModel(new DefaultComboBoxModel(cModel));
        c13.addItemListener(new IsChange());

        pan.add(c1);
        pan.add(c2);
        pan.add(c3);
        pan.add(t4);
        pan.add(t5);
        pan.add(c6);
        pan.add(t7);
        pan.add(t8);
        pan.add(t9);
        pan.add(t10);
        pan.add(c11);
        pan.add(c12);
        pan.add(c13);

        this.indexNode = indexNode;
        this.namePotencialCause = namePotencialCause;
    }

    

    class IsFocus extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent evt) {
            if (evt.getComponent() instanceof JTextField) {
                JTextField t = (JTextField) evt.getComponent();
                statusLabel.setText(t.getText());
            }
        }

        @Override
        public void focusLost(FocusEvent evt) {
            statusLabel.setText("");
        }
    }

    class IsKeyPress extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent evt) {
            if (evt.getComponent() instanceof JTextField) {
                JTextField t = (JTextField) evt.getComponent();
                statusLabel.setText(t.getText());
            }
        }
    }

    class IsChange implements ItemListener {

        public void itemStateChanged(ItemEvent evt) {
            statusLabel.setText(evt.getItem().toString());
        }
    }
}
