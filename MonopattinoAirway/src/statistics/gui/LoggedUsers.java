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
import statistics.InformationHandler;
import statistics.gui.GUIMainframe;


public class LoggedUsers extends javax.swing.JFrame {

    private String[] args;
    InformationHandler statistics;
    JFreeChart pie;
    ChartPanel cp;
    DefaultTableModel model;
    int xx, xy;
    
    /**
     *Sezione dedicata agli user registrati con aggiornamento automatico della pagina per la creazione del grafico a torta
     * @param statistics
     */
    public LoggedUsers(InformationHandler statistics) {
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
            DefaultPieDataset dataset = statistics.cookLoggedPie();
            pie = ChartFactory.createPieChart("Logged Sales",dataset,true,true,false);

            cp = new ChartPanel(pie);
            pnl_chart.add(cp);

        }
        
    /**
     * Aggiornamento della pagina relativa alle vendite degli user registrati
     */
    public void updatePage(){
            statistics.update();
            pnl_chart.remove(cp);
            pnl_chart.setVisible(false);
            createPie();
            pnl_chart.setVisible(true);
    }  

    /**
     *Aggiunta dinamica delle righe alla tabella 
     */
    public void addRowToJtable(){
        model = (DefaultTableModel) pnl_jtable.getModel();
        List<Sale> list = statistics.getSaleLoggedList();
        Object rowData[] = new Object[6];
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        for(Sale s : list){
            rowData[0] = s.getSellerMachineIp();
            rowData[1] = s.getSerialCode();
            rowData[2] = s.getSaleDate().toString();
            rowData[3] = s.getUsername();
            rowData[4] = s.getType();
            rowData[5] = s.getProduct().getDescription();
            
            model.addRow(rowData);  
        }
        
    }

    
    
        private void initComponents() {
            

        pnl_sidebar = new javax.swing.JPanel();
        lbl_north = new javax.swing.JLabel();
        lbl_Home = new javax.swing.JLabel();
        pnl_1 = new javax.swing.JPanel();
        lbl_footer = new javax.swing.JLabel();
        lbl_Refresh = new javax.swing.JLabel();
        pnl_chart = new javax.swing.JPanel();
        pnl_table = new javax.swing.JPanel();
        pnl_scroll = new javax.swing.JScrollPane();
        pnl_jtable = new javax.swing.JTable();
        pnl_2 = new javax.swing.JPanel();
        lbl_close = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(54, 63, 73));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setUndecorated(true);

        pnl_sidebar.setBackground(new java.awt.Color(34, 41, 50));

        lbl_north.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lbl_north.setForeground(new java.awt.Color(48, 201, 235));
        lbl_north.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Two Tickets_18px.png"))); 
        lbl_north.setText("Sold to logged in users");

        lbl_Home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Home_18px.png"))); 
        lbl_Home.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HomeMouseClicked(evt);
            }
        });

        pnl_1.setBackground(new java.awt.Color(22, 27, 33));

        lbl_footer.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lbl_footer.setForeground(new java.awt.Color(153, 153, 153));
        lbl_footer.setText("© Progetto L");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pnl_1);
        pnl_1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbl_footer, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
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
            .addComponent(pnl_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl_sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_north, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Home, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_Refresh)
                .addContainerGap())
        );
        pnl_sidebarLayout.setVerticalGroup(
            pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_Refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_Home, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(164, 164, 164)
                .addComponent(lbl_north)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
                .addComponent(pnl_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnl_chart.setBackground(new java.awt.Color(54, 63, 73));
        pnl_chart.setLayout(new java.awt.BorderLayout());

        pnl_scroll.setBackground(new java.awt.Color(255, 255, 255));
        pnl_scroll.setBorder(null);
        
        
        pnl_jtable.setFont(new java.awt.Font("Segoe UI", 0, 16)); 
        pnl_jtable.setForeground(new java.awt.Color(51, 51, 51));
        pnl_jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ip", "Serial Code", "Sale Date", "Username", "Type" , "Description"
            }
                        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });


        pnl_jtable.setGridColor(new java.awt.Color(255, 255, 255));
        pnl_jtable.setSelectionBackground(new java.awt.Color(62, 226, 141));
        pnl_jtable.setShowHorizontalLines(false);
        pnl_jtable.setShowVerticalLines(false);
        pnl_scroll.setViewportView(pnl_jtable);

        javax.swing.GroupLayout pnl_tableLayout = new javax.swing.GroupLayout(pnl_table);
        pnl_table.setLayout(pnl_tableLayout);
        pnl_tableLayout.setHorizontalGroup(
            pnl_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_scroll)
        );
        pnl_tableLayout.setVerticalGroup(
            pnl_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_scroll)
        );

        pnl_2.setBackground(new java.awt.Color(22, 27, 33));
        pnl_2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnl_2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        pnl_2.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        pnl_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Delete_18px.png")));
        lbl_close.addMouseListener(new java.awt.event.MouseAdapter() {
        
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        pnl_2.add(lbl_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 20, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnl_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_chart, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnl_sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1111, 754));
        setLocationRelativeTo(null);
        
        
        
    }   
        


    
    
    
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {                                     
        System.exit(0);
    }                                    

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {                                     
        xx = evt.getX ();
        xy = evt.getY ();
    }                                    

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {                                     
       int x = evt.getXOnScreen ();
       int y = evt.getYOnScreen();
        
       this.setLocation(x - xx, y - xy);
    }                                    

    private void lbl_HomeMouseClicked(java.awt.event.MouseEvent evt) {                                      
        this.setVisible(false);
        GUIMainframe ma = new GUIMainframe ();
        ma.setVisible (true);
    }                                     

    private void lbl_RefreshMouseClicked(java.awt.event.MouseEvent evt) {                                         
        updatePage();
    }                                        
    

    
    
    
    
    private javax.swing.JLabel lbl_footer;
    private javax.swing.JLabel lbl_close;
    private javax.swing.JPanel pnl_1;
    private javax.swing.JPanel pnl_2;
    private javax.swing.JScrollPane pnl_scroll;
    private javax.swing.JTable pnl_jtable;
    private javax.swing.JLabel lbl_Home;
    private javax.swing.JLabel lbl_Refresh;
    private javax.swing.JLabel lbl_north;
    private javax.swing.JPanel pnl_chart;
    private javax.swing.JPanel pnl_sidebar;
    private javax.swing.JPanel pnl_table;
    
}
