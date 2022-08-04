package com.example.demo.service;

import com.example.demo.model.dto.CompanyDTO;
import com.example.demo.model.dto.CompanyEmployeeDTO;
import com.example.demo.model.entity.Company;
import com.example.demo.model.entity.CompanyEmployee;
import com.example.demo.model.vo.NewCompanyVo;
import com.example.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyEmployeeService companyEmployeeService;

    public CompanyDTO addNewCompany(NewCompanyVo newCompanyVo){
        Company company = convert(newCompanyVo);
        company = companyRepository.save(company);

        return convert(company);
    }

    private Company convert(NewCompanyVo newCompanyVo){
        Company company = new Company();
        company.setStatus(newCompanyVo.getStatus());
        company.setName(newCompanyVo.getName());
        return company;
    }
    private CompanyDTO convert(Company company){
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setStatus(company.getStatus());
        companyDTO.setName(company.getName());
        companyDTO.setCreate_date(company.getCreate_date());
        companyDTO.setUpdate_date(company.getUpdate_date());
        return companyDTO;
    }

    public String getCompanyNameById(Long userId) {
        CompanyEmployeeDTO companyEmployeeDTO = companyEmployeeService.getCompanyIdByUserId(userId);
        Company company = companyRepository.findCompanyById(companyEmployeeDTO.getCompanyId());
        return  company.getName();
    }
}
