package com.spring.production.serviceimpl;

import com.spring.production.controller.AppController;
import com.spring.production.model.EmployeeData;
import com.spring.production.model.primary.Employee;
import com.spring.production.model.primary.Env;
import com.spring.production.model.secondary.EmpRating;
import com.spring.production.model.secondary.Source;
import com.spring.production.repository.primary.EmployeeRepository;
import com.spring.production.repository.primary.EnvRepository;
import com.spring.production.repository.secondary.RatingRepository;
import com.spring.production.repository.secondary.SourceRepository;
import com.spring.production.service.SpringService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class SpringServiceImpl implements SpringService {

    private static final Logger logger = LogManager.getLogger(SpringServiceImpl.class);

    @Autowired
    private Environment properties;

   @Autowired
    private EnvRepository envRepo;

    @Autowired
    private SourceRepository sourceRepo;

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private RatingRepository ratingRepo;



    @Override
    public String testDB() {



        String result="";
        String[] activeProfile = properties.getActiveProfiles();
        logger.info("Current Active Profile "+ Arrays.stream(activeProfile).findFirst());

        logger.info("Testing config from common.properties....");
        logger.info(properties.getProperty("displayText"));

        logger.info("Initializing DB Check ....");
        try{
            Env envir = envRepo.findAll().get(0);
            result= "Primary  DB - > Application is running in "+envir.getEnv()+" Environment.";
        }catch (Exception e){
            logger.error("Primary DB Error :"+e.getMessage());
        }

        try{
            Source data = sourceRepo.findAll().get(0);
            result+= "Secondary  DB - > Application is running in "+data.getData()+" Environment.";
        }catch (Exception e){
            logger.error("Secondary DB Error :"+e.getMessage());
        }

        logger.info(result);
        return result;

    }

    @Override
    public String addEmployee(EmployeeData emp) {

        boolean savEmp =false;
        boolean savRating =false;

        Employee employee = new Employee();
        employee.setId(emp.getId());
        employee.setName(emp.getName());
        employee.setCompany(emp.getCompany());
        employee.setAddress(emp.getAddress());
        employee.setPhone(emp.getPhone());


        //These database operation should take place in dao classes. Now i just made a rough code
        try{
            empRepo.save(employee);
            savEmp=true;
        } catch (Exception e) {

        }


        EmpRating rating = new EmpRating();
        rating.setId(emp.getId());
        rating.setName(emp.getName());
        rating.setCommunication(emp.getRating().getCommunication());
        rating.setDiscipline(emp.getRating().getDiscipline());
        rating.setPunctuality(emp.getRating().getPunctuality());
        rating.setUpskilling(emp.getRating().getUpskilling());
        rating.setWorkEfficiency(emp.getRating().getWorkEfficiency());

        try{
            ratingRepo.save(rating);
            savRating=true;
        } catch (Exception e) {

        }

        if(savEmp && savRating){
            logger.info("Employee Registered successfully in system");
            return "Employee Registered successfully in system";
        }else{
            logger.info("Employee Registration Failed");
            return "Employee Registration Failed";
        }

    }

}
