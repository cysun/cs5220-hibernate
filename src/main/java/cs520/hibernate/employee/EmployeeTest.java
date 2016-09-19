package cs520.hibernate.employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeTest {

    public static void main( String args[] )
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "hibernate-examples" );
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Find the employee whose id is 2.
        Employee e2 = entityManager.find( Employee.class, 2 );
        System.out.println( e2.getName() );

        // Print employee #2's supervisor's name.
        System.out.println( e2.getSupervisor().getName() );

        // Add a new employee who's going to be supervised by employee #2.
        Employee employee = new Employee();
        employee.setName( "Jim" );
        employee.setSupervisor( e2 );
        entityManager.getTransaction().begin();
        entityManager.persist( employee );
        entityManager.getTransaction().commit();

        // Find all the employees who are supervised by employee #2.
        List<Employee> subordinates = entityManager.createQuery(
            "from Employee where supervisor.id = :id", Employee.class )
            .setParameter( "id", 2 )
            .getResultList();
        for( Employee subordinate : subordinates )
            System.out.println( subordinate.getName() );

        entityManager.close();
        entityManagerFactory.close();
    }

}
