import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<String> schedulerList = new ArrayList<>(); 
    private Connection conn; 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Schedule userInfoGUI = new Schedule();
            userInfoGUI.dbConnectionInit();
            userInfoGUI.setUpGUI();
        });
    }

    

    public void dbConnectionInit() {
        String url = "jdbc:mysql://localhost:3306/timeschedulers";
        String user = "root";
        String password = "mite";

        try {
            
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUpGUI() {
        JFrame frame = new JFrame("schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        
        JPanel mainPanel = new JPanel(new GridLayout(5, 1));

        JPanel topPanel = new JPanel(new GridLayout(3, 4));
        topPanel.add(new JLabel("Subject: "));
        JComboBox<String> subjectComboBox = new JComboBox<>();
        
        populateComboBox("Subject", subjectComboBox);
        topPanel.add(subjectComboBox);

        topPanel.add(new JLabel("Time: "));
        JComboBox<String> timeComboBox = new JComboBox<>();
        
        populateComboBox("Time", timeComboBox);
        topPanel.add(timeComboBox);

        topPanel.add(new JLabel("Whattodo: "));
        JComboBox<String> whattodoComboBox = new JComboBox<>();
        
        populateComboBox("Whattodo", whattodoComboBox);
        topPanel.add(whattodoComboBox);

        mainPanel.add(topPanel);

        
        JPanel comboBoxPanel = new JPanel(new GridLayout(3, 2));
        comboBoxPanel.add(new JLabel("Monthly: "));
        JComboBox<String> monthComboBox = new JComboBox<>();
       
        populateComboBox("Monthly", monthComboBox);
        comboBoxPanel.add(monthComboBox);

        comboBoxPanel.add(new JLabel("Weekly: "));
        JComboBox<String> weeklyComboBox = new JComboBox<>();
       
        populateComboBox("Weekly", weeklyComboBox);
        comboBoxPanel.add(weeklyComboBox);

        comboBoxPanel.add(new JLabel("Daily: "));
        JComboBox<String> dailyComboBox = new JComboBox<>();
        
        populateComboBox("Daily", dailyComboBox);
        comboBoxPanel.add(dailyComboBox);

        mainPanel.add(comboBoxPanel);

        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton saveButton = new JButton("저장");
        
        
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS)); 
        bottomPanel.add(saveButton, BorderLayout.NORTH);
      
        
        JScrollPane scrollPane = new JScrollPane(displayPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(bottomPanel);
        


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String subject = (String) subjectComboBox.getSelectedItem();
                String time = (String) timeComboBox.getSelectedItem();
                String whattodo = (String) whattodoComboBox.getSelectedItem();
                String monthly = (String) monthComboBox.getSelectedItem();
                String weekly = (String) weeklyComboBox.getSelectedItem();
                String daily = (String) dailyComboBox.getSelectedItem();

                String userInfo = "일정 : Subject: " + subject + ", Time: " + time + ", Whattodo: " + whattodo +
                        ", Monthly: " + monthly + ", Weekly: " + weekly + ", Daily: " + daily;

                schedulerList.add(userInfo);

                displayPanel.removeAll(); 
                for (String info : schedulerList) {
                    displayPanel.add(new JLabel(info));
                }

                frame.revalidate();
                frame.repaint();
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // MySQL 데이터베이스에서 정보를 가져와 콤보 박스에 추가하는 메서드
    private void populateComboBox(String columnName, JComboBox<String> comboBox) {
        try {
            String query = "SELECT " + columnName + " FROM timeschedulers";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String value = resultSet.getString(columnName);
                comboBox.addItem(value);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
