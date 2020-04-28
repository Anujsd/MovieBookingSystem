import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;



public class Mvtbook{

	private JFrame frame;
	private JTextField txtSubTotal;
	private JTable table;
	private JPanel homePagePanel1;
	private JLabel headingLabel;
	private JButton bookTicketButton;
	private JTextField inputTextField;
	DefaultTableModel model=new DefaultTableModel();//table model is declared here
	static final String DB_URL = "jdbc:mysql://localhost/movienames";
	 //  Database credentials
	 static final String USER = "root";
	 static final String PASS = "root";
	 Connection conn = null;
	Statement stmt = null;
	PreparedStatement ps=null;
	private JButton addMovieButton;
	/**
	 * Launch the application.
	 */
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mvtbook window = new Mvtbook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mvtbook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel input
		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(0, 0, 589, 414);
		inputPanel.setLayout(null);
		
		JLabel inputLabel = new JLabel("Enter Name of Movie");
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setForeground(Color.RED);
		inputLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		inputLabel.setBounds(0, 11, 589, 117);
		inputPanel.add(inputLabel);
		
		
		inputTextField = new JTextField();
		inputTextField.setBounds(157, 200, 242, 37);
		inputPanel.add(inputTextField);
		inputTextField.setColumns(10);
		
		
		//homepage panel
		homePagePanel1 = new JPanel();
		homePagePanel1.setForeground(Color.BLACK);
		homePagePanel1.setBackground(Color.WHITE);
		homePagePanel1.setBounds(0, 0, 681, 448);
		homePagePanel1.setLayout(null);
		frame.getContentPane().add(homePagePanel1);
		
		bookTicketButton = new JButton("Book Ticket");
		bookTicketButton.setBackground(Color.LIGHT_GRAY);
		bookTicketButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		bookTicketButton.setBounds(260, 203, 134, 42);
		homePagePanel1.add(bookTicketButton);
		
		
		
