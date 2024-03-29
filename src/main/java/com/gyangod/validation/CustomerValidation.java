package com.gyangod.validation;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.exception.domain.*;
import com.gyangod.model.Customer;
import com.gyangod.service.CustomerService;
import org.apache.commons.lang3.StringUtils;

public class CustomerValidation {

    public static void ValidateCustomer(Customer customer) throws Exception {
        validateEntity(customer);
        validateUserName(customer.getUserName());
    }

    public static void RegisterValidator(Customer customer, CustomerService service) throws Exception{
        validateNewUserName(customer.getUserName(),service);
        validateNewEmail(customer.getEmailAddress(),service);
        validateFirstName(customer.getFirstName());
        validatePassword(customer.getPassword());
    }

    public static void UpdateValidator(CustomerEntity previousEntity,Customer customer, CustomerService service) throws Exception {
        if(!customer.getUserName().equalsIgnoreCase(previousEntity.getUserName())){
            validateNewUserName(customer.getUserName(),service);
        }
        if(!customer.getEmailAddress().equalsIgnoreCase(previousEntity.getEmailAddress())){
            validateNewUserName(customer.getEmailAddress(),service);
        }
        validateFirstName(customer.getFirstName());
    }

    private static void validateUserName(String userName) throws BlankFieldException, RegexMatchException {
        if(StringUtils.isBlank(userName)){
            throw new BlankFieldException("Username");
        }
        if(!userName.matches("^[a-z0-9_]{5,50}$")){
            throw new RegexMatchException("Username must be small letters,numbers & underscore with 5 to 50 characters");
        }
    }

    private static void validateEntity(Customer customer) throws ObjectNotFoundException {
        if(customer==null){
            throw new ObjectNotFoundException("Customer");
        }
    }

    public static void validatePassword(String password) throws BlankFieldException, RegexMatchException {
            if(StringUtils.isNotBlank(password)){
                if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
                    throw new RegexMatchException("Password must contains at least one digit, one upper case alphabet, one lower" +
                            " case alphabet, one special character no white space and minimum 8 characters");
                }
            } else {
                throw new BlankFieldException("Password");
            }
    }

    private static void validateFirstName(String firstName) throws RegexMatchException, BlankFieldException {
        if(StringUtils.isNotBlank(firstName)) {
            if (!firstName.matches("^[A-Za-z]{3,30}$")) {
                throw new RegexMatchException("First name must contains Alphabets within 3 to 30 characters");
            }
        }else{
            throw new BlankFieldException("First name");
        }
    }

    private static void validateNewUserName(String userName,CustomerService service) throws RegexMatchException, UsernameExistException, BlankFieldException {
        if(StringUtils.isNotBlank(userName)){
            if(!userName.matches("^[a-z0-9_]{5,50}$")){
                throw new RegexMatchException("Username must be small letters,numbers & underscore with 5 to 50 characters");
            }
            try {
                service.findByUserName(userName);
            } catch (UserNotFoundException e) {
                return;
            }
            throw new UsernameExistException();
        } else {
            throw new BlankFieldException("Username");
        }

    }

    private static void validateNewEmail(String emailAddress,CustomerService service) throws RegexMatchException, EmailExistException, BlankFieldException {
        if(StringUtils.isNotBlank(emailAddress)){
            if(!emailAddress.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
                throw new RegexMatchException("Email format is not proper");
            }
            try {
                service.findByEmailAddress(emailAddress);
            } catch (EmailNotFoundException e) {
                return;
            }
            throw new EmailExistException();
        } else {
            throw new BlankFieldException("Email Id");
        }
    }
}
