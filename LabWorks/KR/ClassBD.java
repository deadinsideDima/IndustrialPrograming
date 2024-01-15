import java.io.*;
import java.util.*;
class ClassBD {
    private List<Student> students;
    private Map<Integer, String> teachers;

    public ClassBD() {
        students = new ArrayList<>();
        teachers = new HashMap<>();
    }

    public void readStudentData(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("[;!_ ?]+");

                String[] dateParts = parts[0].split("[.]");
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);

                Date birthDate = new GregorianCalendar(year, month - 1, day).getTime();
                String studentid = parts[1];

                String lastName = parts[2];

                if (studentid.contains(".")) {
                    String[] idParts = studentid.split("\\.");
                    if (idParts.length > 1 && idParts[1].matches("e.*")) {
                        studentid = idParts[0];
                    }
                }
                double averageScore = Double.parseDouble(parts[3].replace(",", "."));


                List<Integer> subjects = new ArrayList<>();
                for (int i = 4; i < parts.length; i++) {
                    if (parts[i].matches("\\d+")) {
                        subjects.add(Integer.parseInt(parts[i]));
                    }
                }

                Student student = new Student(birthDate, studentid, lastName, averageScore, subjects);
                students.add(student);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readTeacherData(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                teachers.put(Integer.parseInt(parts[1]), parts[0]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTeachersToMapFile(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Map.Entry<Integer, String> entry : teachers.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findDebts(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Student student : students) {
                for (int subject : student.getSubjects()) {
                    if (!teachers.containsKey(subject)) {
                        writer.write(student.getLastName() + " owes for subject " + subject + "\n");
                    } else {
                        String teacherName = teachers.get(subject);
                        writer.write(student.getLastName() + " owes for subject " + subject + " to " + teacherName + "\n");
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sortStudentsAndWriteToFile(String filename) {
        try {
            Collections.sort(students, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return s1.getLastName().compareTo(s2.getLastName());
                }
            });

            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Student student : students) {
                writer.write(student.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTeachersToXML(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("<teachers>\n");
            for (Map.Entry<Integer, String> entry : teachers.entrySet()) {
                writer.write(String.format("<teacher><subject>%d</subject><name>%s</name></teacher>\n", entry.getKey(), entry.getValue()));
            }
            writer.write("</teachers>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