		headingLabel = new JLabel("Movie Ticket Booking System");
		headingLabel.setBounds(0, 0, 681, 152);
		homePagePanel1.add(headingLabel);
		headingLabel.setBackground(Color.blue);
		headingLabel.setForeground(Color.RED);
		headingLabel.setFont(new Font("Verdana", Font.BOLD, 40));
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JButton inputButton = new JButton("Enter ");
		inputButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		inputButton.setBounds(224, 276, 109, 58);
		inputPanel.add(inputButton);
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=inputTextField.getText();
				try {
					System.out.println("inputButton button pressed");
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
					ps = conn.prepareStatement("insert into mvn values(?)"); 
					ps.setString(1,name);
					ps.executeUpdate();
					JFrame f=new JFrame();  
					JOptionPane.showMessageDialog(f,"Movie name is entered correctly"); 
					frame.setVisible(false);
					frame.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		addMovieButton = new JButton("Add movie");
		addMovieButton.setBackground(Color.LIGHT_GRAY);
		addMovieButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		addMovieButton.setBounds(260, 316, 134, 42);
		homePagePanel1.add(addMovieButton);
		addMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("book Ticket Button pressed");
				frame.getContentPane().removeAll();
				frame.getContentPane().repaint();
				frame.getContentPane().add(inputPanel, BorderLayout.CENTER);
				frame.getContentPane().validate();
				frame.getContentPane().invalidate();
			}
		});
		
		
		//panel 2**************************************
		JPanel moviesPanel2 = new JPanel();
		moviesPanel2.setBounds(0, 0, 1, 395);
		frame.getContentPane().add(moviesPanel2,BorderLayout.CENTER);
		moviesPanel2.setLayout(null);
		
		
		
		String[] pls = new String[] {"Pune", "Mumbai", "Delhi", "Banglore", "Chennai", "Guwahati"};
		DefaultListModel <String>l1=new DefaultListModel<>();
		for(int i=0;i<pls.length;i++)
			l1.addElement(pls[i]);

		
		String []mvs = new String[20];
		int t=0;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM mvn");
			while(rs.next()){
				mvs[t] = rs.getString("name");
				t++;
		      }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		DefaultListModel <String>l2=new DefaultListModel<>();
		for(int i=0;i<mvs.length;i++)
			l2.addElement(mvs[i]);
		JList <String>cityList = new JList<>(l1);
		cityList.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Select City", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		cityList.setBounds(32, 122, 118, 134);
		moviesPanel2.add(cityList);
		JList <String>moviesList = new JList<>(l2);
		moviesList.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Select Movies", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		moviesList.setBounds(205, 122, 144, 180);
		moviesPanel2.add(moviesList);
		
		String[] tms = new String[] {"10:00 am", "12:00 pm", "03:00 pm", "06:00 pm", "09:00 pm"};
		DefaultListModel <String>l3=new DefaultListModel<>();
		for(int i=0;i<tms.length;i++)
			l3.addElement(tms[i]);
		JList <String>timeList = new JList<>(l3);
		timeList.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Select Time", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		timeList.setBounds(407, 122, 110, 116);
		moviesPanel2.add(timeList);
		
		JLabel p2Heading = new JLabel("SELECT ACCORDING TO YOUR CHOICE");
		p2Heading.setHorizontalAlignment(SwingConstants.CENTER);
		p2Heading.setFont(new Font("Tahoma", Font.BOLD, 26));
		p2Heading.setBounds(0, 0, 600, 89);
		moviesPanel2.add(p2Heading);
		
		JButton selectAndNextButton = new JButton("Select and Next");
		selectAndNextButton.setBounds(407, 334, 144, 23);
		moviesPanel2.add(selectAndNextButton);
		//panel 2*************************************
		
		
		//panel 3**********************************
		JPanel bookingPanel3 = new JPanel();
		bookingPanel3.setForeground(Color.BLACK);
		bookingPanel3.setBackground(Color.WHITE);
		
		bookingPanel3.setLayout(null);
		
		JLabel seatBookingHeadingLabel = new JLabel("Seat Booking Area");
		seatBookingHeadingLabel.setForeground(Color.RED);
		seatBookingHeadingLabel.setBackground(Color.BLUE);
		seatBookingHeadingLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		seatBookingHeadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		seatBookingHeadingLabel.setBounds(0, 0, 681, 47);
		bookingPanel3.add(seatBookingHeadingLabel);
		
		JPanel InternalPanel = new JPanel();
		InternalPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		InternalPanel.setBounds(25, 92, 233, 218);
		bookingPanel3.add(InternalPanel);
		InternalPanel.setLayout(null);
		
		JCheckBox chk1 = new JCheckBox("ODC");
		chk1.setBounds(17, 36, 97, 23);
		InternalPanel.add(chk1);
		
		JCheckBox chk2 = new JCheckBox("Balcony");
		chk2.setBounds(17, 78, 97, 23);
		InternalPanel.add(chk2);
		
		JCheckBox chk3 = new JCheckBox("Box");
		chk3.setBounds(17, 116, 97, 23);
		InternalPanel.add(chk3);
		
		JCheckBox chk4 = new JCheckBox("Super Balcony ");
		chk4.setBounds(17, 156, 97, 23);
		InternalPanel.add(chk4);
		
		JSpinner txtOdc = new JSpinner();
		txtOdc.setBounds(158, 37, 30, 20);
		InternalPanel.add(txtOdc);
		
		JSpinner txtBalcony = new JSpinner();
		txtBalcony.setBounds(158, 79, 30, 20);
		InternalPanel.add(txtBalcony);
		
		JSpinner txtBox = new JSpinner();
		txtBox.setBounds(158, 117, 30, 20);
		InternalPanel.add(txtBox);
		
		JSpinner txtSBalcony = new JSpinner();
		txtSBalcony.setBounds(158, 157, 30, 20);
		InternalPanel.add(txtSBalcony);
		
		JButton totalButton = new JButton("Total");
		totalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sum=0,pr,qua,tot;
				if(chk1.isSelected())
				{
					String type=chk1.getText();
					pr=300;
					qua=Integer.parseInt(txtOdc.getValue().toString());
					tot=pr*qua;
					model=( DefaultTableModel)table.getModel();
					model.addRow(new Object[]{
							type,
							pr,
							qua,
							tot
					});
				}
				if(chk2.isSelected())
				{
					String type=chk2.getText();
					pr=350;
					qua=Integer.parseInt(txtBalcony.getValue().toString());
					tot=pr*qua;
					model=( DefaultTableModel)table.getModel();
					model.addRow(new Object[]{
							type,
							pr,
							qua,
							tot
					});
				}
				if(chk3.isSelected())
				{
					String type=chk3.getText();
					pr=400;
					qua=Integer.parseInt(txtBox.getValue().toString());
					tot=pr*qua;
					model=( DefaultTableModel)table.getModel();
					model.addRow(new Object[]{
							type,
							pr,
							qua,
							tot
					});
				}
				if(chk4.isSelected())
				{
					String type=chk4.getText();
					pr=500;
					qua=Integer.parseInt(txtSBalcony.getValue().toString());
					tot=pr*qua;
					model=( DefaultTableModel)table.getModel();
					model.addRow(new Object[]{
							type,
							pr,
							qua,
							tot
					});
				}
				for(int i=1;i<table.getRowCount();i++)
				{
					sum+=Integer.parseInt(table.getValueAt(i, 3).toString());
				}
				txtSubTotal.setText(String.valueOf(sum));
			}
		});
		totalButton.setBounds(25, 360, 89, 23);
		bookingPanel3.add(totalButton);
		
		JLabel subtotalLabel = new JLabel("Subtotal");
		subtotalLabel.setBounds(388, 366, 67, 14);
		bookingPanel3.add(subtotalLabel);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(488, 363, 86, 20);
		bookingPanel3.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSurrendersFocusOnKeystroke(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Seat Type", "Price", "Quantity", "Total"},
			},
			new String[] {
				"Seat Type", "Price", "Quantity", "Total"
			}
		));
		table.setBackground(Color.CYAN);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBounds(332, 92, 326, 219);
		bookingPanel3.add(table);
		//panel3 end*********************
		
		
		
		JButton payAndExitButton = new JButton("pay and exit");
		payAndExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Payexit button pressed");
				JFrame f=new JFrame();  
				JOptionPane.showMessageDialog(f,"Amount is paid");  
				 frame.setVisible(false);
				 frame.dispose();
			}
		});
		payAndExitButton.setBounds(145, 360, 109, 23);
		bookingPanel3.add(payAndExitButton);
		
		
		//button click event
		selectAndNextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("select and next button pressed");
				frame.remove(moviesPanel2);
				frame.getContentPane().add(bookingPanel3, BorderLayout.CENTER);
				frame.validate();
				frame.invalidate();
			}
		});
		
		
		bookTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("add movie Button pressed");
				
				frame.getContentPane().removeAll();
				frame.getContentPane().repaint();
				frame.getContentPane().add(moviesPanel2, BorderLayout.CENTER);
				frame.getContentPane().validate();
				frame.getContentPane().invalidate();
				
			}
		});
		

	}
}
