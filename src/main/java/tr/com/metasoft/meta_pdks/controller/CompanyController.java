package tr.com.metasoft.meta_pdks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tr.com.metasoft.meta_pdks.model.Company;
import tr.com.metasoft.meta_pdks.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    //add new companies
    @PostMapping(value = "/add")
    public Company addCompany(@Validated @RequestBody Company company) {
        return companyService.save(company);
    }

    //get all companies
    @GetMapping(value = "/getAll")
    public List<Company> getCompanies() {
        return companyService.findAll();
    }

    //get company by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Company> getById(@PathVariable(value = "id") String id) {
        Company company = companyService.getById(id);

        if (company == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(company);
        }
    }

    //update an company
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable(value = "id") String id, @RequestBody Company companyDetail) {
        Company company = companyService.getById(id);

        if (company == null) {
            return ResponseEntity.notFound().build();
        }

        if (companyDetail.getCompany() != null) {
            company.setCompany(companyDetail.getCompany());
        }

        Company updateCompany = companyService.save(company);

        return ResponseEntity.ok().body(updateCompany);

    }

    //delete a company
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable(value = "id") String id) {
        Company company = companyService.getById(id);

        if (company == null) {
            return ResponseEntity.notFound().build();
        }

        companyService.delete(company);

        return ResponseEntity.ok().build();
    }

}
