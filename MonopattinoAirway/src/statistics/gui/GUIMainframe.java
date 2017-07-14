
package statistics.gui;

import java.io.IOException;
import statistics.InformationHandler;

/**
 *
 * @author Zubeer
 */
public class GUIMainframe extends javax.swing.JFrame {

    
    InformationHandler statistics;
    int xx, xy;

    /**
     *
     */
    public GUIMainframe() {
        try {
            statistics = new InformationHandler(GuiStart.CSYSTEM_IP);
        } catch (IOException ex) {
            System.err.println("ERRORE APERTURA INFORMATION HANDLER");
        }
        initComponents();
        
    }

              
    private void initComponents() {

        pnl_footer = new javax.swing.JPanel();
        lbl_project = new javax.swing.JLabel();
        pnl_1 = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        Title1 = new javax.swing.JLabel();
        icn_analysis = new javax.swing.JLabel();
        pnl_2 = new javax.swing.JPanel();
        icn_machine = new javax.swing.JLabel();
        icn_user = new javax.swing.JLabel();
        lbl_user = new javax.swing.JLabel();
        lbl_machine = new javax.swing.JLabel();
        pnl_3 = new javax.swing.JPanel();
        pnl_bar = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_footer.setBackground(new java.awt.Color(22, 27, 33));

        lbl_project.setFont(new java.awt.Font("Segoe UI", 0, 12)); 
        lbl_project.setForeground(new java.awt.Color(153, 153, 153));
        lbl_project.setText("© Progetto L");

        javax.swing.GroupLayout pnl_footerLayout = new javax.swing.GroupLayout(pnl_footer);
        pnl_footer.setLayout(pnl_footerLayout);
        pnl_footerLayout.setHorizontalGroup(
            pnl_footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_footerLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbl_project, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(956, Short.MAX_VALUE))
        );
        pnl_footerLayout.setVerticalGroup(
            pnl_footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_footerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_project, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        getContentPane().add(pnl_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 1120, 90));

        pnl_1.setBackground(new java.awt.Color(54, 63, 73));

        Title.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 24)); 
        Title.setForeground(new java.awt.Color(48, 201, 235));
        Title.setText("System");

        Title1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 36)); 
        Title1.setForeground(new java.awt.Color(48, 201, 235));
        Title1.setText("Statistics");

        icn_analysis.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/System Task_32px.png"))); 

        javax.swing.GroupLayout pnl_1Layout = new javax.swing.GroupLayout(pnl_1);
        pnl_1.setLayout(pnl_1Layout);
        pnl_1Layout.setHorizontalGroup(
            pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(Title1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(icn_analysis, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(767, Short.MAX_VALUE))
        );
        pnl_1Layout.setVerticalGroup(
            pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(icn_analysis, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Title)
                        .addComponent(Title1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1120, 270));

        pnl_2.setBackground(new java.awt.Color(41, 48, 56));

        icn_machine.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Vending Machine_64px.png"))); 
        icn_machine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icn_machineMouseClicked(evt);
            }
        });

        icn_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/User Groups_64px.png"))); 
        icn_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icn_userMouseClicked(evt);
            }
        });

        lbl_user.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        lbl_user.setForeground(new java.awt.Color(48, 201, 235));
        lbl_user.setText("Tickets sold to non-logged in users");

        lbl_machine.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        lbl_machine.setForeground(new java.awt.Color(48, 201, 235));
        lbl_machine.setText("Tickets sold to logged in users ");

        javax.swing.GroupLayout pnl_2Layout = new javax.swing.GroupLayout(pnl_2);
        pnl_2.setLayout(pnl_2Layout);
        pnl_2Layout.setHorizontalGroup(
            pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_2Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(lbl_machine)
                .addGap(182, 182, 182)
                .addComponent(lbl_user)
                .addContainerGap(199, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_2Layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(icn_machine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(icn_user)
                .addGap(299, 299, 299))
        );
        pnl_2Layout.setVerticalGroup(
            pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icn_machine)
                    .addComponent(icn_user))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_user)
                    .addComponent(lbl_machine))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 1120, 290));

        pnl_3.setBackground(new java.awt.Color(54, 63, 73));

        javax.swing.GroupLayout pnl_3Layout = new javax.swing.GroupLayout(pnl_3);
        pnl_3.setLayout(pnl_3Layout);
        pnl_3Layout.setHorizontalGroup(
            pnl_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
        );
        pnl_3Layout.setVerticalGroup(
            pnl_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 1120, 90));

        pnl_bar.setBackground(new java.awt.Color(22, 27, 33));
        pnl_bar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnl_barMouseDragged(evt);
            }
        });
        pnl_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_barMouseClicked(evt);
            }
        });

        exit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("./icons/Delete_18px.png"))); 
        exit.setText("jLabel1");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_barLayout = new javax.swing.GroupLayout(pnl_bar);
        pnl_bar.setLayout(pnl_barLayout);
        pnl_barLayout.setHorizontalGroup(
            pnl_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_barLayout.createSequentialGroup()
                .addGap(0, 1098, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_barLayout.setVerticalGroup(
            pnl_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_barLayout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(exit))
        );

        getContentPane().add(pnl_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 20));

        setSize(new java.awt.Dimension(1120, 755));
        setLocationRelativeTo(null);
    }                      

    private void pnl_barMouseClicked(java.awt.event.MouseEvent evt) {                                     
        xx = evt.getX ();
        xy = evt.getY ();
    }                                    

    
    
    
    
    
    private void pnl_barMouseDragged(java.awt.event.MouseEvent evt) {                                     
       int x = evt.getXOnScreen ();
       int y = evt.getYOnScreen();
        
       this.setLocation(x - xx, y - xy);
    }                                    

    
    private void exitMouseClicked(java.awt.event.MouseEvent evt) {                                  
        System.exit(0);
    }                                 

    
    private void icn_machineMouseClicked(java.awt.event.MouseEvent evt) {                                         
        this.setVisible(false);
        LoggedUsers m = new LoggedUsers (statistics);
        m.setVisible (true);
    }                                        

    
    private void icn_userMouseClicked(java.awt.event.MouseEvent evt) {                                      
        this.setVisible(false);
        NonLoggedUsers nu = new NonLoggedUsers (statistics);
        nu.setVisible (true);
    }                                     



                    
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Title1;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel icn_analysis;
    private javax.swing.JLabel icn_machine;
    private javax.swing.JLabel icn_user;
    private javax.swing.JLabel lbl_machine;
    private javax.swing.JLabel lbl_project;
    private javax.swing.JLabel lbl_user;
    private javax.swing.JPanel pnl_1;
    private javax.swing.JPanel pnl_2;
    private javax.swing.JPanel pnl_3;
    private javax.swing.JPanel pnl_bar;
    private javax.swing.JPanel pnl_footer;
                  
}
