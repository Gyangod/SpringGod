package com.gyangod.validation;

import com.gyangod.exception.domain.*;
import com.gyangod.model.Customer;
import com.gyangod.service.CustomerService;
import org.apache.commons.lang3.StringUtils;

public class CustomerValidation {

    public static void RegisterValidator(Customer customer, CustomerService service) throws Exception{
        validateNewUserName(customer.getUserName(),service);
        validateNewEmail(customer.getEmailAddress(),service);
        validateFirstName(customer.getFirstName());
    }

    private static void validateFirstName(String firstName) throws RegexMatchException, BlankFieldException {
        if(StringUtils.isNotBlank(firstName)) {
            if (!firstName.matches("^[A-Za-z]{3,30}")) {
                throw new RegexMatchException("First name must contains Alphabets within 3 to 30 characters");
            }
        }else{
            throw new BlankFieldException("First name");
        }
    }

    private static void validateNewUserName(String userName,CustomerService service) throws RegexMatchException, UsernameExistException, BlankFieldException {
        if(StringUtils.isNotBlank(userName)){
            if(!userName.matches("^[a-z0-9_]{5,50}")){
                throw new RegexMatchException("Username must be small letters,numbers & underscore with 5 to 50 characters");
            }
            Customer customer;
            try {
                customer = service.findByUserName(userName);
            } catch (UserNotFoundException e) {
                return;
            }
            if(customer != null) {
                throw new UsernameExistException();
            }
        } else {
            throw new BlankFieldException("Username");
        }

    }

    private static void validateNewEmail(String emailAddress,CustomerService service) throws RegexMatchException, EmailExistException, BlankFieldException {
        if(StringUtils.isNotBlank(emailAddress)){
            if(!emailAddress.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
                throw new RegexMatchException("Email format is not proper");
            }
            Customer customer;
            try {
                customer = service.findByEmailAddress(emailAddress);
            } catch (EmailNotFoundException e) {
                return;
            }
            if(customer != null) {
                throw new EmailExistException();
            }
        } else {
            throw new BlankFieldException("Email Id");
        }
    }
}
