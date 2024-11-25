import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class RandomSelector extends JFrame {
    private SchoolClass schoolClass;
    private JTextArea displayArea;
    private JButton randomGroupBtn;
    private JButton randomStudentInGroupBtn;
    private JButton randomStudentInClassBtn;
    private JButton clearBtn;  // 新增清除按钮

    public RandomSelector() {
        setTitle("随机抽取系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeData();
        createComponents();
        setupLayout();
    }

    private void initializeData() {
        schoolClass = new SchoolClass("23级8班");

        Group group1 = new Group("第一小组");
        Group group2 = new Group("第二小组");

        Student student1 = new Student("刘志博", 1);
        Student student2 = new Student("梁均涵", 2);
        Student student3 = new Student("徐佳浩", 3);
        Student student4 = new Student("刘同泽", 4);

        group1.addStudent(student1);
        group1.addStudent(student2);
        group2.addStudent(student3);
        group2.addStudent(student4);

        schoolClass.addGroup(group1);
        schoolClass.addGroup(group2);

        schoolClass.addStudent(student1);
        schoolClass.addStudent(student2);
        schoolClass.addStudent(student3);
        schoolClass.addStudent(student4);
    }

    private void createComponents() {
        Font font = new Font("微软雅黑", Font.PLAIN, 16);

        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);
        displayArea.setFont(font);
        displayArea.setMargin(new Insets(10, 10, 10, 10));

        randomGroupBtn = new JButton("随机抽取小组");
        randomStudentInGroupBtn = new JButton("随机抽取小组中的学生");
        randomStudentInClassBtn = new JButton("随机抽取班级学生");
        clearBtn = new JButton("清除显示");  // 新增清除按钮

        // 设置按钮字体
        randomGroupBtn.setFont(font);
        randomStudentInGroupBtn.setFont(font);
        randomStudentInClassBtn.setFont(font);
        clearBtn.setFont(font);

        // 添加按钮事件
        randomGroupBtn.addActionListener(e -> randomSelectGroup());
        randomStudentInGroupBtn.addActionListener(e -> randomSelectStudentFromGroup());
        randomStudentInClassBtn.addActionListener(e -> randomSelectStudentFromClass());
        clearBtn.addActionListener(e -> displayArea.setText(""));  // 清除文本区域内容
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(randomGroupBtn);
        buttonPanel.add(randomStudentInGroupBtn);
        buttonPanel.add(randomStudentInClassBtn);
        buttonPanel.add(clearBtn);  // 添加清除按钮

        // 创建标题面板
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(schoolClass.getClassName() + " 随机抽取系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        // 添加组件到窗口
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void randomSelectGroup() {
        if (schoolClass.getGroups().isEmpty()) {
            appendToDisplay("警告：没有可用的小组！\n");
            return;
        }
        Random random = new Random();
        Group randomGroup = schoolClass.getGroups().get(random.nextInt(schoolClass.getGroups().size()));
        appendToDisplay("随机抽取的小组：\n" + randomGroup + "\n");
    }

    private void randomSelectStudentFromGroup() {
        if (schoolClass.getGroups().isEmpty()) {
            appendToDisplay("警告：没有可用的小组！\n");
            return;
        }
        Random random = new Random();
        Group randomGroup = schoolClass.getGroups().get(random.nextInt(schoolClass.getGroups().size()));
        if (randomGroup.getStudents().isEmpty()) {
            appendToDisplay("警告：选中的小组没有学生！\n");
            return;
        }
        Student randomStudent = randomGroup.getStudents().get(random.nextInt(randomGroup.getStudents().size()));
        appendToDisplay("从" + randomGroup + "随机抽取的学生：\n" + randomStudent + "\n");
    }

    private void randomSelectStudentFromClass() {
        if (schoolClass.getStudents().isEmpty()) {
            appendToDisplay("警告：班级中没有学生！\n");
            return;
        }
        Random random = new Random();
        Student randomStudent = schoolClass.getStudents().get(random.nextInt(schoolClass.getStudents().size()));
        appendToDisplay("从班级随机抽取的学生：\n" + randomStudent + "\n");
    }

    private void appendToDisplay(String text) {
        displayArea.append(text + "\n");
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RandomSelector().setVisible(true);
        });
    }
}
