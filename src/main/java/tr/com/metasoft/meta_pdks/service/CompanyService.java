package tr.com.metasoft.meta_pdks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.metasoft.meta_pdks.model.Company;
import tr.com.metasoft.meta_pdks.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company save(Company company){
        return companyRepository.save(company);
    }

    //get all employees
    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    //get an employee by id
    public Company getById(String id){
        return companyRepository.findById(id).orElse(null);
    }

    //delete an employee
    public void delete(Company company){
        companyRepository.delete(company);
    }

    //get a company with company name
    public List<Company> getByCompany(String company){
        return companyRepository.findByCompanyName(company);
    }
}
