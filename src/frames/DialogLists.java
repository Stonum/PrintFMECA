package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import printfmeca.DB;

/**
 * @author Stonum
 */
public class DialogLists extends JDialog {

    DB db;
    static int HEIGHT = 500, WIDTH = 450;
    Vector developersModel;
    Vector potencialModel;
    Vector rootModel;

    public DialogLists(Frame parent, boolean modal) {
        super(parent, modal);
        db = new DB();
        developersModel = db.getDevelopName();
        potencialModel = db.getPotencialName();
        rootModel = db.getRootName();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Списки");
        setSize(WIDTH, HEIGHT);
        getContentPane().setLayout(null);
        centreWindow();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                db.close();
            }
        });

        developPanel();
        potencialPanel();
        rootPanel();

        tabs.setBounds(10, 5, WIDTH - 25, HEIGHT - 40);
        tabs.addTab("Разработчики", panelDevelopers);
        tabs.addTab("Потенциальные причины", panelPotencial);
        tabs.addTab("Корневые причины", panelRoot);
        getContentPane().add(tabs);
    }

    private void centreWindow() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }

    private void developPanel() {
        textDeveloper.setBounds(10, HEIGHT - 95, WIDTH - 120, 25);
        btnAddDeveloper.setBounds(WIDTH - 100, HEIGHT - 95, 25, 25);
        btnAddDeveloper.setIcon(new ImageIcon(getClass().getResource("/img/add.png")));
        btnAddDeveloper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                developersModel.add(textDeveloper.getText());
                listDevelopers.setListData(developersModel);
                db.addDevelopName(textDeveloper.getText());
                textDeveloper.setText("");
            }
        });
        btnDelDeveloper.setBounds(WIDTH - 65, HEIGHT - 95, 25, 25);
        btnDelDeveloper.setIcon(new ImageIcon(getClass().getResource("/img/del.png")));
        btnDelDeveloper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db.delDevelopName(textDeveloper.getText());
                developersModel.remove(listDevelopers.getSelectedIndex());
                textDeveloper.setText("");
                listDevelopers.setListData(developersModel);
            }
        });
        listDevelopers.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        listDevelopers.setListData(developersModel);
        listDevelopers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int index = listDevelopers.getSelectedIndex();
                if(index != -1){
                    textDeveloper.setText(developersModel.get(index).toString());
                } else {
                    textDeveloper.setText("");
                }
            }
        });
        spDevelop.setBounds(10, 10, WIDTH - 50, HEIGHT - 110);
        spDevelop.setViewportView(listDevelopers);
        panelDevelopers.add(textDeveloper);
        panelDevelopers.add(btnAddDeveloper);
        panelDevelopers.add(btnDelDeveloper);
        panelDevelopers.add(spDevelop);
    }

    private void potencialPanel() {
        textPotencial.setBounds(10, HEIGHT - 95, WIDTH - 120, 25);
        btnAddPotencial.setBounds(WIDTH - 100, HEIGHT - 95, 25, 25);
        btnAddPotencial.setIcon(new ImageIcon(getClass().getResource("/img/add.png")));
        btnAddPotencial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                potencialModel.add(textPotencial.getText());
                listPotencial.setListData(potencialModel);
                db.addPotencialName(textPotencial.getText());
                textPotencial.setText("");
            }
        });
        btnDelPotencial.setBounds(WIDTH - 65, HEIGHT - 95, 25, 25);
        btnDelPotencial.setIcon(new ImageIcon(getClass().getResource("/img/del.png")));
        btnDelPotencial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db.delPotencialname(textPotencial.getText());
                potencialModel.remove(listPotencial.getSelectedIndex());
                textPotencial.setText("");
                listPotencial.setListData(potencialModel);
            }
        });
        listPotencial.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        listPotencial.setListData(potencialModel);
        listPotencial.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int index = listPotencial.getSelectedIndex();
                if(index != -1){
                    textPotencial.setText(potencialModel.get(index).toString());
                } else {
                    textPotencial.setText("");
                }
            }
        });
        spPotencial.setBounds(10, 10, WIDTH - 50, HEIGHT - 110);
        spPotencial.setViewportView(listPotencial);
        panelPotencial.add(textPotencial);
        panelPotencial.add(btnAddPotencial);
        panelPotencial.add(btnDelPotencial);
        panelPotencial.add(spPotencial);
    }

    private void rootPanel() {
        textRoot.setBounds(10, HEIGHT - 95, WIDTH - 120, 25);
        btnAddRoot.setBounds(WIDTH - 100, HEIGHT - 95, 25, 25);
        btnAddRoot.setIcon(new ImageIcon(getClass().getResource("/img/add.png")));
        btnAddRoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rootModel.add(textRoot.getText());
                listRoot.setListData(rootModel);
                db.addRootName(textRoot.getText());
                textRoot.setText("");
            }
        });
        btnDelRoot.setBounds(WIDTH - 65, HEIGHT - 95, 25, 25);
        btnDelRoot.setIcon(new ImageIcon(getClass().getResource("/img/del.png")));
        btnDelRoot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db.delRootName(textRoot.getText());
                rootModel.remove(listRoot.getSelectedIndex());
                textRoot.setText("");
                listRoot.setListData(rootModel);
            }
        });
        listRoot.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        listRoot.setListData(rootModel);
        listRoot.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int index = listRoot.getSelectedIndex();
                if(index != -1){
                    textRoot.setText(rootModel.get(index).toString());
                } else {
                    textRoot.setText("");
                }
            }
        });
        spRoot.setBounds(10, 10, WIDTH - 50, HEIGHT - 110);
        spRoot.setViewportView(listRoot);
        panelRoot.add(textRoot);
        panelRoot.add(btnAddRoot);
        panelRoot.add(btnDelRoot);
        panelRoot.add(spRoot);
    }
    
    

    JTabbedPane tabs = new JTabbedPane();
    JPanel panelDevelopers = new JPanel(null);
    JPanel panelPotencial = new JPanel(null);
    JPanel panelRoot = new JPanel(null);
    JList listDevelopers = new JList();
    JList listPotencial = new JList();
    JList listRoot = new JList();
    JButton btnAddDeveloper = new JButton();
    JButton btnDelDeveloper = new JButton();
    JButton btnAddPotencial = new JButton();
    JButton btnDelPotencial = new JButton();
    JButton btnAddRoot = new JButton();
    JButton btnDelRoot = new JButton();
    JTextField textDeveloper = new JTextField();
    JTextField textPotencial = new JTextField();
    JTextField textRoot = new JTextField();
    JScrollPane spDevelop = new JScrollPane();
    JScrollPane spPotencial = new JScrollPane();
    JScrollPane spRoot = new JScrollPane();

}
