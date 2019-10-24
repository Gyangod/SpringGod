package com.gyangod.service;

import com.gyangod.entity.AddressEntity;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Address;
import com.gyangod.model.CustomerAddress;
import com.gyangod.repository.AddressRepository;
import com.gyangod.repository.CustomerRepository;
import com.gyangod.utils.AddressConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    private AddressConversion addressConversion;

    public AddressServiceImpl(AddressConversion addressConversion) {
        this.addressConversion = addressConversion;
    }

    @Override
    public Address saveAddress(Address address, String userName) throws Exception{
        try{
            CustomerEntity customerEntity = customerRepository.findByUserName(userName);
            if(customerEntity==null) throw new Exception("UserName Not Found");
            address.setCustomerId(customerEntity.getCustomerId());
            AddressEntity addressEntity = addressConversion.getAddressEntity(address);
            addressEntity = addressRepository.save(addressEntity);
            customerEntity.setAddresses(this.buildAddressListForCustomer(customerEntity.getAddresses(),addressEntity.get_id()));
            customerRepository.save(customerEntity);
            return addressConversion.getAddress(addressEntity);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public CustomerAddress getAllAddressForUser(String userName) throws Exception {
        try{
            CustomerEntity customerEntity = customerRepository.findByUserName(userName);
            CustomerAddress customerAddress = addressConversion.setInitialCustomer(customerEntity);
            if(customerEntity==null) throw new Exception("UserName Not Found");
            for (String address: customerEntity.getAddresses()) {
//                AddressEntity addressEntity = addressRepository.findBy_id(address);
//                System.out.println(addressEntity);
//                customerAddress.setAddresses(this.buildAddressListForCustomer(customerAddress.getAddresses(),addressConversion.getAddress(addressEntity)));
                Optional.ofNullable(addressRepository.findById(address)).ifPresent(addressEntity1 ->
                    customerAddress.setAddresses(
                            this.buildAddressListForCustomer(customerAddress.getAddresses(),
                                    addressConversion.getAddress(addressEntity1.get()))));
            }
            return customerAddress;
        }catch (Exception e){
            e.printStackTrace();
         throw e;
        }
    }

    /**
     * Generic Function to save it in a array List of any kind.
     * @param addresses the existing List of addresses or blank List
     * @param newAddress the newly generated address
     * @return the generated new List of address
     */
    private <T> List<T> buildAddressListForCustomer(List<T> addresses, T newAddress){
        if(addresses!=null && addresses.size()!=0) addresses.add(newAddress);
        else {
            addresses = new ArrayList<>();
            addresses.add(newAddress);
        }
        return addresses;
    }
}
