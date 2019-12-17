package DatabaseManager;

import Entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public EntityManager em;

    public DatabaseManager(){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "GradingSystemProvider" );
        this.em = emfactory.createEntityManager();
    }

    public <E> void add(E entity){
        em.getTransaction().begin();
        em.persist(entity);
        em.flush();
        em.getTransaction().commit();
    }

    public <E> void remove(E entity){
        em.getTransaction().begin();
        em.merge(entity);
        em.flush();
        em.remove(entity);
        em.flush();
        em.getTransaction().commit();
    }

    public <E> void update(E entity){
        em.getTransaction().begin();
        em.merge(entity);
        em.flush();
        em.getTransaction().commit();
    }

    public Assignment findAssignment(Integer id){
        em.getTransaction().begin();
        Assignment result = em.find(Assignment.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Category findCategory(Integer id){
        em.getTransaction().begin();
        Category result = em.find(Category.class,id);
        em.getTransaction().commit();
        return result;
    }

    public CategoryPercent findCategoryPercent(Integer id){
        em.getTransaction().begin();
        CategoryPercent result = em.find(CategoryPercent.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Course findCourse(Integer id){
        em.getTransaction().begin();
        Course result = em.find(Course.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Grades findGrades(Integer id){
        em.getTransaction().begin();
        Grades result = em.find(Grades.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Student findStudent(Integer id){
        em.getTransaction().begin();
        Student result = em.find(Student.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Professor findProfessor(Integer id){
        em.getTransaction().begin();
        Professor result = em.find(Professor.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Semester findSemester(Integer id){
        em.getTransaction().begin();
        Semester result = em.find(Semester.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Template findTemplate(Integer id){
        em.getTransaction().begin();
        Template result = em.find(Template.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Category findCategoryByName(String name){
        Category result = null;
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Category s WHERE s.name = :n");
        q.setParameter("n",name);
        result = (Category) q.getSingleResult();
        em.getTransaction().commit();

        return result;
    }
    public CategoryPercent findCategoryPercentByName(String name, Course course){
        CategoryPercent result = null;
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM CategoryPercent s WHERE s.category.name = :n AND s.course = :cour");
        q.setParameter("n",name).setParameter("cour",course);
        result = (CategoryPercent) q.getSingleResult();
        em.getTransaction().commit();

        return result;
    }

    public List<Assignment> getAllAssignments(){
        em.getTransaction().begin();

        Query q = em.createQuery("SELECT s FROM Assignment s");
        List<Assignment> result = (List<Assignment>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Category> getAllCategories(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Category s");
        List<Category> result = (List<Category>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<CategoryPercent> getAllCategoryPercents(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM CategoryPercent s");
        List<CategoryPercent> result = (List<CategoryPercent>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Course> getAllCourses(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Course s");
        List<Course> result = (List<Course>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Grades> getAllGrades(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Grades s");
        List<Grades> result = (List<Grades>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Student> getAllStudents(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Student s");
        List<Student> result = (List<Student>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Semester> getAllSemester(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Semester s");
        List<Semester> result = (List<Semester>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Template> getTemplate(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Template s");
        List<Template> result = (List<Template>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Course> getCoursesBySemester(Semester semester){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Course s WHERE s.semester = :sem");
        q.setParameter("sem",semester);
        List<Course> result = (List<Course>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Assignment> getAssignmentsByCourse(Course course){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Assignment s WHERE s.categoryPercent.course = :cour");
        q.setParameter("cour",course);
        List<Assignment> result = (List<Assignment>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public void addStudent(Student stud){

        for(CategoryPercent cp : stud.getCourse().getCategoryPercents()){
            System.out.println(cp);
            for(Assignment a: cp.getAssignments()){
                Grades temp = new Grades(stud, a);
                stud.getGrades().add(temp);
            }
        }
        Course cour = stud.getCourse();
        cour.getStudents().add(stud);
        em.getTransaction().begin();
        em.persist(stud);
        em.flush();
        em.getTransaction().commit();
        update(cour);
    }

    public void addAssignment(int id , Course course){
        Assignment assignment = findAssignment(id);
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
        for (Student s: course.getStudents()) {
            Grades temp = new Grades(s, assignment);
            assignment.getGrades().add(temp);
            s.getGrades().add(temp);
            update(s);
            update(assignment);

        }
        update(course);
    }

    public void createCourseFromCsv(Course course, String csvPath){
        List<Student> studentList = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";
        Student stud=null;
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] student = line.split(cvsSplitBy);
                switch (student[4]){
                    case "0":
                        stud = new UndergraduateStudent(student[0],student[1],student[2],student[3],course);
                        break;
                    case "1":
                        stud = new GraduateStudent(student[0],student[1],student[2],student[3],course);
                        break;
                    case "2":
                        stud = new PhdStudent(student[0],student[1],student[2],student[3],course);
                        break;
                }
                studentList.add(stud);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        course.setStudents(studentList);
        add(course);
    }

    public void createTemplate(Course course, String name){
        String categories = "{";
        String catPercent = "{";
        String assignNum = "{";
        String assignPercent = "{";
        String assignTotalScore="{";
        for(int i =0; i<course.getCategoryPercents().size();i++){
            categories+=course.getCategoryPercents().get(i).getCategory().getName();
            catPercent+=course.getCategoryPercents().get(i).getPercent();
            assignNum+=course.getCategoryPercents().get(i).getAssignments().size();
            assignPercent+="{";
            assignTotalScore+="{";
            for(int j =0; j<course.getCategoryPercents().get(i).getAssignments().size();j++){
                assignPercent+=course.getCategoryPercents().get(i).getAssignments().get(j).getPercent();
                assignTotalScore+=course.getCategoryPercents().get(i).getAssignments().get(j).getTotalScore();
                if(course.getCategoryPercents().get(i).getAssignments().size()== j+1){
                    assignPercent+="}";
                    assignTotalScore+="}";
                }else{
                    assignPercent+=",";
                    assignTotalScore+=",";
                }
            }
            if(course.getCategoryPercents().size()== i+1){
                categories+="}";
                catPercent+="}";
                assignNum+="}";
                assignPercent+="}";
                assignTotalScore+="}";
            }else{
                categories+=",";
                catPercent+=",";
                assignNum+=",";
                assignPercent+=",";
                assignTotalScore+=",";
            }
        }
        Template temp= new Template(name,categories,catPercent,assignNum,assignPercent,assignTotalScore);
        add(temp);
    }

    public void createCourseByTemplate(Template template, String name, Semester semester){
        Course course = new Course(name,semester);
        add(course);
        List<CategoryPercent> categoryPercentsList = new ArrayList<>();
        String category = template.getCategories();
        String catPercent = template.getCatPercent();
        String assignNum = template.getAssignNum();
        String assignPercent = template.getAssignPercent();
        String assignTotalScore = template.getAssignTotalScore();

        String [] categories = category.substring(1,category.length()-1).split(",");
        String [] catPercents = catPercent.substring(1,catPercent.length()-1).split(",");
        String [] assignNums = assignNum.substring(1,assignNum.length()-1).split(",");
        String [] assignPercents= assignPercent.substring(2,assignPercent.length()-2).split("},\\{");
        String [] assignTotalScores= assignTotalScore.substring(2,assignTotalScore.length()-2).split("},\\{");

        for(int i =0;i<categories.length;i++) {
            Category tempCategory = findCategoryByName(categories[i]);
            CategoryPercent tempCategoryPercent = new CategoryPercent(Double.valueOf(catPercents[i]), tempCategory, course);

            String[] tempAssignPercents = assignPercents[i].split(",");
            String [] tempAssignTotalScores = assignTotalScores[i].split(",");
            ArrayList<Assignment> assignmentsList = new ArrayList<>();
            for (int j = 0; j < tempAssignPercents.length; j++) {
                Assignment tempAssignment = new Assignment(Double.valueOf(tempAssignPercents[j]), (categories[i] + j), tempCategoryPercent, Double.valueOf(tempAssignTotalScores[j]));
                assignmentsList.add(tempAssignment);
            }
            tempCategoryPercent.setAssignments(assignmentsList);
            categoryPercentsList.add(tempCategoryPercent);
        }
        course.setCategoryPercents(categoryPercentsList);
        update(course);
    }

    public void createCourseByCsvAndTemplate(Course course, Template template, String csvPath){
        add(course);
        Course cour = findCourse(course.getId());
        String line = "";
        String cvsSplitBy = ",";
        Student stud=null;
        List<Student> studList = new ArrayList<>();
        List<Grades> gradesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] student = line.split(cvsSplitBy);
                switch (student[4]){
                    case "0":
                        stud = new UndergraduateStudent(student[0],student[1],student[2],student[3],course);
                        break;
                    case "1":
                        stud = new GraduateStudent(student[0],student[1],student[2],student[3],course);
                        break;
                    case "2":
                        stud = new PhdStudent(student[0],student[1],student[2],student[3],course);
                        break;
                }
                studList.add(stud);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String category = template.getCategories();
        String catPercent = template.getCatPercent();
        String assignNum = template.getAssignNum();
        String assignPercent = template.getAssignPercent();
        String assignTotalScore = template.getAssignTotalScore();

        String [] categories = category.substring(1,category.length()-1).split(",");
        String [] catPercents = catPercent.substring(1,catPercent.length()-1).split(",");
        String [] assignNums = assignNum.substring(1,assignNum.length()-1).split(",");
        String [] assignPercents= assignPercent.substring(2,assignPercent.length()-2).split("},\\{");
        String [] assignTotalScores= assignTotalScore.substring(2,assignTotalScore.length()-2).split("},\\{");

        for(int i =0;i<categories.length;i++) {
            Category tempCategory = findCategoryByName(categories[i]);
            CategoryPercent tempCategoryPercent = new CategoryPercent(Double.valueOf(catPercents[i]), tempCategory, course);
            String[] tempAssignPercents = assignPercents[i].split(",");
            String [] tempAssignTotalScores = assignTotalScores[i].split(",");
            for (int j = 0; j < tempAssignPercents.length; j++) {
                Assignment tempAssignment = new Assignment(Double.valueOf(tempAssignPercents[j]), (categories[i] + j), tempCategoryPercent, Double.valueOf(tempAssignTotalScores[j]));
                for(Student s:studList){
//                    gradesList.add(new Grades(s,tempAssignment));
                    s.getGrades().add(new Grades(s,tempAssignment));

                }
            }
        }
        course.setStudents(studList);
        update(course);
    }

    public void gradeAssignment(Grades grades, double result){
        if(result<=0){
            grades.setGrade((grades.getAssignment().getTotalScore()+result)/grades.getAssignment().getTotalScore());
        }else {
            grades.setGrade(result);
        }
        update(grades);
    }

    public void exportToCsv(Course course, String csvPath) throws IOException {
        FileWriter csvWriter = new FileWriter(csvPath);
        csvWriter.append("Name,");
        for(int i = 0;i<course.getStudents().get(0).getGrades().size();i++){
            if(course.getStudents().get(0).getGrades().size()==i+1){
                csvWriter.append(course.getStudents().get(0).getGrades().get(i).getAssignment().getName());
            }else{
                csvWriter.append(course.getStudents().get(0).getGrades().get(i).getAssignment().getName()+",");
            }
        }
        csvWriter.append("\n");

        for(int i = 0; i< course.getStudents().size();i++){
            csvWriter.append(course.getStudents().get(i).toString()+",");
            for(int j = 0;j<course.getStudents().get(i).getGrades().size();j++){
                if(course.getStudents().get(i).getGrades().size()==j+1){
                    csvWriter.append(course.getStudents().get(i).getGrades().get(j).toString());
                }else{
                    csvWriter.append(course.getStudents().get(i).getGrades().get(j).toString()+",");
                }
            }
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public void removeStudentById(int id){
        Student s = findStudent(id);
        Course a = s.getCourse();
        remove(s);
        a.getStudents().remove(s);
        update(a);
    }

    public void removeAssignment(Assignment assignment){
        Course course = assignment.getCategoryPercent().getCourse();

        for(Student s: course.getStudents()){
            ArrayList<Grades> deletedGrades = new ArrayList<>();
            for(Grades g:s.getGrades()){
                if(g.getAssignment().equals(assignment)){
                    deletedGrades.add(g);
                }
            }
            s.getGrades().removeAll(deletedGrades);
        }
        remove(assignment);
        assignment.getCategoryPercent().getAssignments().remove(assignment);
        if(assignment.getCategoryPercent().getAssignments().isEmpty()){
            remove(assignment.getCategoryPercent());
        }
        update(course);
    }

//    public void addCategory(int courseId, String category){
//        Course course = findCourse(courseId);
//        List<Category> categories = getAllCategories();
//        if(!categories.contains(category)){
//            Category tepm = new Category(category);
//            CategoryPercent tempPercent = new CategoryPercent(0.0,tepm,course);
//            course.getCategoryPercents().add(tempPercent);
//            add(tempPercent);
//            update(course);
//        }else if(false){
//
//        }
//    }

    public void addAssignment(Assignment assignment, int courseId,Category category){
        Course course = findCourse(courseId);
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
        CategoryPercent temp = new CategoryPercent(0.0,category,course);
        if(course.getCategoryPercents().contains(temp)){
            System.out.println("1!!!!!!!!!");
            CategoryPercent categoryPercent = findCategoryPercentByName(category.getName(),course);
            assignment.setCategoryPercent(categoryPercent);
            categoryPercent.getAssignments().add(assignment);
            update(categoryPercent);
            add(assignment);
            addAssignment( assignment.getId(), course);

        }else{
            System.out.println("2!!!!!!!!!!!!!!");
            CategoryPercent categoryPercent = new CategoryPercent(0.0,category,course);
            assignment.setCategoryPercent(categoryPercent);
            add(assignment);
            addAssignment( assignment.getId(),course);
        }
    }


}
