import java.util.ArrayList;
import java.util.List;

public class SchoolClass {
    private String className;
    private List<Group> groups;
    private List<Student> students;

    public SchoolClass(String className) {
        this.className = className;
        this.groups = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getClassName() {
        return className;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return className + "\n小组: " + groups + "\n学生: " + students;
    }
}
