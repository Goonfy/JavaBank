package org.academiadecodigo.services;

import static org.mockito.Mockito.*;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.service.JpaAuthenticationService;
import org.academiadecodigo.javabank.service.JpaCustomerService;

public class JpaAuthenticationServiceTest {

    private JpaAuthenticationService authenticationService;
    private JpaCustomerService customerService;
    private Customer accessingCustomer;

    //@Before
    public void setup() {
        customerService = mock(JpaCustomerService.class);
        accessingCustomer = mock(Customer.class);
        authenticationService = mock(JpaAuthenticationService.class);
    }

    //@Test
    public void testAuthentication() {
        // setup
        int id = 1;

        // exercise
        //when(customerService.get(id)).thenReturn(accessingCustomer);
        //authenticationService.authenticate(id);

        // verify
        //verify(customerService).get(id);
    }
}
