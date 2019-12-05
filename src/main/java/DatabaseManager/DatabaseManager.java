package DatabaseManager;

import Entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DatabaseManager {
    EntityManager em;

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
        return result;
    }

    public Template findTemplate(Integer id){
        em.getTransaction().begin();
        Template result = em.find(Template.class,id);
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

    public void addStudent(Student stud){

        for(CategoryPercent cp : stud.getCourse().getCategoryPercents()){
            for(Assignment a: cp.getAssignments()){
                Grades temp = new Grades(stud, a);
                stud.getGrades().add(temp);
            }
        }
        em.getTransaction().begin();
        em.persist(stud);
        em.flush();
        em.getTransaction().commit();
    }

    public void addAssignment(Assignment assignment){
        for (Student s: assignment.getCategoryPercent().getCourse().getStudents()) {
            Grades temp = new Grades(s, assignment);
            assignment.getGrades().add(temp);
        }
        em.getTransaction().begin();
        em.persist(assignment);
        em.flush();
        em.getTransaction().commit();
    }

}
