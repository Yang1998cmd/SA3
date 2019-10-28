package Client;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class windows {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField fie1;
    private JTextArea fie2;
    public JPanel mainPanel;
    private JButton check;
    private JButton send;
    private JComboBox<String> faceCombo1;
    public static String way;
    public static String url;
    public static String payload;
    private Cli_rest cre;
    private Cli_soap cso;

    public windows() {
        cre=new Cli_rest();
        cso=new Cli_soap();
        mainPanel=new JPanel();
        JFrame jFrame = new JFrame("邮件推送");
        jFrame.setBounds(200,200,650,400);
//        jFrame.setSize(200,150);
//        jFrame.setLocation(500,300);
        jFrame.setContentPane(mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //jFrame.pack();
       // jFrame.setVisible(true);
        mainPanel.setLayout(null);

        label1=new JLabel("发送地址:");
        label1.setBounds(10,3,60,60);
        mainPanel.add(label1);

        fie1 =new JTextField(20);
        fie1.setBounds(80,25,300,25);
        mainPanel.add(fie1);


        label2=new JLabel("可以批量发送,邮箱之间请用“,”(英文逗号）隔开!");
        label2.setBounds(400,3,400,60);
        mainPanel.add(label2);


        label3=new JLabel("信封内容:");
        label3.setBounds(10,50,60,60);
        mainPanel.add(label3);

        fie2 =new JTextArea();
        fie2.setBounds(10,120,450,220);
        mainPanel.add(fie2);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 120, 450, 220);
        scrollPane.setViewportView(fie2);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane);


        String[] listData1 = new String[]{" ","soap", "rest"};

        ItemListener ite1 = arg0 -> {
            // TODO Auto-generated method stub
            if (ItemEvent.SELECTED == arg0.getStateChange()) {
                String selectedItem = arg0.getItem().toString();
                way=selectedItem;
                System.out.printf(way);
//                       tarea.append("查询内容："+select1+"\n");

            }
        };
        faceCombo1 = new JComboBox<String>(listData1);
        faceCombo1.setEditable(true);
        faceCombo1.addItemListener(ite1);
        faceCombo1.setEnabled(true);
        mainPanel.add(faceCombo1, BorderLayout.SOUTH);
        faceCombo1.setBounds(500, 100, 100, 30);

        check=new JButton("验证邮箱");
        check.setBounds(500,200,100,30);
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                url=fie1.getText();
                String ans="";
                System.out.println(url);
                if(way.equals("soap"))
                {
                    ans=cso.validateEmailAddressSOAP(url);

                }
                else if(way.equals("rest"))
                {
                    ans=cre.do_restserver_check(url);
                }
                if(ans.equals("false"))
                {
                    JFrame jFrame = new JFrame("提醒！！！");
                    jFrame.getContentPane().add(new TextField("不符合要求的邮箱！！！"));
                    jFrame.setLocation(300,300);
                    jFrame.pack();
                    jFrame.setVisible(true);

                }

            }
        });

        mainPanel.add(check);
        mainPanel.validate();

        send=new JButton("发送");
        send.setBounds(500,300,100,30);
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payload=fie2.getText();
                String ans="";
                if(way.equals("soap"))
                {
                    String[] aa=url.split(",");
                    if(aa.length>1)
                    {
                        ans=cso.sendEmailBatchSOAP(url,payload);
                    }
                    else
                    {
                        ans=cso.sendEmailSOAP(url,payload);
                    }

                }
                else if(way.equals("rest"))
                {
                    ans=cre.do_restserver_sendemails(url,payload);
                }

                if(ans.equals("true"))
                {
                    JFrame jFrame = new JFrame("提醒！！！");
                    jFrame.getContentPane().add(new TextField("发送成功！！！"));
                    jFrame.setLocation(300,300);
                    jFrame.pack();
                    jFrame.setVisible(true);

                }

            }
        });

        mainPanel.add(send);

        jFrame.setVisible(true);




    }

    public static void main(String[] args)  {
        windows w=new windows();

    }

}
