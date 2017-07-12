package hu.codecool;

import hu.codecool.model.Address;
import hu.codecool.model.Klass;
import hu.codecool.model.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebListener
@ApplicationScoped
public class JPAExampleWebListener implements ServletContextListener {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate1 = Calendar.getInstance().getTime();
        Date birthDate2 = Calendar.getInstance().getTime();
        try {
            birthDate1 = sdf.parse("1997-07-21");
            birthDate2 = sdf.parse("1993-12-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Klass classBp2 = new Klass("Budapest 2016-2");
        Address address = new Address("Hungary", "1234", "Budapest", "Macskakő út 5.");
        Student student = new Student("Ödön", "odon@tokodon.hu", birthDate1, address);
        classBp2.addStudent(student);

        em.persist(address);
        em.persist(student);
        System.out.println("Ödön saved.");

        Address address2 = new Address("Hungary", "6789", "Budapest", "Harap u. 3.");
        Student student2 = new Student("Aladár", "ktyfl@gmail.com", birthDate2, address);
        classBp2.addStudent(student2);

        em.persist(student2);
        em.persist(address2);
        System.out.println("Aladár saved.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
