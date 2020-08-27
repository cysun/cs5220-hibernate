package cs5220.hibernate.employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeTest {

    public static void main( String args[] )
    {
        EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory( "hibernate-examples" );
        EntityManager entityManager = entityManagerFactory
            .createEntityManager();

        // Find the employee whose id is 3.
        Employee e3 = entityManager.find( Employee.class, 3 );
        System.out.println( e3.getName() );

        // Print employee #3's supervisor's supervisor's name.
        System.out.println( e3.getSupervisor().getSupervisor().getName() );

        // Add a new employee who's going to be supervised by employee #3.
        Employee employee = new Employee();
        employee.setName( "Jim" );
        employee.setSupervisor( e3 );
        entityManager.getTransaction().begin();
        employee = entityManager.merge( employee );
        entityManager.getTransaction().commit();

        // Find all the employees who are supervised by employee #3.
        List<Employee> subordinates = entityManager
            .createQuery( "from Employee where supervisor = :supervisor",
                Employee.class )
            .setParameter( "supervisor", e3 )
            .getResultList();
        for( Employee subordinate : subordinates )
            System.out.println( subordinate.getName() );

        entityManager.close();
        entityManagerFactory.close();
    }

}
