package com.example.demo.service;

import com.example.demo.model.dto.CompanyEmployeeDTO;
import com.example.demo.model.entity.CompanyEmployee;
import com.example.demo.model.vo.NewCompanyEmployeeVo;
import com.example.demo.repository.CompanyEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyEmployeeService {
    private final CompanyEmployeeRepository companyEmployeeRepository;

    public CompanyEmployeeDTO addNewEmployee(NewCompanyEmployeeVo newCompanyEmployeeVo) {
        CompanyEmployee companyEmployee = convert(newCompanyEmployeeVo);
        companyEmployee = companyEmployeeRepository.save(companyEmployee);
        return convert(companyEmployee);
    }

    private CompanyEmployee convert(NewCompanyEmployeeVo newCompanyEmployeeVo){
        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setUserId(newCompanyEmployeeVo.getUserId());
        companyEmployee.setCompanyId(newCompanyEmployeeVo.getCompanyId());
        return companyEmployee;
    }
    private CompanyEmployeeDTO convert(CompanyEmployee companyEmployee){
        CompanyEmployeeDTO companyEmployeeDTO = new CompanyEmployeeDTO();
        companyEmployeeDTO.setId(companyEmployee.getId());
        companyEmployeeDTO.setUserId(companyEmployee.getUserId());
        companyEmployeeDTO.setCompanyId(companyEmployee.getCompanyId());
        return companyEmployeeDTO;
    }

    public CompanyEmployeeDTO getCompanyIdByUserId(Long userId) {
        CompanyEmployee companyEmployee = companyEmployeeRepository.findCompanyEmployeeByUserId(userId);
        return convert(companyEmployee);
    }

}
