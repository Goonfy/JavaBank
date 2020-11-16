package org.academiadecodigo.services;

import static org.mockito.Mockito.*;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.service.AuthenticationService;
import org.academiadecodigo.javabank.service.CustomerService;
import org.junit.*;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;
    private CustomerService customerService;
    private Customer accessingCustomer;

    @Before
    public void setup() {
        customerService = mock(CustomerService.class);
        accessingCustomer = mock(Customer.class);
        authenticationService = mock(AuthenticationService.class);
        authenticationService.setCustomerService(customerService);
    }

    @Test
    public void testAuthentication() {
        // setup
        int id = 1;

        // exercise
        when(customerService.get(id)).thenReturn(accessingCustomer);
        authenticationService.authenticate(id);

        // verify
        verify(customerService).get(id);
    }
}
