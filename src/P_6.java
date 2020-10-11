import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class SortingStudentsByGPA implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getGrade() > s2.getGrade()) {
            return -1;
        } else if (s1.getGrade() < s2.getGrade()) {
            return 1;
        } else {
            return 0;
        }
    }
}

class Student implements Comparable<Student>{
    private Integer idNumber;
    private int grade;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        return "idNumber = " + idNumber + "; Grade = " + grade;
    }

    @Override
    public int compareTo(Student o) {
        return idNumber.compareTo(o.getIdNumber());
    }
}

class MergeClass {
    public static Student[] sortArray(Student[] arrayA) {
        if (arrayA == null) {
            return null;
        }
        if (arrayA.length < 2) {
            return arrayA;
        }
        int len = arrayA.length / 2;
        Student [] arrayB = new Student[len];
        System.arraycopy(arrayA, 0, arrayB, 0, len);
        Student [] arrayC = new Student[arrayA.length - len];
        System.arraycopy(arrayA, len, arrayC, 0, arrayA.length - len);
        arrayB = sortArray(arrayB);
        arrayC = sortArray(arrayC);
        return mergeArray(arrayB, arrayC);
    }

    public static Student[] mergeArray(Student[] a1, Student[] a2) {
        Student[] b = new Student[a1.length + a2.length];
        int position1 = 0;
        int position2 = 0;
        for (int i = 0; i < b.length; i++) {
            if (position1 == a1.length){
                b[i] = a2[position2];
                position2++;
            } else if (position2 == a2.length){
                b[i] = a1[position1];
                position1++;
            } else if (a1[position1].getGrade() < a2[position2].getGrade()){
                b[i] = a1[position1];
                position1++;
            } else {
                b[i] = a2[position2];
                position2++;
            }
        }
        return b;
    }
}

public class P_6 {
    public static void insertionSort(Student[] students) {
        for (int i = 1; i < students.length; i++) {
            Student tmp = students[i];
            int j = i - 1;
            while (j >= 0 && students[j].compareTo(tmp) > 0) {
                students[j + 1] = students[j];
                j--;
            }
            students[j + 1] = tmp;
        }
    }

    public static void main(String[] args) {
        Student[] students1 = new Student[10];
        Student[] students2 = new Student[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            students1[i] = new Student();
            students1[i].setIdNumber(random.nextInt(99999) + 1);
            students1[i].setGrade(random.nextInt(5) + 1);
            students2[i] = new Student();
            students2[i].setIdNumber(random.nextInt(99999) + 1);
            students2[i].setGrade(random.nextInt(5) + 1);
        }
        insertionSort(students1);
        for (Object obj : students1) {
            System.out.println(obj);
        }
        System.out.println();
        Arrays.sort(students2, new SortingStudentsByGPA());
        for (Object student : students2) {
            System.out.println(student);
        }
        students1 = MergeClass.sortArray(students1);
        students2 = MergeClass.sortArray(students2);
        Student[] student3 = MergeClass.mergeArray(students1, students2);
        System.out.println();
        for (Object obj : student3) {
            System.out.println(obj);
        }
    }
}

