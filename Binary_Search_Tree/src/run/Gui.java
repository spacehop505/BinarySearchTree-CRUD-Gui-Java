package run;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tree.BinarySearchTree;
import tree.BinarySearchTree.Node;

public class Gui {
	BinarySearchTree bnObj = new BinarySearchTree();
	private JFrame frame;
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private JTextField name1;
	private JTextField price1;
	private JTextField quantity1;
	private JTextField id3;
	private JTextField id4;
	private JTextField name2;
	private JTextField price2;
	private JTextField quantity2;
	private JLabel warning1;
	private JLabel warning2;
	private JLabel warning4;
	private JTextField id2;
	private JTextField key1;
	private JLabel warning3;

	public Gui() {
		frame = new JFrame();
		frame.setBounds(100, 100, 873, 669);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(228, 107, 400, 250);
		frame.getContentPane().add(scrollPane);

		defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Key");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("Price");
		defaultTableModel.addColumn("Quantity");
		table = new JTable(defaultTableModel);

		scrollPane.setViewportView(table);

		JButton createBtn = new JButton("Create");
		createBtn.setBackground(SystemColor.inactiveCaption);
		createBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					warning1.setText("");
					Integer key = Integer.parseInt(key1.getText());
					String name = name1.getText();
					Double price = Double.parseDouble(price1.getText());
					Integer quantity = Integer.parseInt(quantity1.getText());

					bnObj.insertRootNode(key, name, price, quantity);

					key1.setText("");
					name1.setText("");
					price1.setText("");
					quantity1.setText("");
					table();
				} catch (NumberFormatException e1) {
					warning1.setText("NumberFormatException");
				}
			}
		});
		createBtn.setBounds(20, 220, 180, 30);
		frame.getContentPane().add(createBtn);

		name1 = new JTextField();
		name1.setBounds(100, 100, 100, 30);
		frame.getContentPane().add(name1);
		name1.setColumns(10);

		price1 = new JTextField();
		price1.setColumns(10);
		price1.setBounds(100, 140, 100, 30);
		frame.getContentPane().add(price1);

		quantity1 = new JTextField();
		quantity1.setColumns(10);
		quantity1.setBounds(100, 180, 100, 30);
		frame.getContentPane().add(quantity1);

		JLabel lblname1 = new JLabel("Name:");
		lblname1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1.setBounds(20, 100, 80, 30);
		frame.getContentPane().add(lblname1);

		JLabel lnlprice1 = new JLabel("Price:");
		lnlprice1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lnlprice1.setBounds(20, 140, 80, 30);
		frame.getContentPane().add(lnlprice1);

		JLabel lblQuantity1 = new JLabel("Quantity:");
		lblQuantity1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantity1.setBounds(20, 180, 80, 30);
		frame.getContentPane().add(lblQuantity1);

		JLabel lblCreateFruit = new JLabel("Create Node");
		lblCreateFruit.setForeground(Color.BLUE);
		lblCreateFruit.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCreateFruit.setBounds(20, 30, 180, 30);
		frame.getContentPane().add(lblCreateFruit);

		JLabel lblSelectAFruit = new JLabel("Binary Search Tree");
		lblSelectAFruit.setForeground(new Color(0, 0, 0));
		lblSelectAFruit.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblSelectAFruit.setBounds(317, 70, 222, 30);
		frame.getContentPane().add(lblSelectAFruit);

		JLabel lblDeleteFruit = new JLabel("Delete Node");
		lblDeleteFruit.setForeground(Color.BLUE);
		lblDeleteFruit.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDeleteFruit.setBounds(659, 30, 180, 30);
		frame.getContentPane().add(lblDeleteFruit);

		JLabel lblname1_1 = new JLabel("Key:");
		lblname1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1_1.setBounds(659, 60, 80, 30);
		frame.getContentPane().add(lblname1_1);

		id3 = new JTextField();
		id3.setColumns(10);
		id3.setBounds(739, 60, 100, 30);
		frame.getContentPane().add(id3);

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(SystemColor.inactiveCaption);
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					warning3.setText("");
					int id = Integer.parseInt(id3.getText());
					bnObj.deleteRootNode(id);
					id3.setText("");
					table();
				} catch (NumberFormatException e1) {
					warning3.setText("NumberFormatException");
				}
			}
		});
		deleteBtn.setBounds(659, 100, 180, 30);
		frame.getContentPane().add(deleteBtn);

		JButton searchBtn = new JButton("Search");
		searchBtn.setBackground(SystemColor.inactiveCaption);
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				defaultTableModel.setRowCount(0);
				warning4.setText("");
				try {
					int id = Integer.parseInt(id4.getText());
					bnObj.searchRootNode(id);
					defaultTableModel.setRowCount(0);
					try {
						Iterator<Node> itr = bnObj.getArrayList().iterator();
						while (itr.hasNext()) {
							Node nodeObj = itr.next();
							Object rowData[] = new Object[4];
							rowData[0] = nodeObj.getKey();
							rowData[1] = nodeObj.getName();
							rowData[2] = nodeObj.getPrice();
							rowData[3] = nodeObj.getQuantity();
							defaultTableModel.addRow(rowData);
						}
					} catch (NullPointerException e1) {
						System.out.println("Error: NullPointerException");
					}
					id4.setText("");
				} catch (NumberFormatException e1) {
					warning4.setText("NumberFormatException");
				}
			}
		});
		searchBtn.setBounds(659, 390, 180, 30);
		frame.getContentPane().add(searchBtn);

		id4 = new JTextField();
		id4.setColumns(10);
		id4.setBounds(739, 350, 100, 30);
		frame.getContentPane().add(id4);

		JLabel lblname1_1_1 = new JLabel("Key:");
		lblname1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1_1_1.setBounds(659, 350, 80, 30);
		frame.getContentPane().add(lblname1_1_1);

		JLabel lblSearchSold = new JLabel("Search Node");
		lblSearchSold.setForeground(Color.BLUE);
		lblSearchSold.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSearchSold.setBounds(659, 310, 180, 30);
		frame.getContentPane().add(lblSearchSold);

		JLabel lblUpdateFruit = new JLabel("Update Node");
		lblUpdateFruit.setForeground(Color.BLUE);
		lblUpdateFruit.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUpdateFruit.setBounds(20, 310, 180, 30);
		frame.getContentPane().add(lblUpdateFruit);

		JLabel lblname1_2 = new JLabel("Name:");
		lblname1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1_2.setBounds(20, 380, 80, 30);
		frame.getContentPane().add(lblname1_2);

		name2 = new JTextField();
		name2.setColumns(10);
		name2.setBounds(100, 380, 100, 30);
		frame.getContentPane().add(name2);

		price2 = new JTextField();
		price2.setColumns(10);
		price2.setBounds(100, 420, 100, 30);
		frame.getContentPane().add(price2);

		JLabel lnlprice1_1 = new JLabel("Price:");
		lnlprice1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lnlprice1_1.setBounds(20, 420, 80, 30);
		frame.getContentPane().add(lnlprice1_1);

		JLabel lblQuantity1_1 = new JLabel("Quantity:");
		lblQuantity1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantity1_1.setBounds(20, 460, 80, 30);
		frame.getContentPane().add(lblQuantity1_1);

		quantity2 = new JTextField();
		quantity2.setColumns(10);
		quantity2.setBounds(100, 460, 100, 30);
		frame.getContentPane().add(quantity2);

		JButton updateBtn = new JButton("Update");
		updateBtn.setBackground(SystemColor.inactiveCaption);
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					warning2.setText("");

					int id = Integer.parseInt(id2.getText());
					String name = name2.getText();
					Double price = Double.parseDouble(price2.getText());
					Integer quantity = Integer.parseInt(quantity2.getText());

					bnObj.updateRootNode(id, name, price, quantity);

					id2.setText("");
					name2.setText("");
					price2.setText("");
					quantity2.setText("");
					table();
				} catch (NumberFormatException e1) {
					warning2.setText("NumberFormatException");
				}
			}
		});
		updateBtn.setBounds(20, 500, 180, 30);
		frame.getContentPane().add(updateBtn);

		warning1 = new JLabel("");
		warning1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		warning1.setBounds(20, 250, 180, 30);
		frame.getContentPane().add(warning1);

		warning2 = new JLabel("");
		warning2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		warning2.setBounds(20, 530, 180, 30);
		frame.getContentPane().add(warning2);

		warning3 = new JLabel("");
		warning3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		warning3.setBounds(659, 130, 180, 30);
		frame.getContentPane().add(warning3);

		warning4 = new JLabel("");
		warning4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		warning4.setBounds(659, 420, 180, 30);
		frame.getContentPane().add(warning4);

		JButton btnNewButton_2_1 = new JButton("Refresh Inorder");
		btnNewButton_2_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table();
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2_1.setBounds(338, 370, 180, 30);
		frame.getContentPane().add(btnNewButton_2_1);

		id2 = new JTextField();
		id2.setColumns(10);
		id2.setBounds(100, 340, 100, 30);
		frame.getContentPane().add(id2);

		JLabel lblname1_2_1 = new JLabel("Key:");
		lblname1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblname1_2_1.setBounds(20, 340, 80, 30);
		frame.getContentPane().add(lblname1_2_1);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKey.setBounds(20, 60, 80, 30);
		frame.getContentPane().add(lblKey);

		key1 = new JTextField();
		key1.setColumns(10);
		key1.setBounds(100, 60, 100, 30);
		frame.getContentPane().add(key1);

		JButton btnLoadNodes = new JButton("Load Nodes");
		btnLoadNodes.setBackground(SystemColor.inactiveCaption);
		btnLoadNodes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadNodes();
				table();
			}
		});
		btnLoadNodes.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLoadNodes.setBounds(338, 500, 180, 30);
		frame.getContentPane().add(btnLoadNodes);

		table();
		frame.setVisible(true);
	}

	public void table() {
		defaultTableModel.setRowCount(0);
		try {
			bnObj.setInorder();
			Iterator<Node> itr = bnObj.getArrayList().iterator();
			while (itr.hasNext()) {
				Node nodeObj = itr.next();
				Object rowData[] = new Object[4];
				rowData[0] = nodeObj.getKey();
				rowData[1] = nodeObj.getName();
				rowData[2] = nodeObj.getPrice();
				rowData[3] = nodeObj.getQuantity();
				defaultTableModel.addRow(rowData);
			}
		} catch (NullPointerException e1) {
			System.out.println("Error: NullPointerException");
		}
	}

	public void loadNodes() {
		bnObj.insertRootNode(20, "orange", 0.2, 36);
		bnObj.insertRootNode(15, "apple", 0.6, 66);
		bnObj.insertRootNode(34, "peach", 1.2, 7);
		bnObj.insertRootNode(16, "lemon", 2.4, 12);
		bnObj.insertRootNode(10, "mellon", 0.7, 33);
		bnObj.insertRootNode(17, "pear", 0.8, 20);
	}

}
