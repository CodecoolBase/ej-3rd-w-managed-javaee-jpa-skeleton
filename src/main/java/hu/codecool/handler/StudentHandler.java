package hu.codecool.handler;

import hu.codecool.model.Student;

import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
@Path("/students")
public class StudentHandler {

    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAll() {
        // JPQL: SELECT s FROM hu.codecool.model.Student AS s
        // SQL: SELECT * FROM student
        String jpql = String.format("SELECT s FROM %s AS s", Student.class.getName());
        return em.createQuery(jpql, Student.class)
                .getResultList();
    }
}
