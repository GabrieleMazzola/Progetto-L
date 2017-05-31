package gui;

import exceptions.TicketTypeNotFoundException;
import ticket.TicketType;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import machines.TicketMachine;
import paymentMethods.PaymentMethod;

/**
 *
 * @author Manuele
 */
public class MainMachineSwingPanel extends JPanel implements Observer{
    private JButton bottoneCompra;
    private JPanel pannelloBottoni;
    private final JButton bottoneDueEuro, bottoneUnEuro, bottoneCinquantaCent,
                    bottoneVentiCent, bottoneDieciCent, bottoneCinqueCent;
    private JLabel displayTotaleInserito;
    private JPanel pannelloSoldi;
    
    private TicketType type;
    private PaymentMethod paymentMethod;
    
    private TicketMachine tMachine;
    
    public MainMachineSwingPanel(TicketMachine tMachine) {
        super(new BorderLayout());
        
        this.tMachine = tMachine;
        tMachine.addObserver(this);
        
        paymentMethod = PaymentMethod.CASH;
        tMachine.setPaymentMethod(paymentMethod);
        
        bottoneCompra = new JButton("Single Ticket");
        bottoneCompra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try{
                    tMachine.setTicketToSell("Single");
                    tMachine.buyTicket();
                
                    tMachine.setTicketToSell("Single");
                    tMachine.buyTicket();
                }
                catch(TicketTypeNotFoundException exc) {
                        
                }
            }
        });
        pannelloBottoni = new JPanel();
        pannelloBottoni.add(bottoneCompra);
        
        bottoneDueEuro = new JButton("2€");
        bottoneUnEuro = new JButton("1€");
        bottoneCinquantaCent = new JButton("0,50€");
        bottoneVentiCent = new JButton("0,20€");
        bottoneDieciCent = new JButton("0,10€");
        bottoneCinqueCent = new JButton("0,05€");
        
        bottoneDueEuro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tMachine.insertMoney(2.0);
            }
        });
        
        bottoneUnEuro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tMachine.insertMoney(1.0);
            }
        });
        
        bottoneCinquantaCent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tMachine.insertMoney(0.5f);
            }
        });
        
        bottoneVentiCent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tMachine.insertMoney(0.2f);
            }
        });
        
        bottoneDieciCent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tMachine.insertMoney(0.1f);
            }
        });
        
        bottoneCinqueCent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                tMachine.insertMoney(0.05f);
            }
        });
        
        displayTotaleInserito = new JLabel(tMachine.getInsertedMoney() + "");
        
        JPanel pannelloBottoniSoldi = new JPanel(new GridLayout(2, 3));
        pannelloBottoniSoldi.add(bottoneDueEuro);
        pannelloBottoniSoldi.add(bottoneUnEuro);
        pannelloBottoniSoldi.add(bottoneCinquantaCent);
        pannelloBottoniSoldi.add(bottoneVentiCent);
        pannelloBottoniSoldi.add(bottoneDieciCent);
        pannelloBottoniSoldi.add(bottoneCinqueCent);
        
        pannelloSoldi = new JPanel(new BorderLayout());
        pannelloSoldi.add(pannelloBottoniSoldi);
        pannelloSoldi.add(displayTotaleInserito, BorderLayout.PAGE_START);
        
        this.add(pannelloBottoni);
        this.add(pannelloSoldi, BorderLayout.EAST);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        displayTotaleInserito.setText(tMachine.getInsertedMoney() + "");
    }
}
