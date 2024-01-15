public class Main {
    public static void main(String[] args) {
        ClassBD classBD = new ClassBD();
        classBD.readStudentData("Student2.txt");
        classBD.readTeacherData("Teacher.txt");

        classBD.writeTeachersToMapFile("rezultmap.txt");
        classBD.findDebts("rezuldebt.txt");
        classBD.sortStudentsAndWriteToFile("rezultsort.txt");
        classBD.writeTeachersToXML("rezXML.txt");
    }
}