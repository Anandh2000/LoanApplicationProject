package com.dualtricks.assignment.entity;

import com.dualtricks.assignment.repository.UserDataRepository;
import com.dualtricks.assignment.repository.ClientDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserDataRepository userDataRepository;
    private final ClientDetailsRepository clientDetailsRepository;

    public DataInitializer(UserDataRepository userDataRepository, ClientDetailsRepository clientDetailsRepository) {
        this.userDataRepository = userDataRepository;
        this.clientDetailsRepository = clientDetailsRepository;
    }

    @Override
    public void run(String... args) {
        // dummy data
        userDataRepository.save(new UserData("123", "123", "23456", "asdds@gmail.com"));
        userDataRepository.save(new UserData("1234", "1234", "1234", "asdds@gmail.com"));
        userDataRepository.save(new UserData("root", "root", "23456", "asdds@gmail.com"));
        userDataRepository.save(new UserData("user", "user", "1234", "asdds@gmail.com"));
        userDataRepository.save(new UserData("nouser", "nouser", "23456", "asdds@gmail.com"));
        userDataRepository.save(new UserData("pass", "pass", "1234", "asdds@gmail.com"));

        //dummy data
        clientDetailsRepository.save(new ClientDetails("123","it industry", "business solution", "partial growth","immediate" ,"testing1"));
        clientDetailsRepository.save(new ClientDetails("1234","bpo insudtry", "business setup", "full growth","immediate" ,"testing2"));
        clientDetailsRepository.save(new ClientDetails("123","it industry", "business devlopment", "partial growth","within a week" ,"description"));
        clientDetailsRepository.save(new ClientDetails("1234","bpo insudtry", "business solution", "full growth","immediate" ,"description"));
        clientDetailsRepository.save(new ClientDetails("123","medical industry", "business devlopment", "partial growth","within a week" ,"description"));
        clientDetailsRepository.save(new ClientDetails("1234","automation insudtry", "business solution", "full growth","immediate" ,"description"));
    }
}

