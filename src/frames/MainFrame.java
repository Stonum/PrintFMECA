package frames;

import java.awt.Color;
import printfmeca.PrintXLS;
import printfmeca.EditXLS;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.table.DefaultTableModel;
import printfmeca.DB;
import struct.*;

/**
 * @author Aleksey Arshinov
 */
public class MainFrame extends JFrame {

    static MainFrame frame;
    static int HEIGHT = 760, WIDTH = 1250;
    EditXLS e;
    DB db;

    Vector developersModel;
    Vector potencialModel;
    Vector rootModel;

    ArrayList<ComponentsNode> arrayNode = new ArrayList<ComponentsNode>();
    ArrayList<ComponentsRootCauses> arrayRoot = new ArrayList<ComponentsRootCauses>();
    public static String filePath = new File("").getAbsolutePath() + "\\output.xls";

    public MainFrame() {
        db = new DB();
        developersModel = db.getDevelopName();
        db.close();
        setModels();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Print FMECA");
        setSize(WIDTH, HEIGHT);
        getContentPane().setLayout(null);
        setFont(new Font("Calibri", Font.BOLD, 20));
        centreWindow();

        // <editor-fold defaultstate="collapsed" desc="Menu">     
        menuFile.setText("Файл");
        miExit.setText("Выход");
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        miPrint.setText("Печать");
        miPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xlsAddData();
                xlsEditData();
                PrintXLS.print(filePath);
            }
        });
        menuFile.add(miPrint);
        menuFile.add(miExit);
        menuBar.add(menuFile);

        menuSettings.setText("Настройки");
        miSettings.setText("Списки");
        miSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DB db = new DB();
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        DialogLists d = new DialogLists(frame, true);
                        d.setVisible(true);
                        d.addWindowListener(new WindowAdapter() {
                            public void windowClosed(WindowEvent evt){
                                setModels();
                            }
                        });
                    }
                });
            }
        });
        menuSettings.add(miSettings);
        menuBar.add(menuSettings);

        setJMenuBar(menuBar);
        // </editor-fold>  

        // <editor-fold defaultstate="collapsed" desc="TopLeftGroupBox">          
        leftGroupBox.setBorder(BorderFactory.createTitledBorder(" "));
        leftGroupBox.setBounds(10, 5, 1030, 160);
        leftGroupBox.setLayout(null);

        //1й столбец
        l1.setBounds(10, 10, 20, 20);
        textNumber.setBounds(10, 30, 240, 20);
        l2.setBounds(10, 60, 240, 20);
        textEquipment.setBounds(10, 80, 240, 20);
        l3.setBounds(10, 110, 240, 20);
        textFunction.setBounds(10, 130, 240, 20);
        leftGroupBox.add(l1);
        leftGroupBox.add(textNumber);
        leftGroupBox.add(l2);
        leftGroupBox.add(textEquipment);
        leftGroupBox.add(l3);
        leftGroupBox.add(textFunction);

        //2й столбец
        l4.setBounds(270, 30, 80, 20);
        cbDevelop.setBounds(350, 30, 140, 20);
        cbDevelop.setEditable(true);
        btnSaveDevelop.setBounds(495, 30, 25, 20);
        btnSaveDevelop.setIcon(new ImageIcon(getClass().getResource("/img/save.png")));
        btnSaveDevelop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DB db = new DB();
                db.addDevelopName(cbDevelop.getSelectedItem().toString());
                cbDevelop.addItem(cbDevelop.getSelectedItem());
                db.close();
            }
        });
        l5.setBounds(270, 70, 180, 20);
        l6.setBounds(270, 100, 180, 20);
        l7.setBounds(270, 130, 180, 20);
        textData1.setBounds(440, 70, 80, 20);
        textData1.setText(getCurrentDate());
        textData2.setBounds(440, 100, 80, 20);
        textData2.setText(getCurrentDate());
        textData3.setBounds(440, 130, 80, 20);
        textData3.setText(getCurrentDate());
        leftGroupBox.add(l4);
        leftGroupBox.add(cbDevelop);
        leftGroupBox.add(btnSaveDevelop);
        leftGroupBox.add(l5);
        leftGroupBox.add(l6);
        leftGroupBox.add(l7);
        leftGroupBox.add(textData1);
        leftGroupBox.add(textData2);
        leftGroupBox.add(textData3);

        //3й столбец
        l8.setBounds(550, 30, 220, 20);
        l9.setBounds(770, 30, 250, 20);
        sp1.setViewportView(textDocuments);
        sp1.setBounds(550, 60, 210, 90);
        textDocuments.setLineWrap(true);
        sp2.setViewportView(textDrawings);
        sp2.setBounds(770, 60, 250, 90);
        textDrawings.setLineWrap(true);
        leftGroupBox.add(l8);
        leftGroupBox.add(l9);
        leftGroupBox.add(sp1);
        leftGroupBox.add(sp2);

        getContentPane().add(leftGroupBox);
        // </editor-fold>    

        // <editor-fold defaultstate="collapsed" desc="TopRightGroupBox">  
        rightGroupBox.setBorder(BorderFactory.createTitledBorder("Компонент"));
        rightGroupBox.setBounds(1045, 5, 190, 160);
        rightGroupBox.setLayout(null);

        l10.setBounds(10, 30, 170, 20);
        textNodeName.setBounds(10, 60, 170, 20);
        btnAddNode.setBounds(10, 90, 170, 20);
        btnAddNode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNode();
            }
        });
        btnDelNode.setBounds(10, 120, 170, 20);
        btnDelNode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delNode();
            }
        });
        rightGroupBox.add(l10);
        rightGroupBox.add(textNodeName);
        rightGroupBox.add(btnAddNode);
        rightGroupBox.add(btnDelNode);

        getContentPane().add(rightGroupBox);
        // </editor-fold> 

        // <editor-fold defaultstate="collapsed" desc="Body">  
        tpMain.setBounds(10, 180, WIDTH - 25, 500);
        getContentPane().add(tpMain);
        // </editor-fold> 

        // <editor-fold defaultstate="collapsed" desc="Bot">  
        statusPanel.setBounds(0, HEIGHT - 70, WIDTH - 5, 20);
        statusPanel.setLayout(null);
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel.setBounds(10, 0, WIDTH - 5, 20);
        statusLabel.setText("...");
        statusPanel.add(statusLabel);
        getContentPane().add(statusPanel);
        // </editor-fold> 
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }

    private String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR);
    }

    private void addNode() {
        String nodeName = textNodeName.getText();
        ComponentsNode c = new ComponentsNode(nodeName);
        arrayNode.add(c);

        c.btnAddPotenc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int currentTab = tpMain.getSelectedIndex();
                int count = (Integer) arrayNode.get(currentTab).causeCount.getValue();
                String namePotencial = arrayNode.get(currentTab).causeName.getSelectedItem().toString();
                int indexNode = currentTab;
                JPanel p = arrayNode.get(currentTab).pan;
                JScrollPane sp = arrayNode.get(currentTab).sp;
                addPotencialCause(indexNode, namePotencial, count, p, sp);
            }
        });
        tpMain.addTab(nodeName, c.sp);
    }

    private void delNode() {
        int index = tpMain.getSelectedIndex(); // Индекс текущей закладки, означает и индекс узла
        arrayNode.remove(index);
        for (int i = 0; i < arrayRoot.size(); i++) {
            if (arrayRoot.get(i).indexNode == index) {
                arrayRoot.remove(i); // Если массив компонент содержит объект с индексом удаляемого узла, то удаляем этот объект
                i--;
            }
        }
        tpMain.remove(index);
    }

    /*Добавление компонент потенциальных причин отказа*/
    private void addPotencialCause(int indexNode, String namePotencial, int count, JPanel p, JScrollPane sp) {
        JPanel pan = new JPanel(null);
        int y = arrayNode.get(tpMain.getSelectedIndex()).y;
        pan.setBorder(BorderFactory.createTitledBorder(namePotencial));
        pan.setBounds(10, y, 1200, 60 + 20 * (count - 1));
        for (int i = 0; i < count; i++) {
            ComponentsRootCauses c = new ComponentsRootCauses(indexNode, namePotencial, pan, i, statusLabel);
            arrayRoot.add(c);
        }
        Dimension d = p.getPreferredSize();
        arrayNode.get(tpMain.getSelectedIndex()).y = y + 60 + 20 * (count - 1);
        d.height = y + 60 + 20 * (count - 1);
        p.setPreferredSize(d);
        p.add(pan);
        p.revalidate();
        p.repaint();
    }

    private void xlsAddData() {
        /*Запись основной части формы*/
        e = new EditXLS();
        for (int i = 0; i < arrayRoot.size(); i++) {
            ComponentsRootCauses c2 = arrayRoot.get(i);
            ComponentsNode c1 = arrayNode.get(c2.indexNode);
            e.addData(c1, c2);
        }

        /*Запись данных из шапки формы*/
        Top.number = textNumber.getText();
        Top.develop = cbDevelop.getSelectedItem().toString();
        Top.equipment = textEquipment.getText();
        Top.function = textFunction.getText();
        Top.data1 = textData1.getText();
        Top.data2 = textData2.getText();
        Top.data3 = textData3.getText();
        Top.documents = textDocuments.getText();
        Top.drawings = textDrawings.getText();
    }

    private void xlsEditData() {
        e.edit(filePath);
    }

    private void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }

    /*Установка моделей данных для листбоксов*/
    private void setModels() {
        db = new DB();
        developersModel = db.getDevelopName();
        potencialModel = db.getPotencialName();
        rootModel = db.getRootName();
        cbDevelop.removeAllItems();
        cbDevelop.setModel(new DefaultComboBoxModel(developersModel));
        db.close();
    }

    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu();
    JMenu menuSettings = new JMenu();
    JMenuItem miExit = new JMenuItem();
    JMenuItem miPrint = new JMenuItem();
    JMenuItem miSettings = new JMenuItem();

    JPanel leftGroupBox = new JPanel();

    JLabel l1 = new JLabel("№");
    JLabel l2 = new JLabel("Оборудование");
    JLabel l3 = new JLabel("Функция агрегата");
    JLabel l4 = new JLabel("Разработал");
    JLabel l5 = new JLabel("Дата составления FMECA");
    JLabel l6 = new JLabel("Дата выдачи рекомендаций");
    JLabel l7 = new JLabel("Дата пересмотра FMECA");
    JLabel l8 = new JLabel("Документы для составления FMECA");
    JLabel l9 = new JLabel("Чертежи и схемы для составления FMECA");
    JTextField textNumber = new JTextField();
    JTextField textEquipment = new JTextField();
    JTextField textFunction = new JTextField();
    JComboBox cbDevelop = new JComboBox();
    JButton btnSaveDevelop = new JButton();
    JTextField textData1 = new JTextField();
    JTextField textData2 = new JTextField();
    JTextField textData3 = new JTextField();
    JTextArea textDocuments = new JTextArea();
    JTextArea textDrawings = new JTextArea();
    JScrollPane sp1 = new JScrollPane();
    JScrollPane sp2 = new JScrollPane();

    JPanel rightGroupBox = new JPanel();
    JLabel l10 = new JLabel("Название узла");
    JTextField textNodeName = new JTextField();
    JButton btnAddNode = new JButton("Добавить узел");
    JButton btnDelNode = new JButton("Удалить узел");

    JTabbedPane tpMain = new JTabbedPane();

    JPanel statusPanel = new JPanel();
    JLabel statusLabel = new JLabel();
}
