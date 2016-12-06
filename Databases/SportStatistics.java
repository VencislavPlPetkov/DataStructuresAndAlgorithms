package databases;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class SportStatistics {

	static Object[][] databaseInfo;

	static Object[] columns = { "Year", "PlayerID", "Name", "TTRC", "Team", "Salary", "CPR", "POS" };

	static ResultSet rows;

	static ResultSetMetaData metaData;

	// overrides getColumnClass() so that we don't get everything from the
	// database as strings. Ex: this will parse doubles as doubles so they
	// can be aligned and sorted.
	static DefaultTableModel dTableModel = new DefaultTableModel(databaseInfo, columns) {
		public Class getColumnClass(int column) {

			Class returnValue;

			if ((column >= 0) && (column < getColumnCount())) {
				returnValue = getValueAt(0, column).getClass();
			} else {
				// The standard default implementation
				returnValue = Object.class;
			}

			return returnValue;
		}
	};

	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Connection conn = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lahman591?autoReconnect=true&useSSL=false",
					"****", "****");

			Statement sqlState = conn.createStatement();

			String selectStuff = "select b.yearID, b.playerID, " + "CONCAT(m.nameFirst, ' ', m.nameLast) AS Name, "
					+ "((b.H+b.BB)+(2.4*(b.AB+b.BB)))*(t.TB+(3*(b.AB+b.BB)))/(9*(b.AB+b.BB))-(.9*(b.AB+b.BB)) AS TTRC, "
					+ "b.teamID AS Team, s.salary AS Salary, "
					+ "CAST( s.salary/(((b.H+b.BB)+(2.4*(b.AB+b.BB)))*(t.TB+(3*(b.AB+b.BB)))/(9*(b.AB+b.BB))-(.9*(b.AB+b.BB))) as decimal(10,2)) AS CPR, "
					+ "f.POS AS POS FROM Batting b, Master m, Salaries s, TOTBYR t, Fielding f "
					+ "WHERE b.playerID = m.playerID AND t.playerID = m.playerID "
					+ "AND t.yearID = 2010 AND b.yearID = t.yearID AND s.playerID = b.playerID "
					+ "AND s.yearID = b.yearID AND b.AB > 50 AND b.playerID = f.playerID "
					+ "AND b.playerID = t.playerID GROUP BY b.playerID ORDER BY TTRC DESC LIMIT 200;";

			rows = sqlState.executeQuery(selectStuff);

			Object[] tempRow;

			while (rows.next()) {
				tempRow = new Object[] { rows.getInt(1), rows.getString(2), rows.getString(3), rows.getDouble(4),
						rows.getString(5), rows.getInt(6), rows.getDouble(7), rows.getString(8) };
				dTableModel.addRow(tempRow);

			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());

		} catch (ClassNotFoundException ex) {

			System.out.println(ex.getMessage());

		}

		JTable table = new JTable(dTableModel);

		table.setRowHeight(table.getRowHeight() + 10);

		// table.setFont("Serif", Font.PLAIN, 20);

		table.setAutoCreateRowSorter(true);

		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//TableColumn col1 = table.getColumnModel().getColumn(0);
		//col1.setPreferredWidth(100);

		TableColumn tc = table.getColumn("Team");
		CenterTableCellRenderer centerRenderer = new CenterTableCellRenderer();

		tc = table.getColumn("POS");
		centerRenderer = new CenterTableCellRenderer();
		tc.setCellRenderer(centerRenderer);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(600, 500);
		frame.setVisible(true);

	}// end of main

}// end of class

class CenterTableCellRenderer extends DefaultTableCellRenderer {

	public CenterTableCellRenderer() {

		setHorizontalAlignment(JLabel.CENTER);

	}

}
