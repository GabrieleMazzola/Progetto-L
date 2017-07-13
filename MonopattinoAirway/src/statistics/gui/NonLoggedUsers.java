package statistics.gui;
import items.Sale;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import statistics.gui.GUIMainframe;
import statistics.InformationHandler;

/**
 *
 * @author Zubeer
 */
public class NonLoggedUsers extends javax.swing.JFrame {
    
   
    private String[] args;
    InformationHandler statistics;
    JFreeChart pie;
    ChartPanel cp;
    DefaultTableModel model;
    int xx, xy;
    
    /**
     *
     * @param statistics
     */
    public NonLoggedUsers(InformationHandler statistics) {
        this.statistics = statistics;
        initComponents();
        createPie();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                updatePage();
                addRowToJtable();
            }
            
        }, 10, 3000);      
    }    
    
    
    
   
        private void createPie(){
            DefaultPieDataset dataset = statistics.cookNonloggedPie();
            pie = ChartFactory.createPieChart("Non-logged Sales",dataset,true,true,false);

            cp = new ChartPanel(pie);
            pnl_chart.add(cp);
        }

    /**
     *
     */
    public void addRowToJtable(){
            model = (DefaultTableModel) tbl_data.getModel();
            List<Sale> list = statistics.getSaleUnloggedList();
            Object rowData[] = new Object[4];
            while(model.getRowCount()>0){
                model.removeRow(0);
        }
        for(Sale s : list){
            rowData[0] = s.getSellerMachineIp();
            rowData[1] = s.getSerialCode();
            rowData[2] = s.getSaleDate().toString();
            rowData[3] = s.getProduct().getType();
            model.addRow(rowData);  
        }
    }
    
    /**
     *
     */
    public void updatePage(){
            statistics.update();
            pnl_chart.remove(cp);
            pnl_chart.setVisible(false);
            createPie();
            pnl_chart.setVisible(true);
    }  



   
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnl_sidebar = new javax.swing.JPanel();
        lbl_north = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        pnl_2 = new javax.swing.JPanel();
        lbl_footer = new javax.swing.JLabel();
        lbl_Refresh = new javax.swing.JLabel();
        pnl_chart = new javax.swing.JPanel();
        pnl_table = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_data = new javax.swing.JTable();
        pnl_3 = new javax.swing.JPanel();
        delete = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnl_sidebar.setBackground(new java.awt.Color(34, 41, 50));

        lbl_north.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        lbl_north.setForeground(new java.awt.Color(48, 201, 235));
        lbl_north.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Two Tickets_18px.png")));
        lbl_north.setText("Ticket sold to non-logged in users");

        home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Home_18px.png")));
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        pnl_2.setBackground(new java.awt.Color(22, 27, 33));

        lbl_footer.setFont(new java.awt.Font("Segoe UI", 0, 12)); 
        lbl_footer.setForeground(new java.awt.Color(153, 153, 153));
        lbl_footer.setText("© Progetto L");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(pnl_2);
        pnl_2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbl_footer, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_footer, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        lbl_Refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Refresh_18px.png")));
        lbl_Refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_RefreshMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_sidebarLayout = new javax.swing.GroupLayout(pnl_sidebar);
        pnl_sidebar.setLayout(pnl_sidebarLayout);
        pnl_sidebarLayout.setHorizontalGroup(
            pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl_sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_Refresh)
                .addContainerGap())
            .addGroup(pnl_sidebarLayout.createSequentialGroup()
                .addComponent(lbl_north)
                .addGap(0, 28, Short.MAX_VALUE))
        );
        pnl_sidebarLayout.setVerticalGroup(
            pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(164, 164, 164)
                .addComponent(lbl_north, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                .addComponent(pnl_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnl_chart.setBackground(new java.awt.Color(54, 63, 73));
        pnl_chart.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tbl_data.setFont(new java.awt.Font("Segoe UI", 0, 16)); 
        tbl_data.setForeground(new java.awt.Color(51, 51, 51));
        tbl_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ip", "Serial Code", "Sale Date", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_data.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_data.setSelectionBackground(new java.awt.Color(62, 226, 141));
        tbl_data.setShowHorizontalLines(false);
        tbl_data.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tbl_data);

        javax.swing.GroupLayout pnl_tableLayout = new javax.swing.GroupLayout(pnl_table);
        pnl_table.setLayout(pnl_tableLayout);
        pnl_tableLayout.setHorizontalGroup(
            pnl_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        pnl_tableLayout.setVerticalGroup(
            pnl_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pnl_3.setBackground(new java.awt.Color(22, 27, 33));
        pnl_3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnl_3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        pnl_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });
        pnl_3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Delete_18px.png"))); 
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        pnl_3.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 20, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnl_sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_3, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnl_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_chart, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnl_sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1155, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(1154, 762));
        setLocationRelativeTo(null);
    }

    
    
    
    
    
    
    
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
        GUIMainframe ma = new GUIMainframe ();
        ma.setVisible (true);
    }
    
    

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    
    
    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen ();
        int y = evt.getYOnScreen();

        this.setLocation(x - xx, y - xy);
    }

    
    
    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {
        xx = evt.getX ();
        xy = evt.getY ();
    }

    
    
    private void lbl_RefreshMouseClicked(java.awt.event.MouseEvent evt) {
        updatePage();

    }
    
    
    
    

    
    
    private javax.swing.JLabel home;
    private javax.swing.JLabel lbl_footer;
    private javax.swing.JLabel delete;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnl_2;
    private javax.swing.JPanel pnl_3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_data;
    private javax.swing.JLabel lbl_Refresh;
    private javax.swing.JLabel lbl_north;
    private javax.swing.JPanel pnl_chart;
    private javax.swing.JPanel pnl_sidebar;
    private javax.swing.JPanel pnl_table;

}
